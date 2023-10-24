package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class UpdateProgram {
    @NotBlank
    private String name;
    private String description;
}
