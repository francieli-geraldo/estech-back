package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Agreement;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.repository.GroupRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.GroupService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    public GroupServiceImpl(final GroupRepository repository) {
        super(repository);
    }

    @Override
    public Long create(Group group) {
        return save(group).getId();
    }

    @Override
    public Group update(Group group) {
        return save(group);
    }

    private Group save(Group group) {
        try {
            return repository.save(group);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(ex.getMessage(), "Já existe um grupo com este nome.");
        }
    }
}
