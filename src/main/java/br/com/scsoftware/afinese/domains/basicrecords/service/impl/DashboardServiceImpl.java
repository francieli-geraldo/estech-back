package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.repository.SummaryDashboardRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SummaryDashboardRepository summaryDashboardRepository;

    @Override
    public SummaryDashboard getSummaryDashboard() {
        return summaryDashboardRepository.getSummaryDashboard();
    }
}
