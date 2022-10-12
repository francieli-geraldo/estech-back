package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateProgram;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateProgram;
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

    public static Program toEntity(CreateProgram program) {
        Program ent = new Program();
        ent.setName(program.getName());
        ent.setDescription(program.getDescription());

        return ent;
    }

    public static Program toEntity(Program ent, UpdateProgram updateProgram) {
        ent.setName(updateProgram.getName());
        ent.setDescription(updateProgram.getDescription());

        return ent;
    }
}
