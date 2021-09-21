package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.UpdateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.AgreementConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.repository.AgreementRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.AgreementService;
import br.com.scsoftware.afinese.domains.basicrecords.service.GroupService;
import br.com.scsoftware.afinese.domains.basicrecords.service.PatientService;
import br.com.scsoftware.afinese.domains.basicrecords.service.ProgramService;
import br.com.scsoftware.afinese.infrastructure.common.exception.BadRequestException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private AgreementRepository repository;

    @Override
    public Page<Agreement> getAllRecords(final Long patientId, final Long programId, final StatusAgreement status,
                                         final Pageable pageRequest) {
        return repository.findByPatientId(patientId, programId, status, pageRequest);
    }

    @Override
    public Optional<Agreement> getRecord(final Long id) {
        return repository.findById(id);
    }

    @Override
    public CreateAgreementBO create(final CreateAgreementBO agreement) {
        final Agreement agreementEnt = AgreementConverter.fromBO(agreement);

        agreementEnt.setPatient(patientService.getRecord(agreement.getPatientId()).orElseThrow(() -> ResourceNotFoundException.of()));
        agreementEnt.setGroup(groupService.getRecord(agreement.getGroupId()).orElseThrow(() -> new BadRequestException("Group not found.")));
        agreementEnt.setProgram(programService.getRecord(agreement.getProgramId()).orElseThrow(() -> new BadRequestException("Program not found.")));
        agreementEnt.setStatus(StatusAgreement.ACTIVE);

        return AgreementConverter.toCreateBO(repository.save(agreementEnt));
    }

    @Override
    public UpdateAgreementBO update(final UpdateAgreementBO agreement, final Long id) {
        Agreement agreementEnt = getRecord(id).orElseThrow(() -> ResourceNotFoundException.of());
        agreementEnt = AgreementConverter.fromBO(agreement, agreementEnt);

        agreementEnt.setGroup(groupService.getRecord(agreement.getGroupId()).orElseThrow(() -> new BadRequestException("Group not found.")));
        agreementEnt.setProgram(programService.getRecord(agreement.getProgramId()).orElseThrow(() -> new BadRequestException("Program not found.")));

        return AgreementConverter.toUpdateBO(repository.save(agreementEnt));
    }
}
