package br.com.scsoftware.estech.domains.basicrecords.converter;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.CreateGroup;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.UpdateGroup;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.GroupResponse;
import br.com.scsoftware.estech.domains.basicrecords.entity.Group;

public class GroupConverter {
    public static GroupResponse toDTO(Group group) {
        return GroupResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .description(group.getDescription())
            .build();
    }

    public static Group toEntity(CreateGroup group) {
        Group ent = new Group();
        ent.setName(group.getName());
        ent.setDescription(group.getDescription());

        return ent;
    }

    public static Group toEntity(Group ent, UpdateGroup updateGroup) {
        ent.setName(updateGroup.getName());
        ent.setDescription(updateGroup.getDescription());

        return ent;
    }
}
