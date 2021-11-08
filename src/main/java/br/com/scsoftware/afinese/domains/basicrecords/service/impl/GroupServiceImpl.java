package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.repository.GroupRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.GroupService;
import br.com.scsoftware.afinese.infrastructure.common.exception.ConflictException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(final GroupRepository repository) {
        super(repository);
        groupRepository = repository;
    }

    @Override
    public Long create(Group group) {
        return save(group).getId();
    }

    @Override
    public Group update(Group group) {
        return save(group);
    }

    @Override
    public Page<Group> getAllRecords(Pageable pageRequest, String search) {
        return groupRepository.findAllByTenantIdAndActiveTrueAndNameContaining(pageRequest,
                UserServiceImpl.getTenantIdAuthenticatedUser(), search == null ? "" : search);
    }

    private Group save(Group group) {
        try {
            return repository.save(group);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(ex.getMessage(), "Já existe um grupo com este nome.");
        }
    }
}
