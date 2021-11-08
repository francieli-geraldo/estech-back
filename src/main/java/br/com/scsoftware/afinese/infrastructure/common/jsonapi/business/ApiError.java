package br.com.scsoftware.afinese.infrastructure.common.jsonapi.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ApiError implements Serializable {

    private final long timestamp;

    private final String date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object data;

    private final Object details;

    public ApiError(Object data, Object details) {
        this.timestamp = System.currentTimeMillis();
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        this.status = "error";
        this.data = data;
        this.details = details;
    }
}
