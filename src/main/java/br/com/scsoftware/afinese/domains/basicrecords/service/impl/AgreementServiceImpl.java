package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.UpdateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.AgreementConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.repository.AgreementRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.*;
import br.com.scsoftware.afinese.infrastructure.common.exception.BadRequestException;
import br.com.scsoftware.afinese.infrastructure.common.exception.BusinessException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DailyPostingService dailyPostingService;
    @Autowired
    private AgreementRepository repository;

    @Override
    public Page<Agreement> getAllRecords(final Long patientId, final Long programId, final StatusAgreement status,
                                         final Pageable pageRequest) {
        var statusToSearch = status;
        if (Objects.nonNull(status) && StatusAgreement.OVERDUE.equals(status) || StatusAgreement.OVERDUE_LESS_7.equals(status) ||
                StatusAgreement.OVERDUE_LESS_15.equals(status) || StatusAgreement.OVERDUE_LESS_30.equals(status)) {
            statusToSearch = StatusAgreement.ACTIVE;

            var daysOfOverdue = 0;
            if (StatusAgreement.OVERDUE_LESS_7.equals(status)) {
                daysOfOverdue = 7;
            } else if (StatusAgreement.OVERDUE_LESS_15.equals(status)) {
                daysOfOverdue = 15;
            } else if (StatusAgreement.OVERDUE_LESS_30.equals(status)) {
                daysOfOverdue = 30;
            }

            return repository.findAll(patientId, programId, statusToSearch, UserServiceImpl.getTenantIdAuthenticatedUser(), daysOfOverdue , LocalDate.now(), pageRequest);
        }

        return repository.findAll(patientId, programId, statusToSearch, UserServiceImpl.getTenantIdAuthenticatedUser(), pageRequest);
    }

    @Override
    public Optional<Agreement> getRecord(final Long id) {
        return repository.findByIdAndTenantId(id, UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public CreateAgreementBO create(final CreateAgreementBO agreement) {
        final Agreement agreementEnt = AgreementConverter.fromBO(agreement);

        agreementEnt.setPatient(patientService.getRecord(agreement.getPatientId()).orElseThrow(ResourceNotFoundException::of));
        agreementEnt.setGroup(groupService.getRecord(agreement.getGroupId()).orElseThrow(() -> new BadRequestException("Group not found.")));
        agreementEnt.setProgram(programService.getRecord(agreement.getProgramId()).orElseThrow(() -> new BadRequestException("Program not found.")));
        agreementEnt.setStatus(StatusAgreement.ACTIVE);

        return AgreementConverter.toCreateBO(repository.save(agreementEnt));
    }

    @Override
    public UpdateAgreementBO update(final UpdateAgreementBO agreement, final Long id) {
        Agreement agreementEnt = getRecord(id).orElseThrow(ResourceNotFoundException::of);

        if (StatusAgreement.CANCELED.equals(agreementEnt.getStatus()) && StatusAgreement.COMPLETED.equals(agreement.getStatus())) {
            String msgErro = "Um contrato cancelado não pode ser marcado como concluído.";
            throw new ConflictException(msgErro, msgErro);
        }

        if (StatusAgreement.COMPLETED.equals(agreementEnt.getStatus()) && StatusAgreement.CANCELED.equals(agreement.getStatus())) {
            String msgErro = "Um contrato concluído não pode ser marcado como cancelado.";
            throw new ConflictException(msgErro, msgErro);
        }

        if (StatusAgreement.ACTIVE.equals(agreement.getStatus()) && !agreementEnt.isOpened()) {
            if (!canCancelAgreement(agreementEnt)) {
                String msgErro = "Você não pode reativar um contrato que esteja cancelado/concluído a mais de 30 dias.";
                throw new ConflictException(msgErro, msgErro);
            } else {
                agreement.setCancellationDate(null);
                agreement.setReasonCancellation(null);
                agreement.setDateConclusion(null);
            }
        }

        if (agreement.getStartDate().compareTo(agreement.getHiringDate()) > 0) {
            String msgErro = "A data de início do contrato não pode ser maior que a data prevista para encerramento.";
            throw new BusinessException(msgErro, msgErro);
        }

        if (agreementEnt.getStartDate().compareTo(agreement.getStartDate()) != 0) {
            if (dailyPostingService.existsByAgreementIdAndDateLessThanEqual(agreementEnt.getId(), agreementEnt.getStartDate())) {
                String msgErro = "Não é permitido alterar a data de início, pois já existem lançamentos para este contrato.";
                throw new ConflictException(msgErro, msgErro);
            }
        }

        agreementEnt = AgreementConverter.fromBO(agreement, agreementEnt);

        agreementEnt.setGroup(groupService.getRecord(agreement.getGroupId()).orElseThrow(() -> new BadRequestException("Group not found.")));
        agreementEnt.setProgram(programService.getRecord(agreement.getProgramId()).orElseThrow(() -> new BadRequestException("Program not found.")));

        return AgreementConverter.toUpdateBO(repository.save(agreementEnt));
    }

    private boolean canCancelAgreement(Agreement agreement) {
        LocalDate dateConclusion = agreement.getDateConclusion() == null ? agreement.getCancellationDate() : agreement.getDateConclusion();
        return ChronoUnit.DAYS.between(dateConclusion, LocalDate.now()) < 31;
    }
}