package br.com.scsoftware.estech.domains.basicrecords.repository;

import br.com.scsoftware.estech.domains.basicrecords.business.LaunchedPendingDashboard;
import br.com.scsoftware.estech.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LaunchedPendingDashboardRepository extends BaseRepository<DailyPosting> {
    @Query(value = "select " +
            "   g.id groupId, " +
            "   g.name groupName, " +
            "   sum(case when dp.id is null then 0 else 1 end) totalLaunched, " +
            "   sum(case when dp.id is null then 1 else 0 end) totalPending " +
            "from " +
            "   agreement a left join dailyposting dp on ( " +
            "       dp.agreement_id = a.id " +
            "   and dp.release_date = :date " +
            "   ) inner join grouppatients g on ( " +
            "       g.id = a.group_id " +
            "  ) " +
            "where " +
            "    a.tenant_id = :tenantId " +
            "and a.start_date <= :date " +
            "and a.status = 'ACTIVE' " +
            "group by " +
            "   g.id, " +
            "   g.name " +
            "order by totalPending desc", nativeQuery = true)
    List<LaunchedPendingDashboard> getLaunchedPendingDashboard(final LocalDate date, final Long tenantId);
}
