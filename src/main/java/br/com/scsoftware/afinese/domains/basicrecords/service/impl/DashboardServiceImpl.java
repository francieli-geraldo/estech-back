package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.business.DailyPostingPendingDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.business.LaunchedPendingDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.repository.DailyPostingPendingDashboardRepository;
import br.com.scsoftware.afinese.domains.basicrecords.repository.LaunchedPendingDashboardRepository;
import br.com.scsoftware.afinese.domains.basicrecords.repository.SummaryDashboardRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final SummaryDashboardRepository summaryDashboardRepository;
    private final LaunchedPendingDashboardRepository launchedPendingDashboardRepository;
    private final DailyPostingPendingDashboardRepository dailyPostingPendingDashboardRepository;

    @Override
    public SummaryDashboard getSummaryDashboard() {
        return summaryDashboardRepository.getSummaryDashboard(UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public List<LaunchedPendingDashboard> getLaunchedPendingDashboard(LocalDate date) {
        return launchedPendingDashboardRepository.getLaunchedPendingDashboard(date, UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public List<DailyPostingPendingDashboard> getDailyPostingPendingDashboard(LocalDate date, Long groupId) {
        return dailyPostingPendingDashboardRepository.getDailyPostingPendingDashboard(date, groupId, UserServiceImpl.getTenantIdAuthenticatedUser());
    }


}
