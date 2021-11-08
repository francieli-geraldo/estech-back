package br.com.scsoftware.afinese.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BusinessException{
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, String publicMessage) {
        super(message, publicMessage);
    }
}
