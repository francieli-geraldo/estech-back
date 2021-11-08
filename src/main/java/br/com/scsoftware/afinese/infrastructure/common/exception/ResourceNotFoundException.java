package br.com.scsoftware.afinese.infrastructure.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BusinessException {
    private ResourceNotFoundException() {   }

    public static ResourceNotFoundException of() {
        return new ResourceNotFoundException();
    }
}
