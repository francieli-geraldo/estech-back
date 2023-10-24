package br.com.scsoftware.estech.domains.basicrecords.service;

import br.com.scsoftware.estech.domains.basicrecords.business.DailyPostingPendingDashboard;
import br.com.scsoftware.estech.domains.basicrecords.business.LaunchedPendingDashboard;
import br.com.scsoftware.estech.domains.basicrecords.business.SummaryDashboard;

import java.time.LocalDate;
import java.util.List;

public interface DashboardService  {

    SummaryDashboard getSummaryDashboard();

    List<LaunchedPendingDashboard> getLaunchedPendingDashboard(LocalDate date);

    List<DailyPostingPendingDashboard> getDailyPostingPendingDashboard(final LocalDate date, final Long groupId);
}
