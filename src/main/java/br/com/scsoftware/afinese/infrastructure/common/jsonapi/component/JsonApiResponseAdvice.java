package br.com.scsoftware.afinese.infrastructure.common.jsonapi.component;

import br.com.scsoftware.afinese.infrastructure.common.jsonapi.business.JsonApiError;
import br.com.scsoftware.afinese.infrastructure.common.jsonapi.business.JsonApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class JsonApiResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if (!isSupported(response)) {
            log.warn("Response is not supported by controller advice - instanceof: {}", response.getClass().getName());
            return body;
        }

        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();

        HttpStatus responseType = HttpStatus.resolve(httpServletResponse.getStatus());
        if (responseType == null) {
            log.warn("Response do not contain a valid status code: {}", httpServletResponse.getStatus());
            return body;
        }

        switch (responseType.series()) {
            case SUCCESSFUL:
                return parseSuccessfulResponse(body);
            case CLIENT_ERROR:
                return parseClientErrorResponse(body);
            default:
                return parseInternalErrorResponse(response);
        }
    }

    private JsonApiResponse parseSuccessfulResponse(Object body) {
        JsonApiResponse jsonApiResponse = JsonApiResponse.builder().build();

        if (body instanceof Page<?>) {
            Page<?> page = (Page<?>) body;

            parsePageInformation(jsonApiResponse, page);
            jsonApiResponse.setData(page.getContent());

            return jsonApiResponse;
        }

        jsonApiResponse.setData(body);

        return jsonApiResponse;
    }

    private void parsePageInformation(JsonApiResponse jsonApiResponse, Page<?> page) {
        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("elements", page.getTotalElements());
        pageInfo.put("pages", page.getTotalPages());
        pageInfo.put("number", page.getNumber());
        pageInfo.put("size", page.getSize());
        pageInfo.put("first", page.isFirst());
        pageInfo.put("last", page.isLast());

        if (jsonApiResponse.getMeta() == null)
            jsonApiResponse.setMeta(new HashMap<>());

        jsonApiResponse.getMeta().put("page", pageInfo);
    }

    private JsonApiResponse parseInternalErrorResponse(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        return JsonApiResponse.builder()
            .errors(
                List.of(
                    JsonApiError.builder()
                        .title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .detail("Please contact API operator")
                        .build()
                )
            )
            .build();
    }

    private JsonApiResponse parseClientErrorResponse(Object body) {
        List<JsonApiError> apiErrors = new ArrayList<>();

        if (body instanceof JsonApiError) {
            apiErrors.add((JsonApiError) body);
        } else if (body instanceof List<?>) {
            List<?> list = (List<?>) body;
            list.forEach(e -> {
                if (e instanceof JsonApiError)
                    apiErrors.add((JsonApiError) e);
            });
        }

        return JsonApiResponse.builder()
            .errors(apiErrors)
            .build();
    }

    private boolean isSupported(ServerHttpResponse response) {
        return response instanceof ServletServerHttpResponse;
    }
}
