package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReasonCancellationResponse {
    private Long id;
    private String reason;
    private String description;
}
