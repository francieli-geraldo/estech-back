package br.com.scsoftware.afinese.domains.basicrecords.service;

import br.com.scsoftware.afinese.domains.basicrecords.business.*;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DailyPostingService {

    Page<DailyPosting> getAllRecords(Long agreementId, Long patientId, Pageable pageRequest);

    List<Map<String, Object>> getAllRecords(Long groupId, Long patientId, LocalDate date, Pageable pageRequest);

    Optional<DailyPosting> getRecord(Long agreementId, Long patientId, Long id);

    DailyPostingBO create(DailyPostingBO dailyPosting, Long patientId, Long agreementId);

    DailyPostingBO update(DailyPostingBO dailyPosting, Long patientId, Long agreementId, Long id);

    Page<PeriodicReport> getPeriodicReport(Long groupId, String initialDate, String Date, StatusAgreement status, Long patientId, Pageable pageRequest);

    Page<TotalEvolutionReport> getTotalEvolutionReport(Long groupId, Long patientId, StatusAgreement status, Pageable pageRequest);

    boolean existsByAgreementIdAndDateLessThanEqual(Long agreementId, LocalDate date);

    DailyWeightInformationBO getDailyWeightInformation(LocalDate date, BigDecimal currentWeight, BigDecimal evolution,
                                                       Long currentAgreementId, List<DailyWeightInformation> dailyWeightList,
                                                       BigDecimal agreementStartingWeight);

    List<DailyWeightInformation> getDailyWeightInformation(LocalDate date, ArrayList<Long> agreementsId);
}
