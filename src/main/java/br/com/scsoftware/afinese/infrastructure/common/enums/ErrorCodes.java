package br.com.scsoftware.afinese.infrastructure.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    REQUEST_VALIDATION_ERROR("REQUEST_VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation Error"),
    DATABASE_ERROR("DATABASE_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Database Error"),
    BUSINESS_ERROR("BUSINESS_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Business Error"),
    INTEGRATION_ERROR("INTEGRATION_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Integration Error"),
    INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND, "Resource Not Found"),
    AUTHORIZATION_ERROR("AUTHORIZATION_ERROR", HttpStatus.UNAUTHORIZED, "Authorization Error"),
    FORBIDDEN_ERROR("FORBIDDEN_ERROR", HttpStatus.UNAUTHORIZED, "Forbidden Error"),
    RSA_ERROR("RSA_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "RSA Error"),
    CONFLICT_ERROR("CONFLICT_ERROR", HttpStatus.CONFLICT, "Conflict");

    private final String value;
    private final HttpStatus httpStatus;
    private final String title;

    @JsonValue
    public String getValue() {
        return value;
    }
}