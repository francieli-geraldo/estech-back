package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateGroup;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateProgram;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateGroup;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateProgram;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.ProgramResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.GroupConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.ProgramConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.ProgramServiceImpl;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
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
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/programs")
@Transactional(rollbackFor = Exception.class)
public class ProgramController {

    private final ProgramServiceImpl programService;

    @GetMapping
    public ResponseEntity<Page<ProgramResponse>> getAll(@PageableDefault(sort="name")  final Pageable page) {
        final Page<ProgramResponse> programList = programService.getAllRecords(page)
                .map(ProgramConverter::toDTO);

        if (programList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(programList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponse> getRecord(@PathVariable final Long id) {
        Optional<Program> program = programService.getRecord(id);
        if (program.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ProgramConverter.toDTO(program.get()));
    }

    @PostMapping
    public ResponseEntity postRecord(@Valid @RequestBody CreateProgram createProgram) {
        final Long programId = programService.create(ProgramConverter.toEntity(createProgram));
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(programId)
                .toUri();

        return ResponseEntity.created(uriLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putRecord(@PathVariable final Long id, @Valid @RequestBody UpdateProgram updateProgram) {
        programService.update(ProgramConverter.toEntity(programService.getRecord(id).orElseThrow(() -> ResourceNotFoundException.of()), updateProgram));

        return ResponseEntity.noContent().build();
    }
}
