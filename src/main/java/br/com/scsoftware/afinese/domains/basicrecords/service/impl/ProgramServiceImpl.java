package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import br.com.scsoftware.afinese.domains.basicrecords.repository.ProgramRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.ProgramService;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl extends BaseServiceImpl<Program> implements ProgramService {

    public ProgramServiceImpl(final ProgramRepository repository) {
        super(repository);
    }
}
