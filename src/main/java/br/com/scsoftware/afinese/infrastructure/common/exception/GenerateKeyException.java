package br.com.scsoftware.afinese.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateKeyException extends RuntimeException{
    public GenerateKeyException(String message) {
        super(message);
    }
}
