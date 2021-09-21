package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.GroupResponse;
import br.com.scsoftware.afinese.domains.basicrecords.converter.GroupConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.GroupServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/groups")
@Transactional(rollbackFor = Exception.class)
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping
    public ResponseEntity<Page<GroupResponse>> getAll(@PageableDefault final Pageable page) {
        final Page<GroupResponse> groupList = groupService.getAllRecords(page)
                .map(GroupConverter::toDTO);

        if (groupList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(groupList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getRecord(@PathVariable final Long id) {
        Optional<Group> group = groupService.getRecord(id);
        if (group.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(GroupConverter.toDTO(group.get()));
    }
}
