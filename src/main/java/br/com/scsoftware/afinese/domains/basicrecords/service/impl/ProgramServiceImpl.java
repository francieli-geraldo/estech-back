package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.repository.ProgramRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.ProgramService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl extends BaseServiceImpl<Program> implements ProgramService {

    public ProgramServiceImpl(final ProgramRepository repository) {
        super(repository);
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
}
