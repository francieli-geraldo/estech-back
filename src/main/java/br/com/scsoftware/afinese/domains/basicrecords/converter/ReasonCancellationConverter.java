package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.ReasonCancellationResponse;
import br.com.scsoftware.afinese.domains.basicrecords.entity.ReasonCancellation;

public class ReasonCancellationConverter {
    public static ReasonCancellationResponse toDTO(ReasonCancellation reasonCancellation) {
        return ReasonCancellationResponse.builder()
            .id(reasonCancellation.getId())
            .reason(reasonCancellation.getReason())
            .description(reasonCancellation.getDescription())
            .build();
    }
}
