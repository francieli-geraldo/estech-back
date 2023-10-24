package br.com.scsoftware.estech.domains.basicrecords.service;

import br.com.scsoftware.estech.domains.basicrecords.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService extends BaseService<Program> {

    Long create(Program program);

    Program update(Program program);

    Page<Program> getAllRecords(final Long tenantId, final Pageable pageRequest, final String search);
}
