package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends BaseRepository<Program> {

    Page<Program> findAllByTenantIdAndActiveTrueAndNameContaining(Pageable pageable, Long tenantId, String name);
}
