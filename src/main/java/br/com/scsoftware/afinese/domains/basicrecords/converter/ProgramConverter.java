package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.ProgramResponse;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;

public class ProgramConverter {
    public static ProgramResponse toDTO(Program program) {
        return ProgramResponse.builder()
            .id(program.getId())
            .name(program.getName())
            .description(program.getDescription())
            .build();
    }
}
