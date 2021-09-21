package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummarizedPatientResponse {
    private Long id;
    private String name;
    private String phone;
}
