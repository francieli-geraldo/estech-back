package br.com.scsoftware.afinese.infrastructure.common.jsonapi.business;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JsonApiError {
    private String code;
    private String title;
    private String detail;
    private List<String> fields;
}
