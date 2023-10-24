package br.com.scsoftware.estech.domains.basicrecords.business;

import br.com.scsoftware.estech.domains.basicrecords.enums.Sex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UpdatePatientBO {
    private Long id;
    private String name;
    private Sex sex;
    private LocalDate birthDate;
    private String email;
    private String phone;
}
