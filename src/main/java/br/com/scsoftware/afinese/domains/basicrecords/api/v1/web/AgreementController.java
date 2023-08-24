package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.AgreementProgramMonitoringResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.AgreementResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.AgreementConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/agreements")
@Transactional(rollbackFor = Exception.class)
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping
    public ResponseEntity<Page<AgreementResponse>> getAll(@RequestParam(required = false) final Long patientId,
                                                          @RequestParam(required = false) final Long programId,
                                                          @RequestParam(required = false) final StatusAgreement status,
                                                          @PageableDefault final Pageable page) {
        final Page<AgreementResponse> agreementList = agreementService.getAllRecords(patientId, programId, status, page)
                .map(AgreementConverter::toDTO);

        if (agreementList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(agreementList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementResponse> getRecord(@PathVariable("id") final Long agreementId) {
        Optional<Agreement> agreement = agreementService.getRecord(agreementId);
        if (agreement.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(AgreementConverter.toDTO(agreement.get()));
    }

    @GetMapping("/{id}/program-monitoring-form")
    public ResponseEntity<AgreementProgramMonitoringResponse> getProgramMonitoring(@PathVariable("id") final Long agreementId) {
        var response = agreementService.getProgramMonitoring(agreementId);
        if(Objects.isNull(response)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity postRecord(@Valid @RequestBody CreateAgreement createAgreement) {
        final CreateAgreementBO agreement = agreementService.create(AgreementConverter.toBO(createAgreement));
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(agreement.getId())
                .toUri();

        return ResponseEntity.created(uriLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putRecord(@PathVariable final Long id, @Valid @RequestBody UpdateAgreement updateAgreement) {
        agreementService.update(AgreementConverter.toBO(updateAgreement), id);

        return ResponseEntity.noContent().build();
    }
}
