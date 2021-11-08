package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.repository.SummaryDashboardRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final SummaryDashboardRepository summaryDashboardRepository;

    @Override
    public SummaryDashboard getSummaryDashboard() {
        return summaryDashboardRepository.getSummaryDashboard(UserServiceImpl.getTenantIdAuthenticatedUser());
    }
}
