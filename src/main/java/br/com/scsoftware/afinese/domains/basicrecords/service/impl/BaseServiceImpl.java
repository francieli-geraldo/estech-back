package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.service.BaseService;
import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
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
        return repository.findAllByTenantIdAndActiveTrue(pageRequest, UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public Optional<T> getRecord(final Long id) {
        return repository.findByIdAndTenantId(id, UserServiceImpl.getTenantIdAuthenticatedUser());
    }

    @Override
    public boolean delete(Long id) {
        T entBD = getRecord(id).orElseThrow(() -> ResourceNotFoundException.of());

        if (!entBD.isActive()) {
            throw ResourceNotFoundException.of();
        }

        entBD = beforeDelete(entBD);

        entBD.invalidate();
        repository.save(entBD);
        return true;
    }

    @Override
    public T beforeDelete(T ent) {
        return ent;
    }

}
