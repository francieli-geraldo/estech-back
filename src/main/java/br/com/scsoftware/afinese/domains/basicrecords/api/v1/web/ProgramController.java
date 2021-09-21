package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.ProgramResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.ProgramConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.ProgramServiceImpl;
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
@RequestMapping(path = "/v1/programs")
@Transactional(rollbackFor = Exception.class)
public class ProgramController {

    private final ProgramServiceImpl programService;

    @GetMapping
    public ResponseEntity<Page<ProgramResponse>> getAll(@PageableDefault final Pageable page) {
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
}
