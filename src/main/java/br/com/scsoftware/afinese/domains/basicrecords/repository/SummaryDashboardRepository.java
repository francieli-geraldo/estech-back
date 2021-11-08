package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryDashboardRepository extends BaseRepository<DailyPosting> {
    @Query(value = "select " +
            "  sum(case " +
            "    when date_format(a.created_at, '%Y%m') = date_format(now(), '%Y%m') then 1 " +
            "    else 0 " +
            "  end) totalNewContractsMonth, " +
            "  count(a.id) totalNewContracts, " +
            "  sum(case " +
            "    when date_format(a.created_at, '%Y%m') = date_format(now(), '%Y%m') and a.status = 'COMPLETED' then 1 " +
            "    else 0 " +
            "  end) totalCompletedContractsMonth, " +
            "  sum(case " +
            "    when a.status = 'COMPLETED' then 1 " +
            "    else 0 " +
            "  end) totalCompletedContracts, " +
            "  sum(case " +
            "    when week(d.release_date) = week(now()) and date_format(d.release_date, '%Y%m') = date_format(now(), '%Y%m') then 1 " +
            "    else 0 " +
            "  end) totalReleasesWeek, " +
            "  sum(case " +
            "    when date_format(d.release_date, '%Y%m') = date_format(now(), '%Y%m') then 1 " +
            "    else 0 " +
            "  end) totalReleasesMonth, " +
            "  sum(case " +
            "    when date_format(d.release_date, '%Y%m') = date_format(now(), '%Y%m') then d.evolution " +
            "    else 0 " +
            "  end) totalWeightMonth, " +
            "  sum(d.evolution) totalWeight " +
            "from " +
            "  agreement a left join dailyposting d on ( " +
            "      d.agreement_id = a.id " +
            "  and d.tenant_id = a.tenant_id " +
            "  and d.tenant_id = a.tenant_id " +
            "  ) " +
            "where " +
            "  a.tenant_id = :tenantId", nativeQuery = true)
    SummaryDashboard getSummaryDashboard(final Long tenantId);
}
