package br.com.scsoftware.estech.domains.basicrecords.service.impl;

import br.com.scsoftware.estech.domains.auth.service.impl.UserServiceImpl;
import br.com.scsoftware.estech.domains.basicrecords.entity.Group;
import br.com.scsoftware.estech.domains.basicrecords.repository.GroupRepository;
import br.com.scsoftware.estech.domains.basicrecords.service.GroupService;
import br.com.scsoftware.estech.infrastructure.common.exception.ConflictException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(final GroupRepository repository) {
        super(repository);
        groupRepository = repository;
    }

    @Override
    @CacheEvict(cacheNames = {"groups.all"}, allEntries = true)
    public Long create(Group group) {
        return save(group).getId();
    }

    @Override
    @CacheEvict(cacheNames = {"groups.all", "groups.id"}, allEntries = true)
    public Group update(Group group) {
        return save(group);
    }

    @Override
    @Cacheable("groups.id")
    public Optional<Group> getRecord(Long id) {
        return super.getRecord(id);
    }

    @Override
    @Cacheable("groups.all")
    public Page<Group> getAllRecords(final Long tenantId, Pageable pageRequest, String search) {
        return groupRepository.findAllByTenantIdAndActiveTrueAndNameContaining(pageRequest,
                tenantId, search == null ? "" : search);
    }

    private Group save(Group group) {
        var groupBD = groupRepository.findOneByTenantIdAndActiveTrueAndName(UserServiceImpl.getTenantIdAuthenticatedUser(), group.getName());

        groupBD.ifPresent(e -> {
            if (Objects.isNull(group.getId()) || (Objects.nonNull(group.getId()) && group.getId().compareTo(e.getId()) != 0)) {
                throw new ConflictException("Já existe um grupo com este nome.", "Já existe um grupo com este nome.");
            }
        });

            return repository.save(group);
    }
}
