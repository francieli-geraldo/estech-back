package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.business.DailyWeightInformation;
import br.com.scsoftware.afinese.domains.basicrecords.business.PeriodicReport;
import br.com.scsoftware.afinese.domains.basicrecords.business.TotalEvolutionReport;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DailyPostingRepository extends BaseRepository<DailyPosting> {

    Page<DailyPosting> findByAgreementIdAndAgreementPatientIdAndTenantId(Long agreementId, Long patientId, Long tenantId, Pageable pageRequest);

    @Query(value = "select " +
            "   agreement_id agreementId, " +
            "   release_date date, " +
            "   current_weight currentWeight, " +
            "   evolution evolution " +
            "from " +
            "   dailyposting " +
            "where " +
            "   agreement_id in (:agreementsId) " +
            "and release_date < :date " +
            "and tenant_id    = :tenantId " +
            "order by " +
            "   release_date desc", nativeQuery = true)
    List<DailyWeightInformation> getDailyWeightInformation(ArrayList<Long> agreementsId, LocalDate date, Long tenantId);

    @Query(value = "select " +
            "   agreement_id agreementId, " +
            "   release_date date, " +
            "   current_weight currentWeight, " +
            "   evolution evolution " +
            "from " +
            "   dailyposting " +
            "where " +
            "   agreement_id = :agreementId " +
            "and tenant_id   = :tenantId " +
            "order by " +
            "   release_date asc", nativeQuery = true)
    List<DailyWeightInformation> getDailyWeightInformation(Long agreementId, Long tenantId);

    boolean existsByAgreementIdAndDateLessThanEqualAndTenantId(Long agreementId, LocalDate date, Long tenantId);

    Optional<DailyPosting> findByIdAndAgreementIdAndAgreementPatientIdAndTenantId(Long id, Long agreementId, Long patientId, Long tenantId);

    Optional<DailyPosting> findByAgreementIdAndAgreementPatientIdAndDateAndTenantId(Long agreementId, Long patientId, LocalDate date, Long tenantId);

    @Query(value = "select " +
            "   dp.id, " +
            "   a.id agreementId, " +
            "   a.starting_weight startingWeight, " +
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
            "    a.tenant_id = :tenantId " +
            "and a.group_id = :groupId " +
            "and a.start_date <= :date " +
            "and (:patientId is null or a.patient_id = :patientId) " +
            "and a.status = :status order by p.name ", nativeQuery = true)
    List<Map<String, Object>> findByGroupIdAndPatientIdAndDateAndAgreementStatus(Long groupId, Long patientId, LocalDate date, String status, Long tenantId);

    @Query(value = "select " +
            "  g.name groupName, " +
            "  p.name patientName, " +
            "  pg.name programName, " +
            "  a.status, " +
            "  a.start_date startDate, " +
            "  a.starting_weight startingWeight, " +
            "  sum(d.evolution) accumulatedEvolution, " +
            "  a.starting_weight + sum(d.evolution) currentWeight, " +
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
            "  sum(d.evolution) evolutionPeriod, " +
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
            "    g.id = a.group_id " +
            "  ) " +
            "where " +
            "    d.tenant_id = :tenantId " +
            "and (:groupId is null or g.id = :groupId) " +
            "and (:status is null or a.status = :status) " +
            "and (:agreementId is null or a.id = :agreementId) " +
            "and (:patientId is null or p.id = :patientId) " +
            "and (:initialDate is null or d.release_date between :initialDate and :finalDate) " +
            "and (:daysOfOverdue is null or DATEDIFF(a.hiring_date, now()) < :daysOfOverdue) " +
            "group by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name, " +
            "  a.starting_weight, " +
            "  a.starting_weight - a.goal, " +
            "  d.agreement_id " +
            "order by " +
            "  g.name, " +
            "  p.name, " +
            "  pg.name ",
            countQuery = "select " +
            "  count(d.id) total " +
            "from ca" +
            "  dailyposting d inner join agreement a on ( " +
            "    a.id = d.agreement_id " +
            "  ) inner join patient p on ( " +
            "    p.id = a.patient_id " +
            "  ) inner join programs pg on ( " +
            "        pg.id = a.program_id " +
            "    and pg.active = 1 " +
            "  ) inner join grouppatients g on ( " +
            "    g.id = a.group_id " +
            "  ) " +
            "where " +
            "    d.tenant_id = :tenantId " +
            "and (:groupId is null or g.id = :groupId) " +
            "and (:status is null or a.status = :status) " +
            "and (:agreementId is null or a.id = :agreementId) " +
            "and (:patientId is null or p.id = :patientId) " +
            "and (:initialDate is null or d.release_date between :initialDate and :finalDate) " +
            "and (:daysOfOverdue is null or DATEDIFF(a.hiring_date, now()) < :daysOfOverdue) ",
            nativeQuery = true)
    Page<PeriodicReport> getPeriodicReport(final Long groupId, final LocalDate initialDate, final LocalDate finalDate,
                                           final String status, final Long patientId, final Long tenantId,
                                           final Integer daysOfOverdue, final Long agreementId, final Pageable pageRequest);

    @Query(value = "select " +
            "  g.name groupName, " +
            "  p.name patientName, " +
            "  pg.name programName, " +
            "  case " +
            "    when a.status <> 'ACTIVE' then a.status " +
            "    when a.status = 'ACTIVE' and not :daysOfOverdue is null and DATEDIFF(a.hiring_date, now()) < 0 then 'OVERDUE'" +
            "    when a.status = 'ACTIVE' and not :daysOfOverdue is null and DATEDIFF(a.hiring_date, now()) < 7 then 'OVERDUE_LESS_7'" +
            "    when a.status = 'ACTIVE' and not :daysOfOverdue is null and DATEDIFF(a.hiring_date, now()) < 15 then 'OVERDUE_LESS_15'" +
            "    when a.status = 'ACTIVE' and not :daysOfOverdue is null and DATEDIFF(a.hiring_date, now()) < 30 then 'OVERDUE_LESS_30'" +
            "    else a.status" +
            "  end status, " +
            "  (((sum(d.breakfast) + sum(d.morning_snack) + sum(d.lunch) + sum(d.morning_snack) + sum(d.dinner) + sum(d.hiit)) * count(d.id)) / (count(d.id) * 6 * count(d.id))) * 100 postingPercentage, " +
            "  coalesce(sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) and d.balance then 1 " +
            "    else 0 " +
            "  end) / sum(case " +
            "    when dayofweek(d.release_date) in (2, 6) then 1 " +
            "    else 0 " +
            "  end) * 100, 0) balancePercentage, " +
            "  sum(d.evolution) evolutionPeriod, " +
            "  a.goal - a.starting_weight goal," +
            "  sum((d.evolution / (a.goal - a.starting_weight)) * 100) goalPercentage, " +
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
            "    g.id = a.group_id " +
            "  ) " +
            "where " +
            "    d.tenant_id = :tenantId " +
            "and (:groupId is null or g.id = :groupId) " +
            "and (:status is null or a.status = :status) " +
            "and (:patientId is null or p.id = :patientId) " +
            "and (:daysOfOverdue is null or DATEDIFF(a.hiring_date, now()) < :daysOfOverdue) " +
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
            "  pg.name ",
            countQuery = "select " +
                    "  count(d.id) total " +
                    "from " +
                    "  dailyposting d inner join agreement a on ( " +
                    "    a.id = d.agreement_id " +
                    "  ) inner join patient p on ( " +
                    "    p.id = a.patient_id " +
                    "  ) inner join programs pg on ( " +
                    "        pg.id = a.program_id " +
                    "    and pg.active = 1 " +
                    "  ) inner join grouppatients g on ( " +
                    "    g.id = a.group_id " +
                    "  ) " +
                    "where " +
                    "    d.tenant_id = :tenantId " +
                    "and (:groupId is null or g.id = :groupId) " +
                    "and (:status is null or a.status = :status) " +
                    "and (:patientId is null or p.id = :patientId) " +
                    "and (:daysOfOverdue is null or DATEDIFF(a.hiring_date, now()) < :daysOfOverdue) ",
            nativeQuery = true)
    Page<TotalEvolutionReport> getTotalEvolutionReport(final Long groupId, final Long patientId, final String status,
                                                       final Long tenantId, final Integer daysOfOverdue, final Pageable pageRequest);
}
