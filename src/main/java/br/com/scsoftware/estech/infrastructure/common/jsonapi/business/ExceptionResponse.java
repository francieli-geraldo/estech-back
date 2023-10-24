package br.com.scsoftware.estech.infrastructure.common.jsonapi.business;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ExceptionResponse {
    private final String message;

    public ExceptionResponse(final String message) {
        this.message = message;
    }
}
