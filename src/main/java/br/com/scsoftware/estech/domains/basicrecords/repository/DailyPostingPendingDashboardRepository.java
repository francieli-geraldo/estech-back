package br.com.scsoftware.estech.domains.basicrecords.repository;

import br.com.scsoftware.estech.domains.basicrecords.business.DailyPostingPendingDashboard;
import br.com.scsoftware.estech.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyPostingPendingDashboardRepository extends BaseRepository<DailyPosting> {
    @Query(value = "select " +
            "   p.id patientId, " +
            "   p.name patientName, " +
            "   p.phone patientPhone, " +
            "   pg.id programId, " +
            "   pg.name programName, " +
            "   a.id agreementId " +
            "from " +
            "   agreement a left join dailyposting dp on ( " +
            "       dp.agreement_id = a.id " +
            "   and dp.release_date = :date " +
            "   ) inner join programs pg on ( " +
            "       pg.id = a.program_id " +
            "  ) inner join patient p on ( " +
            "    p.id = a.patient_id " +
            "  ) " +
            "where " +
            "    a.tenant_id = :tenantId " +
            "and (:groupId is null or a.group_id = :groupId) " +
            "and a.start_date <= :date " +
            "and a.status = 'ACTIVE' " +
            "and dp.id is null " +
            "order by p.name ", nativeQuery = true)
    List<DailyPostingPendingDashboard> getDailyPostingPendingDashboard(final LocalDate date, final Long groupId, final Long tenantId);
}
