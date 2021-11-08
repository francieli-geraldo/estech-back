package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.repository.ProgramRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.ProgramService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
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
    public Long create(Program program) {
        return save(program).getId();
    }

    @Override
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
    public Page<Program> getAllRecords(Pageable pageRequest, String search) {
        return programRepository.findAllByTenantIdAndActiveTrueAndNameContaining(pageRequest,
                UserServiceImpl.getTenantIdAuthenticatedUser(), search == null ? "" : search);
    }
}
