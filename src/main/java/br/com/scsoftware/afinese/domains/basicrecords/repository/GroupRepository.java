package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends BaseRepository<Group> {

    Page<Group> findAllByTenantIdAndActiveTrueAndNameContaining(Pageable pageable, Long tenantId, String name);
}
