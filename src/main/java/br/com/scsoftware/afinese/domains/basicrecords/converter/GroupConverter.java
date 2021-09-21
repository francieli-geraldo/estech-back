package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.GroupResponse;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;

public class GroupConverter {
    public static GroupResponse toDTO(Group group) {
        return GroupResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .description(group.getDescription())
            .build();
    }
}
