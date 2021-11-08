package br.com.scsoftware.afinese.infrastructure.common.jsonapi.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DetailError implements Serializable {

    private final String resource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String field;

    private final String message;

    private final String code;

    public DetailError(String resource, String field, String message, String code) {
        this.resource = resource;
        this.field = field;
        this.message = message;
        this.code = code;
    }
}
