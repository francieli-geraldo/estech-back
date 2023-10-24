package br.com.scsoftware.estech.domains.basicrecords.api.v1.web;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.CreatePatient;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.UpdatePatient;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.PatientResponse;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.SummarizedPatientResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.CreatePatientBO;
import br.com.scsoftware.estech.domains.basicrecords.converter.PatientConverter;
import br.com.scsoftware.estech.domains.basicrecords.entity.Patient;
import br.com.scsoftware.estech.domains.basicrecords.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/patients")
@Transactional(rollbackFor = Exception.class)
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<Page<PatientResponse>> getAll(@RequestParam(required = false) final String search,
                                                        @PageableDefault final Pageable page) {
        final Page<PatientResponse> patientList = patientService.getAllRecords(search, page)
                .map(PatientConverter::toDTO);

        if (patientList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(patientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getRecord(@PathVariable final Long id) {
        Optional<Patient> patient = patientService.getRecord(id);
        if (patient.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PatientConverter.toDTO(patient.get()));
    }

    @GetMapping("/summaries")
    public ResponseEntity<Page<SummarizedPatientResponse>> getByNameOrPhone(@RequestParam(required = false) final String search,
                                                                            @PageableDefault(sort="name")  final Pageable page) {
        final Page<SummarizedPatientResponse> patientList = patientService.getByNameOrPhone(search, page);

        if (patientList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(patientList);
    }

    @PostMapping
    public ResponseEntity postRecord(@Valid @RequestBody CreatePatient createPatient) {
        final CreatePatientBO patient = patientService.create(PatientConverter.toBO(createPatient));
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patient.getId())
                .toUri();

        return ResponseEntity.created(uriLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putRecord(@PathVariable final Long id, @Valid @RequestBody UpdatePatient updateGroup) {
        patientService.update(PatientConverter.toBO(id, updateGroup));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecord(@PathVariable final Long id) {
        patientService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
