package br.com.scsoftware.estech.domains.basicrecords.service;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.AgreementProgramMonitoringResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.estech.domains.basicrecords.business.UpdateAgreementBO;
import br.com.scsoftware.estech.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.estech.domains.basicrecords.enums.StatusAgreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgreementService {

    Page<Agreement> getAllRecords(Long patientId, Long programId, StatusAgreement status, Pageable pageRequest);

    Optional<Agreement> getRecord(Long agreementId);

    CreateAgreementBO create(CreateAgreementBO agreement);

    UpdateAgreementBO update(UpdateAgreementBO agreement, Long agreementId);

    AgreementProgramMonitoringResponse getProgramMonitoring(Long agreementId);
}
