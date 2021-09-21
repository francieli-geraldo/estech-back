package br.com.scsoftware.afinese.domains.basicrecords.service;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummarizedPatientResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService extends BaseService<Patient> {


    Page<Patient> getAllRecords(String search, Pageable pageRequest);

    CreatePatientBO create(CreatePatientBO patient);

    Page<SummarizedPatientResponse> getByNameOrPhone(String search, Pageable pageRequest);

}
