package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Patient;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends BaseRepository<Patient> {

    @Query("select p from patient p where p.tenantId = :tenantId and p.active = true and " +
        "(:name is null or p.name like %:name%) and "+
        "(:phone is null or p.phone like %:phone%)")
    Page<Patient> findByNameOptionalContainingAndPhoneOptionalContaining(String name, String phone, Long tenantId, Pageable pageRequest);
    
}
