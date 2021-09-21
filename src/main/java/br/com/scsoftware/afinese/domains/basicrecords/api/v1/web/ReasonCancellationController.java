package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.ReasonCancellationResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.ReasonCancellationConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.ReasonCancellation;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.ReasonCancellationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/reason-cancellations")
@Transactional(rollbackFor = Exception.class)
public class ReasonCancellationController {

    private final ReasonCancellationServiceImpl reasonCancellationService;

    @GetMapping
    public ResponseEntity<Page<ReasonCancellationResponse>> getAll(@PageableDefault final Pageable page) {
        final Page<ReasonCancellationResponse> programList = reasonCancellationService.getAllRecords(page)
                .map(ReasonCancellationConverter::toDTO);

        if (programList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(programList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReasonCancellationResponse> getRecord(@PathVariable final Long id) {
        Optional<ReasonCancellation> reasonCancellation = reasonCancellationService.getRecord(id);
        if (reasonCancellation.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ReasonCancellationConverter.toDTO(reasonCancellation.get()));
    }
}
