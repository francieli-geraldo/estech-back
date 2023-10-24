package br.com.scsoftware.estech.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddentException extends BusinessException {
    public ForbiddentException(String message) {
        super(message);
    }
}
