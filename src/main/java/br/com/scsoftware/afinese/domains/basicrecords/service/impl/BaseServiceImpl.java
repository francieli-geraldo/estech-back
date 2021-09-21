package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.service.BaseService;
import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected final BaseRepository<T> repository;

    public BaseServiceImpl(final BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Page<T> getAllRecords(final Pageable pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public Optional<T> getRecord(final Long id) {
        return repository.findById(id);
    }
}
