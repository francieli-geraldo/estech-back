package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.repository.GroupRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    public GroupServiceImpl(final GroupRepository repository) {
        super(repository);
    }
}
