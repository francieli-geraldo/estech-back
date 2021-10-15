package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.PeriodicReportResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummaryDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.TotalEvolutionReportResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.PeriodicReportConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.SummaryDashboardConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.TotalEvolutionReportConverter;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.service.DashboardService;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.DailyPostingServiceImpl;
import br.com.scsoftware.afinese.infrastructure.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/dashboards/")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("summary")
    public ResponseEntity<SummaryDashboardResponse> getSummaryDashboard() {

        return ResponseEntity.ok(SummaryDashboardConverter.toDTO(dashboardService.getSummaryDashboard()));
    }

}
