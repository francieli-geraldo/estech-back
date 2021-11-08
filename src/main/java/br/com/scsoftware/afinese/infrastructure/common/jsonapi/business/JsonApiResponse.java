package br.com.scsoftware.afinese.infrastructure.common.jsonapi.business;

import lombok.Data;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class JsonApiResponse {
    private Object data;
    private Map<String, Object> meta;
    private List<JsonApiError> errors;
    private Map<String, String> links;
}
