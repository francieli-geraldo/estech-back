package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreatePatient;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdatePatient;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.PatientResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummarizedPatientResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.UpdatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Patient;

public class PatientConverter {
    public static PatientResponse toDTO(Patient patient) {
        return PatientResponse.builder()
            .id(patient.getId())
            .name(patient.getName())
            .sex(patient.getSex())
            .birthDate(patient.getBirthDate())
            .email(patient.getEmail())
            .phone(patient.getPhone())
            .build();
    }

    public static SummarizedPatientResponse toSummarizedPatientResponse(Patient patient) {
        return SummarizedPatientResponse.builder()
            .id(patient.getId())
            .name(patient.getName())
            .phone(patient.getPhone())
            .build();
    }

    public static CreatePatientBO toBO(CreatePatient patient) {
        return CreatePatientBO.builder()
            .name(patient.getName())
            .sex(patient.getSex())
            .birthDate(patient.getBirthDate())
            .email(patient.getEmail())
            .phone(patient.getPhone())
            .build();
    }

    public static CreatePatientBO toBO(Patient patient) {
        return CreatePatientBO.builder()
            .id(patient.getId())
            .name(patient.getName())
            .sex(patient.getSex())
            .birthDate(patient.getBirthDate())
            .email(patient.getEmail())
            .phone(patient.getPhone())
            .build();
    }

    public static UpdatePatientBO toBO(Long id, UpdatePatient patient) {
        return UpdatePatientBO.builder()
                .id(id)
                .name(patient.getName())
                .sex(patient.getSex())
                .birthDate(patient.getBirthDate())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .build();
    }

    public static UpdatePatientBO toUpdateBO(Patient patient) {
        return UpdatePatientBO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .sex(patient.getSex())
                .birthDate(patient.getBirthDate())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .build();
    }

    public static Patient fromBO(CreatePatientBO patient) {
        Patient patientEnt = new Patient();
        patientEnt.setId(patient.getId());
        patientEnt.setName(patient.getName());
        patientEnt.setSex(patient.getSex());
        patientEnt.setBirthDate(patient.getBirthDate());
        patientEnt.setEmail(patient.getEmail());
        patientEnt.setPhone(patient.getPhone());

        return patientEnt;
    }

    public static Patient fromBO(Patient patientEnt, UpdatePatientBO patient) {
        patientEnt.setId(patient.getId());
        patientEnt.setName(patient.getName());
        patientEnt.setSex(patient.getSex());
        patientEnt.setBirthDate(patient.getBirthDate());
        patientEnt.setEmail(patient.getEmail());
        patientEnt.setPhone(patient.getPhone());

        return patientEnt;
    }
}
