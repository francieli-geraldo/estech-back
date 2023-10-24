package br.com.scsoftware.estech.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateKeyException extends BusinessException{
    public GenerateKeyException(String message) {
        super(message);
    }
}
