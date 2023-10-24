package br.com.scsoftware.estech.domains.basicrecords.repository;

import br.com.scsoftware.estech.domains.basicrecords.entity.Program;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends BaseRepository<Program> {
    Optional<Program> findOneByTenantIdAndActiveTrueAndName(Long tenantId, String name);

    Page<Program> findAllByTenantIdAndActiveTrueAndNameContaining(Pageable pageable, Long tenantId, String name);
}
