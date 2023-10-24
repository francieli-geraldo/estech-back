package br.com.scsoftware.estech.domains.basicrecords.api.v1.web;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.PeriodicReportResponse;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.TotalEvolutionReportResponse;
import br.com.scsoftware.estech.domains.basicrecords.converter.PeriodicReportConverter;
import br.com.scsoftware.estech.domains.basicrecords.converter.TotalEvolutionReportConverter;
import br.com.scsoftware.estech.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.estech.domains.basicrecords.service.DailyPostingService;
import br.com.scsoftware.estech.infrastructure.common.exception.BadRequestException;
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
@RequestMapping(path = "/v1/reports/")
public class ReportController {

    private final DailyPostingService dailyPostingService;

    @GetMapping("periodic")
    public ResponseEntity<Page<PeriodicReportResponse>> getPeriodicReport(@RequestParam(required = false) final Long groupId,
                                                                          @RequestParam(required = false) final String status,
                                                                          @RequestParam(required = false) final Long patientId,
                                                                          @RequestParam(required = false) final String initialDate,
                                                                          @RequestParam(required = false) final String finalDate,
                                                                          @PageableDefault final Pageable page) {

        if (StringUtils.hasText(status) && !StatusAgreement.COMPLETED.toString().equals(status)) {
            if (!StringUtils.hasText(initialDate)) {
                throw new BadRequestException("The start date is mandatory");
            }

            if (!StringUtils.hasText(finalDate)) {
                throw new BadRequestException("The end date is mandatory");
            }
        }

        StatusAgreement statusAgreement = null;
        if (StringUtils.hasText(status)) {
            statusAgreement = StatusAgreement.valueOf(status);
        }

        final Page<PeriodicReportResponse> listPeriodicReport = dailyPostingService.getPeriodicReport(groupId,
                        initialDate, finalDate, statusAgreement, patientId, null, page)
                .map(PeriodicReportConverter::toDTO);

        if (listPeriodicReport.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listPeriodicReport);
    }

    @GetMapping("total-evolution")
    public ResponseEntity<Page<TotalEvolutionReportResponse>> getTotalEvolution(@RequestParam(required = false) final Long groupId,
                                                                                @RequestParam(required = false) final String status,
                                                                                @RequestParam(required = false) final Long patientId,
                                                                                @PageableDefault final Pageable page) {
        StatusAgreement statusAgreement = null;
        if (StringUtils.hasText(status)) {
            statusAgreement = StatusAgreement.valueOf(status);
        }

        final Page<TotalEvolutionReportResponse> listPeriodicReport = dailyPostingService.getTotalEvolutionReport(groupId,
                        patientId, statusAgreement, page)
                .map(TotalEvolutionReportConverter::toDTO);

        if (listPeriodicReport.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(listPeriodicReport);
    }
}
