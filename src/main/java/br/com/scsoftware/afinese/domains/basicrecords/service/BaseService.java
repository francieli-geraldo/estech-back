package br.com.scsoftware.afinese.domains.basicrecords.service;

import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    Page<T> getAllRecords(final Long tenantId, final Pageable pageRequest);

    Optional<T> getRecord(final Long id);

    boolean delete(final Long id);

    T beforeDelete(T ent);

    T afterDelete(T ent);

}
