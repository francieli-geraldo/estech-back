package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.AgreementResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.UpdateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;

public class AgreementConverter {
    public static AgreementResponse toDTO(Agreement agreement) {
        return AgreementResponse.builder()
                .id(agreement.getId())
                .patient(PatientConverter.toSummarizedPatientResponse(agreement.getPatient()))
                .program(ProgramConverter.toDTO(agreement.getProgram()))
                .group(GroupConverter.toDTO(agreement.getGroup()))
                .status(agreement.getStatus())
                .startingWeight(agreement.getStartingWeight())
                .goal(agreement.getGoal())
                .hiringDate(agreement.getHiringDate())
                .startDate(agreement.getStartDate())
                .dateConclusion(agreement.getDateConclusion())
                .cancellationDate(agreement.getCancellationDate())
                .reasonCancellation(agreement.getReasonCancellation())
                .notes(agreement.getNotes())
                .build();
    }

    public static CreateAgreementBO toBO(CreateAgreement agreement) {
        return CreateAgreementBO.builder()
                .patientId(agreement.getPatientId())
                .programId(agreement.getProgramId())
                .groupId(agreement.getGroupId())
                .startingWeight(agreement.getStartingWeight())
                .goal(agreement.getGoal())
                .hiringDate(agreement.getHiringDate())
                .startDate(agreement.getStartDate())
                .notes(agreement.getNotes())
                .build();
    }

    public static CreateAgreementBO toCreateBO(Agreement agreement) {
        return CreateAgreementBO.builder()
                .id(agreement.getId())
                .programId(agreement.getProgram().getId())
                .groupId(agreement.getGroup().getId())
                .status(agreement.getStatus())
                .startingWeight(agreement.getStartingWeight())
                .goal(agreement.getGoal())
                .hiringDate(agreement.getHiringDate())
                .startDate(agreement.getStartDate())
                .dateConclusion(agreement.getDateConclusion())
                .cancellationDate(agreement.getCancellationDate())
                .reasonCancellation(agreement.getReasonCancellation())
                .notes(agreement.getNotes())
                .build();
    }

    public static UpdateAgreementBO toUpdateBO(Agreement agreement) {
        return UpdateAgreementBO.builder()
                .id(agreement.getId())
                .programId(agreement.getProgram().getId())
                .groupId(agreement.getGroup().getId())
                .status(agreement.getStatus())
                .startingWeight(agreement.getStartingWeight())
                .goal(agreement.getGoal())
                .hiringDate(agreement.getHiringDate())
                .startDate(agreement.getStartDate())
                .dateConclusion(agreement.getDateConclusion())
                .cancellationDate(agreement.getCancellationDate())
                .reasonCancellation(agreement.getReasonCancellation())
                .notes(agreement.getNotes())
                .build();
    }

    public static UpdateAgreementBO toBO(UpdateAgreement agreement) {
        return UpdateAgreementBO.builder()
                .programId(agreement.getProgramId())
                .groupId(agreement.getGroupId())
                .status(agreement.getStatus())
                .startingWeight(agreement.getStartingWeight())
                .goal(agreement.getGoal())
                .hiringDate(agreement.getHiringDate())
                .startDate(agreement.getStartDate())
                .dateConclusion(agreement.getDateConclusion())
                .cancellationDate(agreement.getCancellationDate())
                .reasonCancellation(agreement.getReasonCancellation())
                .notes(agreement.getNotes())
                .build();
    }

    public static Agreement fromBO(CreateAgreementBO agreement) {
        Agreement agreementEnt = new Agreement();
        agreementEnt.setId(agreement.getId());
        agreementEnt.setStatus(agreement.getStatus());
        agreementEnt.setStartingWeight(agreement.getStartingWeight());
        agreementEnt.setGoal(agreement.getGoal());
        agreementEnt.setHiringDate(agreement.getHiringDate());
        agreementEnt.setStartDate(agreement.getStartDate());
        agreementEnt.setDateConclusion(agreement.getDateConclusion());
        agreementEnt.setCancellationDate(agreement.getCancellationDate());
        agreementEnt.setReasonCancellation(agreement.getReasonCancellation());
        agreementEnt.setNotes(agreement.getNotes());

        return agreementEnt;
    }

    public static Agreement fromBO(UpdateAgreementBO agreement, Agreement agreementEnt) {
        agreementEnt.setStatus(agreement.getStatus());
        agreementEnt.setStartingWeight(agreement.getStartingWeight());
        agreementEnt.setGoal(agreement.getGoal());
        agreementEnt.setHiringDate(agreement.getHiringDate());
        agreementEnt.setStartDate(agreement.getStartDate());
        agreementEnt.setDateConclusion(agreement.getDateConclusion());
        agreementEnt.setCancellationDate(agreement.getCancellationDate());
        agreementEnt.setReasonCancellation(agreement.getReasonCancellation());
        agreementEnt.setNotes(agreement.getNotes());

        return agreementEnt;
    }
}
