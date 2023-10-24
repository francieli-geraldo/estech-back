package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode
public class CreatePinRequest {

    private @NotBlank String encryptedPin;
}
