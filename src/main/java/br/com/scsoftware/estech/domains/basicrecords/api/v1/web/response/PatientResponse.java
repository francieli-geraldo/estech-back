package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.estech.domains.basicrecords.enums.Sex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PatientResponse {
    private Long id;
    private String name;
    private Sex sex;
    private LocalDate birthDate;
    private String email;
    private String phone;
}
