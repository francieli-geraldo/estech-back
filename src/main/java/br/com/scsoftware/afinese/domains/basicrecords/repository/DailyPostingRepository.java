package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.business.PeriodicReport;
import br.com.scsoftware.afinese.domains.basicrecords.business.TotalEvolutionReport;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DailyPostingRepository extends BaseRepository<DailyPosting> {

    Page<DailyPosting> findByAgreementIdAndAgreementPatientId(Long agreementId, Long patientId, Pageable pageRequest);

    List<DailyPosting> findByAgreementIdAndAgreementPatientIdAndDateBeforeOrderByDateDesc(Long agreementId, Long patientId, LocalDate date);

    Optional<DailyPosting> findByIdAndAgreementIdAndAgreementPatientId(Long id, Long agreementId, Long patientId);

    Optional<DailyPosting> findByAgreementIdAndAgreementPatientIdAndDate(Long agreementId, Long patientId, LocalDate date);

    Page<DailyPosting> findByAgreementGroupIdAndAgreementPatientIdAndDateAndAgreementStatus(Long groupId, Long patientId, LocalDate date, StatusAgreement status, Pageable pageRequest);

    Page<DailyPosting> findByAgreementGroupIdAndDateAndAgreementStatus(Long programId, LocalDate date, StatusAgreement status, Pageable pageRequest);

    @Query(value = "select " +
            "   dp.id, " +
            "   a.id agreementId, " +
            "   g.id groupId, " +
            "   g.name groupName, " +
            "   a.patient_id patientId, " +
            "   p.name patientName, " +
            "   coalesce(dp.release_date, :date) releaseDate, " +
            "   coalesce(dp.balance, 0) balance, " +
            "   coalesce(dp.previous_weight, 0) previousWeight, " +
            "   coalesce(dp.current_weight, 0) currentWeight, " +
            "   coalesce(dp.evolution, 0) evolution, " +
            "   coalesce(dp.accumulated_evolution, 0) accumulatedEvolution, " +
            "   coalesce(dp.breakfast, 0) breakfast, " +
            "   coalesce(dp.morning_snack, 0) morningSnack, " +
            "   coalesce(dp.afternoon_snack, 0) afternoonSnack, " +
            "   coalesce(dp.dinner, 0) dinner, " +
            "   coalesce(dp.hiit, 0) hiit, " +
            "   coalesce(dp.lunch, 0) lunch, " +
            "   dp.notes " +
            "from " +
            "   agreement a left join dailyposting dp on ( " +
            "       dp.agreement_id = a.id " +
            "   and dp.release_date = :date " +
            "   ) inner join grouppatients g on ( " +
            "       g.id = a.group_id " +
            "  ) inner join patient p on ( " +
            "    p.id = a.patient_id " +
            "  ) " +
            "where  " +
            "    a.group_id = :groupId " +
            "and a.start_date <= :date " +
            "and (:patientId is null or a.patient_id = :patientId) " +
            "and a.status = :status order by p.name ", nativeQuery = true)
    List<Map<String, Object>> findByGroupIdAndPatientIdAndDateAndAgreementStatus(Long groupId, Long patientId, LocalDate date, String status);

    @Query(value = "select " +
            "  g.name groupName, " +
            "  p.name patientName, " +
            "  pg.name programName, " +
            "  a.status, " +
            "  a.start_date startDate, " +
            "  coalesce(a.cancellation_date, a.date_conclusion) dateConclusion, " +
            "  sum(d.breakfast) breakfastTotal, " +
            "  sum(d.morning_snack) morningSnackTotal, " +
            "  sum(d.lunch) lunchTotal, " +
            "  sum(d.afternoon_snack) afternoonSnackTotal, " +
            "  sum(d.dinner) dinnerTotal, " +
            "  sum(d.hiit) hiitTotal, " +
            "  (((sum(d.breakfast) + sum(d.morning_snack) + sum(d.lunch) + sum(d.morning_snack) + sum(d.dinner) + sum(d.hiit)) * count(d.id)) / (count(d.id) * 6 * count(d.id))) * 100 postingPercentage, " +
            "  sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) and d.balance then 1 " +
            "    else 0 " +
            "  end) balanceTotal, " +
            "  coalesce(sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) and d.balance then 1 " +
            "    else 0 " +
            "  end) / sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) then 1 " +
            "    else 0 " +
            "  end) * 100, 0) balancePercentage, " +
            "  sum(d.accumulated_evolution) evolutionPeriod, " +
            "  a.goal - a.starting_weight goal, " +
            "  count(d.notes) notesTotal " +
            "from " +
            "  dailyposting d inner join agreement a on ( " +
            "    a.id = d.agreement_id " +
            "  ) inner join patient p on ( " +
            "    p.id = a.patient_id " +
            "  ) inner join programs pg on ( " +
            "        pg.id = a.program_id " +
            "    and pg.active = 1 " +
            "  ) inner join grouppatients g on ( " +
            "    g.id = a.program_id " +
            "  ) " +
            "where " +
            "    (:groupId is null or g.id = :groupId) " +
            "and (:status is null or a.status = :status) " +
            "and (:patientId is null or p.id = :patientId) " +
            "and (:initialDate is null or d.release_date between :initialDate and :finalDate) " +
            "group by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name, " +
            "  a.starting_weight - a.goal, " +
            "  d.agreement_id " +
            "order by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name ", nativeQuery = true)
    Page<PeriodicReport> getPeriodicReport(final Long groupId, final LocalDate initialDate, final LocalDate finalDate,
                                           final String status, final Long patientId, final Pageable pageRequest);

    @Query(value = "select " +
            "  g.name groupName, " +
            "  p.name patientName, " +
            "  pg.name programName, " +
            "  a.status, " +
            "  (((sum(d.breakfast) + sum(d.morning_snack) + sum(d.lunch) + sum(d.morning_snack) + sum(d.dinner) + sum(d.hiit)) * count(d.id)) / (count(d.id) * 6 * count(d.id))) * 100 postingPercentage, " +
            "  coalesce(sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) and d.balance then 1 " +
            "    else 0 " +
            "  end) / sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) then 1 " +
            "    else 0 " +
            "  end) * 100, 0) balancePercentage, " +
            "  sum(d.accumulated_evolution) evolutionPeriod, " +
            "  a.goal - a.starting_weight goal," +
            "  sum((d.accumulated_evolution / (a.goal - a.starting_weight)) * 100) goalPercentage, " +
            "  count(d.notes) notesTotal " +
            "from " +
            "  dailyposting d inner join agreement a on ( " +
            "    a.id = d.agreement_id " +
            "  ) inner join patient p on ( " +
            "    p.id = a.patient_id " +
            "  ) inner join programs pg on ( " +
            "        pg.id = a.program_id " +
            "    and pg.active = 1 " +
            "  ) inner join grouppatients g on ( " +
            "    g.id = a.program_id " +
            "  ) " +
            "where " +
            "    (:groupId is null or g.id = :groupId) " +
            "and (:status is null or a.status = :status) " +
            "and (:patientId is null or p.id = :patientId) " +
            "group by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name, " +
            "  a.starting_weight - a.goal, " +
            "  d.agreement_id, " +
            "  a.status " +
            "order by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name ", nativeQuery = true)
    Page<TotalEvolutionReport> getTotalEvolutionReport(final Long groupId, final Long patientId, final String status,
                                                       final Pageable pageRequest);
}
