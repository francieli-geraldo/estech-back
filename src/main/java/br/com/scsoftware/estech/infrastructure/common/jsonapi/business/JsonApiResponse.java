package br.com.scsoftware.estech.infrastructure.common.jsonapi.business;

import lombok.Builder;
import lombok.Data;

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
