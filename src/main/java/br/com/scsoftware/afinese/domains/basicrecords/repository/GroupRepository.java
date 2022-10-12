package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends BaseRepository<Group> {

    Optional<Group> findOneByTenantIdAndActiveTrueAndName(Long tenantId, String name);
    Page<Group> findAllByTenantIdAndActiveTrueAndNameContaining(Pageable pageable, Long tenantId, String name);
}
