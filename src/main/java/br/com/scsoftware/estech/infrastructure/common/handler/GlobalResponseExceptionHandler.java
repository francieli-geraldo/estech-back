package br.com.scsoftware.estech.infrastructure.common.handler;

import br.com.scsoftware.estech.infrastructure.common.enums.ErrorCodes;
import br.com.scsoftware.estech.infrastructure.common.exception.BusinessException;
import br.com.scsoftware.estech.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.estech.infrastructure.common.exception.ForbiddentException;
import br.com.scsoftware.estech.infrastructure.common.exception.UnauthorizedException;
import br.com.scsoftware.estech.infrastructure.common.jsonapi.business.JsonApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
@RestController
@Slf4j
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE = "Catching {} from request {}: {}.";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return extractBindExceptionErrors(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return createResponseEntity(ex, ErrorCodes.REQUEST_VALIDATION_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataNotFoundException(final DataAccessException ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage(), ex);
        return createResponseEntity(ex, ErrorCodes.DATABASE_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(final BusinessException ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return createResponseEntity(ex, ErrorCodes.BUSINESS_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(final Exception ex, final WebRequest request) {
        final ConflictException conflict = new ConflictException(ex.getMessage());
        log.error(ERROR_MESSAGE, conflict.getClass().getName(), request.getDescription(false), conflict.getMessage(), conflict);
        return createResponseEntity(conflict, ErrorCodes.CONFLICT_ERROR);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(final Exception ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage(), ex);
        return createResponseEntity(ex, ErrorCodes.CONFLICT_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledException(final Exception ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage(), ex);
        return createResponseEntity(ex, ErrorCodes.INTERNAL_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return createResponseEntity(ex, ErrorCodes.FORBIDDEN_ERROR);
    }

    @ExceptionHandler(ForbiddentException.class)
    public final ResponseEntity<Object> handleForbiddentException(final Exception ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return createResponseEntity(ex, ErrorCodes.FORBIDDEN_ERROR);
    }

    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class})
    public final ResponseEntity<Object> handleUnauthorizedException(final Exception ex, final WebRequest request) {
        log.error(ERROR_MESSAGE, ex.getClass().getName(), request.getDescription(false), ex.getMessage());
        return createResponseEntity(ex, ErrorCodes.AUTHORIZATION_ERROR);
    }

    private ResponseEntity<Object> createResponseEntity(final Exception ex, ErrorCodes errorCode) {
        if (errorCode == null) {
            log.warn("CreateResponseEntity called without an ErrorCode with Exception {}.", ex.getClass().getSimpleName());
            errorCode = ErrorCodes.INTERNAL_ERROR;
        }

        return ResponseEntity
                .status(extractHttpStatus(ex, errorCode))
                .body(
                        JsonApiError.builder()
                                .code(errorCode.getValue())
                                .title(errorCode.getTitle())
                                .detail(extractPublicMessage(ex, errorCode))
                                .build()
                );
    }

    private HttpStatus extractHttpStatus(final Exception ex, final ErrorCodes errorCode) {
        HttpStatus httpStatus = errorCode.getHttpStatus();
        if (ex != null && ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            httpStatus = ex.getClass().getAnnotation(ResponseStatus.class).value();
        }

        return httpStatus;
    }

    private String extractPublicMessage(final Exception ex, final ErrorCodes errorCode) {
        String publicMessage = errorCode.getTitle();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            if (!Objects.isNull(businessException.getPublicMessage())) {
                publicMessage = businessException.getPublicMessage();
            }
        }

        return publicMessage;
    }

    private ResponseEntity<Object> extractBindExceptionErrors(final BindException ex) {
        final List<JsonApiError> apiErrors = new ArrayList<>();

        apiErrors.addAll(extractFieldErrors(ex));
        apiErrors.addAll(extractGlobalErrors(ex));

        return ResponseEntity.badRequest().body(apiErrors);
    }

    private List<JsonApiError> extractGlobalErrors(final BindException ex) {
        List<JsonApiError> apiErrors = new ArrayList<>();
        List<ObjectError> errors = ex.getGlobalErrors();

        errors.forEach(objectError -> apiErrors.add(
                JsonApiError.builder()
                        .code(objectError.getCode())
                        .title(ErrorCodes.REQUEST_VALIDATION_ERROR.getValue())
                        .detail(objectError.getDefaultMessage())
                        .build()
        ));

        return apiErrors;
    }

    private List<JsonApiError> extractFieldErrors(final BindException ex) {
        final Map<String, List<String>> fieldsByCodes = new HashMap<>();
        final Map<String, String> messageByCodes = new HashMap<>();
        final List<JsonApiError> apiErrors = new ArrayList<>();

        ex.getFieldErrors().forEach(fieldError -> {
            final String code = fieldError.getCode();

            if (!fieldsByCodes.containsKey(code)) {
                fieldsByCodes.put(code, new ArrayList<>());
            }

            if (!messageByCodes.containsKey(code)) {
                messageByCodes.put(code, fieldError.getDefaultMessage());
            }

            fieldsByCodes.get(code).add(fieldError.getField());
        });

        fieldsByCodes.forEach((code, fieldList) -> apiErrors.add(
                JsonApiError.builder()
                        .code(code)
                        .title(ErrorCodes.REQUEST_VALIDATION_ERROR.getValue())
                        .detail(messageByCodes.getOrDefault(code, code))
                        .fields(fieldList)
                        .build()
        ));

        return apiErrors;
    }
}
