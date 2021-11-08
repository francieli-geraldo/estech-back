package br.com.scsoftware.afinese.domains.basicrecords.service;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService extends BaseService<Group> {

    Long create(Group group);

    Group update(Group group);

    Page<Group> getAllRecords(final Pageable pageRequest, final String search);
}
