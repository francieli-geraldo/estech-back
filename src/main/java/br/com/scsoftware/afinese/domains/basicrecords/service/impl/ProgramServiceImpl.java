package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.repository.ProgramRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.ProgramService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgramServiceImpl extends BaseServiceImpl<Program> implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(final ProgramRepository repository) {
        super(repository);
        this.programRepository = repository;
    }

    @Override
    @CacheEvict(cacheNames = {"programs.all"}, allEntries = true)
    public Long create(Program program) {
        return save(program).getId();
    }

    @Override
    @CacheEvict(cacheNames = {"programs.all", "programs.id"}, allEntries = true)
    public Program update(Program program) {
        return save(program);
    }

    private Program save(Program program) {
        try {
            return repository.save(program);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(ex.getMessage(), "Já existe um programa com este nome.");
        }
    }

    @Override
    @Cacheable("programs.id")
    public Optional<Program> getRecord(Long id) {
        return super.getRecord(id);
    }

    @Override
    @Cacheable("programs.all")
    public Page<Program> getAllRecords(Pageable pageRequest, String search) {
        return programRepository.findAllByTenantIdAndActiveTrueAndNameContaining(pageRequest,
                UserServiceImpl.getTenantIdAuthenticatedUser(), search == null ? "" : search);
    }
}
