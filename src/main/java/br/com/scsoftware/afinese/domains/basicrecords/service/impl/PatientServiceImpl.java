package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummarizedPatientResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.PatientConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Patient;
import br.com.scsoftware.afinese.domains.basicrecords.repository.PatientRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends BaseServiceImpl<Patient> implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(final PatientRepository repository) {
        super(repository);
        this.patientRepository = repository;
    }

    @Override
    public CreatePatientBO create(final CreatePatientBO patient) {
        return PatientConverter.toBO(repository.save(PatientConverter.fromBO(patient)));
    }

    @Override
    public Page<Patient> getAllRecords(final String search, final Pageable pageRequest) {
        String name = null;
        String phone = null;

        if (search != null) {
            if (search.matches("\\d+"))
                phone = search;
            else
                name = search;
        }

        return patientRepository.findByNameOptionalContainingAndPhoneOptionalContaining(name, phone, pageRequest);
    }

    @Override
    public Page<SummarizedPatientResponse> getByNameOrPhone(final String search, final Pageable pageRequest) {
        return getAllRecords(search, pageRequest)
                .map(PatientConverter::toSummarizedPatientResponse);
    }

}
