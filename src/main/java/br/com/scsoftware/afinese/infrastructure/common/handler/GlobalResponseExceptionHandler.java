package br.com.scsoftware.afinese.infrastructure.common.handler;

import br.com.scsoftware.afinese.infrastructure.common.jsonapi.business.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RestController
@Slf4j
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerException(final Exception ex, final WebRequest request) {
        HttpStatus httpStatus = ex.getClass().getAnnotation(ResponseStatus.class).value();
        if (httpStatus == null)
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), httpStatus);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(final Exception ex, final WebRequest request) {

        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {
        String resultStr = "";
        for (final ObjectError error : ex.getBindingResult().getAllErrors())
            if (error instanceof FieldError)
                resultStr = ((FieldError) error).getField() + ": " + error.getDefaultMessage() + "\n";
            else
                resultStr = error.toString();

        return new ResponseEntity<>(new ExceptionResponse(resultStr), status);
    }
}
