package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.CreateGroup;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request.UpdateGroup;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.GroupResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.CreateAgreementBO;
import br.com.scsoftware.afinese.domains.basicrecords.converter.AgreementConverter;
import br.com.scsoftware.afinese.domains.basicrecords.converter.GroupConverter;
import br.com.scsoftware.afinese.domains.basicrecords.entity.Group;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.GroupServiceImpl;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/groups")
@Transactional(rollbackFor = Exception.class)
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping
    public ResponseEntity<Page<GroupResponse>> getAll(@PageableDefault(sort="name") final Pageable page) {
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

    @PostMapping
    public ResponseEntity postRecord(@Valid @RequestBody CreateGroup createGroup) {
        final Long groupId = groupService.create(GroupConverter.toEntity(createGroup));
        final URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(groupId)
                .toUri();

        return ResponseEntity.created(uriLocation).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity putRecord(@PathVariable final Long id, @Valid @RequestBody UpdateGroup updateGroup) {
        groupService.update(GroupConverter.toEntity(groupService.getRecord(id).orElseThrow(() -> ResourceNotFoundException.of()), updateGroup));

        return ResponseEntity.noContent().build();
    }
}
