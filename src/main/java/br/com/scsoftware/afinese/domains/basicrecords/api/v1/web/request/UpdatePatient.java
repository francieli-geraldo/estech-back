package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.afinese.domains.basicrecords.enums.Sex;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdatePatient {
    @NotBlank
    private String name;
    @NotNull
    private Sex sex;
    private LocalDate birthDate;
    @Email
    private String email;
    private String phone;
}
