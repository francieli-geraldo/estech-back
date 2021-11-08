package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateProgram {
    @NotBlank
    private String name;
    private String description;
}
