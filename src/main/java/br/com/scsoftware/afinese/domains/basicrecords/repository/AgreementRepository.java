package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends BaseRepository<Agreement> {

    @Query(value = "SELECT a FROM agreement a WHERE (:patientId is null or a.patient.id = :patientId) " +
            "and (:programId is null or a.program.id = :programId) " +
            "and (:status is null or a.status = :status) ")
    Page<Agreement> findByPatientId(final Long patientId, final Long programId,
                                    final StatusAgreement status, final Pageable pageRequest);
}
