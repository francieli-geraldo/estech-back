package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AgreementRepository extends BaseRepository<Agreement> {

    @Query(value = "SELECT a FROM agreement a WHERE a.tenantId = :tenantId " +
            "and (:patientId is null or a.patient.id = :patientId) " +
            "and (:programId is null or a.program.id = :programId) " +
            "and (:status is null or a.status = :status) " +
            "and a.active = true ")
    Page<Agreement> findAll(final Long patientId, final Long programId,
                            final StatusAgreement status, final Long tenantId, final Pageable pageRequest);

    @Query(value = "SELECT a FROM agreement a WHERE a.tenantId = :tenantId " +
            "and (:patientId is null or a.patient.id = :patientId) " +
            "and (:programId is null or a.program.id = :programId) " +
            "and (:status is null or a.status = :status) " +
            "and a.active = true " +
            "and DATEDIFF(a.hiringDate, :today) < :daysOfOverdue")
    Page<Agreement> findAll(final Long patientId, final Long programId,
                            final StatusAgreement status, final Long tenantId,
                            final Integer daysOfOverdue, final LocalDate today, final Pageable pageRequest);
}
