package br.com.scsoftware.afinese.domains.basicrecords.service;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService extends BaseService<Program> {

    Long create(Program program);

    Program update(Program program);

    Page<Program> getAllRecords(final Pageable pageRequest, final String search);
}
