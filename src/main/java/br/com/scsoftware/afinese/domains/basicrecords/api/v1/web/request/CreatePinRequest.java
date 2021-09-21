package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode
public class CreatePinRequest {

    private @NotBlank String encryptedPin;
}
