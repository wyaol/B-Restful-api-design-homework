package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.CommonException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<GroupEntity> group() {
        return groupService.group();
    }

    @PatchMapping("/{groupId}")
    public GroupEntity patchGroup(@PathVariable Integer groupId, @RequestBody GroupEntity groupEntity) throws CommonException {
        return groupService.patchGroupById(groupId, groupEntity);
    }

    @GetMapping
    public List<GroupEntity> getGroups() {
        return groupService.getGroups();
    }
}
