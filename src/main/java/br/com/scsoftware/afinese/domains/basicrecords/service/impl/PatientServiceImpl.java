package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummarizedPatientResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.business.UpdatePatientBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.PatientConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Patient;
import br.com.scsoftware.afinese.domains.basicrecords.repository.PatientRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.AgreementService;
import br.com.scsoftware.afinese.domains.basicrecords.service.PatientService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl extends BaseServiceImpl<Patient> implements PatientService {

    private final PatientRepository patientRepository;
    @Autowired private AgreementService agreementService;

    public PatientServiceImpl(final PatientRepository repository) {
        super(repository);
        this.patientRepository = repository;
    }

    @Override
    public CreatePatientBO create(final CreatePatientBO patient) {
        return PatientConverter.toBO(save(PatientConverter.fromBO(patient)));
    }

    @Override
    public UpdatePatientBO update(UpdatePatientBO patient) {
        return PatientConverter.toUpdateBO(save(PatientConverter.fromBO(getRecord(patient.getId())
                .orElseThrow(() -> ResourceNotFoundException.of()), patient)));
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

        return patientRepository.findByNameOptionalContainingAndPhoneOptionalContaining(name, phone,
                UserServiceImpl.getTenantIdAuthenticatedUser(), pageRequest);
    }

    @Override
    public Page<SummarizedPatientResponse> getByNameOrPhone(final String search, final Pageable pageRequest) {
        return getAllRecords(search, pageRequest)
                .map(PatientConverter::toSummarizedPatientResponse);
    }

    @Override
    public Patient beforeDelete(Patient ent) {
        if (!agreementService.getAllRecords(ent.getId(), null, null, null).isEmpty()) {
            String msgErro = "Não é possível excluir este paciente, pois o mesmo já possui contratos vinculados a ele.";
            throw new ConflictException(msgErro, msgErro);
        }
        return super.beforeDelete(ent);
    }

    private Patient save(Patient patient) {
        try {
            return repository.save(patient);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(ex.getMessage(), "Já existe um paciente com este nome.");
        }
    }

}
