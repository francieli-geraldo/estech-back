package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.DailyPostingPendingDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.LaunchedPendingDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummaryDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.DailyPostingPendingDashboardConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.LaunchedPendingDashboardConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.SummaryDashboardConverter;
import br.com.scsoftware.afinese.domains.basicrecords.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/dashboards/")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("summary")
    public ResponseEntity<SummaryDashboardResponse> getSummaryDashboard() {

        return ResponseEntity.ok(SummaryDashboardConverter.toDTO(dashboardService.getSummaryDashboard()));
    }

    @GetMapping("launched-pending")
    public ResponseEntity<List<LaunchedPendingDashboardResponse>> getLaunchedPendingDashboard(@RequestParam final String date) {

        return ResponseEntity.ok(dashboardService.getLaunchedPendingDashboard(LocalDate.parse(date))
                .stream()
                .map(LaunchedPendingDashboardConverter::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("daily-posting-pending")
    public ResponseEntity<List<DailyPostingPendingDashboardResponse>> getDailyPostingPendingDashboard(
            @RequestParam final Long groupId,
            @RequestParam final String date) {

        return ResponseEntity.ok(dashboardService.getDailyPostingPendingDashboard(LocalDate.parse(date), groupId)
                .stream()
                .map(DailyPostingPendingDashboardConverter::toDTO)
                .collect(Collectors.toList()));
    }
}
