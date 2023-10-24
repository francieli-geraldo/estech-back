package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.estech.domains.basicrecords.enums.Sex;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreatePatient {
    @NotBlank
    private String name;
    @NotNull
    private Sex sex;
    private LocalDate birthDate;
    @Email
    private String email;
    private String phone;
}
