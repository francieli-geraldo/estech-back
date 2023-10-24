package br.com.scsoftware.estech.domains.basicrecords.service.impl;

import br.com.scsoftware.estech.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.estech.domains.basicrecords.entity.Program;
import br.com.scsoftware.estech.domains.basicrecords.repository.ProgramRepository;
import br.com.scsoftware.estech.domains.basicrecords.service.ProgramService;
import br.com.scsoftware.estech.infrastructure.common.exception.ConflictException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        var programBD = programRepository.findOneByTenantIdAndActiveTrueAndName(UserServiceImpl.getTenantIdAuthenticatedUser(), program.getName());

        programBD.ifPresent(e -> {
            if (Objects.isNull(program.getId()) || (Objects.nonNull(program.getId()) && program.getId().compareTo(e.getId()) != 0)) {
                throw new ConflictException("Já existe um programa com este nome.", "Já existe um programa com este nome.");
            }
        });

        return repository.save(program);
    }

    @Override
    @Cacheable("programs.id")
    public Optional<Program> getRecord(Long id) {
        return super.getRecord(id);
    }

    @Override
    @Cacheable("programs.all")
    public Page<Program> getAllRecords(final Long tenantId, Pageable pageRequest, String search) {
        return programRepository.findAllByTenantIdAndActiveTrueAndNameContaining(pageRequest,
                tenantId, search == null ? "" : search);
    }
}
