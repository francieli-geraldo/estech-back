package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.business.DailyPostingBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.DailyWeightInformationBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.PeriodicReport;
import br.com.scsoftware.afinese.domains.basicrecords.business.TotalEvolutionReport;
import br.com.scsoftware.afinese.domains.basicrecords.converter.DailyPostingConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.repository.DailyPostingRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.AgreementService;
import br.com.scsoftware.afinese.domains.basicrecords.service.DailyPostingService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyPostingServiceImpl implements DailyPostingService {

    @Autowired
    private DailyPostingRepository repository;
    @Autowired
    private AgreementService agreementService;

    @Override
    public Page<DailyPosting> getAllRecords(final Long agreementId, final Long patientId, final Pageable pageRequest) {
        return repository.findByAgreementIdAndAgreementPatientIdAndTenantId(agreementId, patientId,
                UserServiceImpl.getTenantIdAuthenticatedUser(), pageRequest);
    }

    @Override
    public List<Map<String, Object>> getAllRecords(final Long groupId, final Long patientId, final LocalDate date, final Pageable pageRequest) {
        return repository.findByGroupIdAndPatientIdAndDateAndAgreementStatus(groupId, patientId, date, StatusAgreement.ACTIVE.name(),
                UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public Optional<DailyPosting> getRecord(final Long agreementId, final Long patientId, final Long id) {
        return repository.findByIdAndAgreementIdAndAgreementPatientIdAndTenantId(id, agreementId, patientId,
                UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public DailyPostingBO create(final DailyPostingBO dailyPosting, final Long patientId, final Long agreementId) {
        final DailyPosting dailyPostingEnt = DailyPostingConverter.fromBO(dailyPosting,
                repository.findByAgreementIdAndAgreementPatientIdAndDateAndTenantId(agreementId, patientId, dailyPosting.getDate(),
                        UserServiceImpl.getTenantIdAuthenticatedUser()).orElse(new DailyPosting()));
        dailyPostingEnt.setAgreement(agreementService.getRecord(agreementId).orElseThrow(() -> ResourceNotFoundException.of()));

        final List<DailyPosting> dailyPostingList = repository.findByAgreementIdAndAgreementPatientIdAndDateBeforeAndTenantIdOrderByDateDesc(agreementId,
                patientId, dailyPosting.getDate(), UserServiceImpl.getTenantIdAuthenticatedUser());
        if (dailyPostingList.isEmpty()) {
            dailyPostingEnt.setEvolution(dailyPosting.getCurrentWeight().subtract(dailyPostingEnt.getAgreement().getStartingWeight()));
            dailyPostingEnt.setPreviousWeight(dailyPostingEnt.getAgreement().getStartingWeight());
        } else {
            DailyPosting previousDailyPosting = dailyPostingList.get(0);
            if (previousDailyPosting.getDate().equals(dailyPosting.getDate()))
                previousDailyPosting = dailyPostingList.get(1);
            dailyPostingEnt.setEvolution(dailyPosting.getCurrentWeight().subtract(previousDailyPosting.getCurrentWeight()));
            dailyPostingEnt.setPreviousWeight(previousDailyPosting.getCurrentWeight());
        }

        dailyPostingEnt.setAccumulatedEvolution(dailyPostingEnt.getEvolution().add(BigDecimal.valueOf(dailyPostingList
                .stream()
                .map(DailyPosting::getEvolution)
                .collect(Collectors.summingDouble(BigDecimal::doubleValue)))));

        return DailyPostingConverter.toBO(repository.save(dailyPostingEnt));
    }

    @Override
    public DailyPostingBO update(final DailyPostingBO dailyPosting, final Long patientId, final Long agreementId, final Long id) {
        return null;
    }

    @Override
    public Page<PeriodicReport> getPeriodicReport(final Long groupId, final String initialDate, final String finalDate,
                                                  final StatusAgreement status, final Long patientId, final Pageable pageRequest) {
        LocalDate initialDateLocal = null;
        LocalDate finalDateLocal = null;
        if (StringUtils.hasText(initialDate)) {
            initialDateLocal = LocalDate.parse(initialDate);
        }

        if (StringUtils.hasText(finalDate)) {
            finalDateLocal = LocalDate.parse(finalDate);
        }

        return repository.getPeriodicReport(groupId, initialDateLocal, finalDateLocal, status == null ? null : status.name(), patientId,
                UserServiceImpl.getTenantIdAuthenticatedUser(), pageRequest);
    }

    @Override
    public Page<TotalEvolutionReport> getTotalEvolutionReport(final Long groupId, final Long patientId,
                                                              final StatusAgreement status, final Pageable pageRequest) {
        return repository.getTotalEvolutionReport(groupId, patientId, status == null ? null : status.name(),
                UserServiceImpl.getTenantIdAuthenticatedUser(), pageRequest);
    }

    @Override
    public boolean existsByAgreementIdAndDateLessThanEqual(Long agreementId, LocalDate date) {
        return repository.existsByAgreementIdAndDateLessThanEqualAndTenantId(agreementId, date, UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    public DailyWeightInformationBO getDailyWeightInformation(final Long patientId, final Agreement agreement, final LocalDate date,
                                                              BigDecimal currentWeight, BigDecimal evolution) {
        final DailyWeightInformationBO result = new DailyWeightInformationBO();

        final List<DailyPosting> dailyPostingList = repository.findByAgreementIdAndAgreementPatientIdAndDateBeforeAndTenantIdOrderByDateDesc(
                agreement.getId(), patientId, date, UserServiceImpl.getTenantIdAuthenticatedUser());
        if (dailyPostingList.isEmpty()) {
            result.setEvolution(currentWeight.subtract(agreement.getStartingWeight()));
            result.setPreviousWeight(agreement.getStartingWeight());
        } else {
            DailyPosting previousDailyPosting = dailyPostingList.get(0);
            if (previousDailyPosting.getDate().equals(date))
                previousDailyPosting = dailyPostingList.get(1);
            result.setEvolution(currentWeight.subtract(previousDailyPosting.getCurrentWeight()));
            result.setPreviousWeight(previousDailyPosting.getCurrentWeight());
        }

        result.setAccumulatedEvolution(evolution.add(BigDecimal.valueOf(dailyPostingList
                .stream()
                .map(DailyPosting::getEvolution)
                .collect(Collectors.summingDouble(BigDecimal::doubleValue)))));

        return result;
    }
}
