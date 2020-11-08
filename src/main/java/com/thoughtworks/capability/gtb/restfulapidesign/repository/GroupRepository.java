package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class GroupRepository {

    private static GroupRepository instance;
    private List<GroupEntity> groups;
    private static int currentId = 1;

    private GroupRepository() {
        groups = new ArrayList<>();
    }

    public static GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepository();
        }
        return instance;
    }

    public void addGroup(GroupEntity groupEntity) {
        groupEntity.setId(currentId);
        this.groups.add(groupEntity);
        currentId ++;
    }

    public GroupEntity getGroupById(Integer groupId) {
        for (GroupEntity groupEntity: this.groups) {
            if (groupEntity.getId().equals(groupId)) return groupEntity;
        }
        return null;
    }

    public GroupEntity patchGroupById(Integer groupId, GroupEntity groupEntity) {
        GroupEntity groupEntity1 = this.getGroupById(groupId);
        Objects.requireNonNull(groupEntity1);
        if (groupEntity.getName() != null)
            groupEntity1.setName(groupEntity.getName());
        if (groupEntity.getNote() != null)
            groupEntity1.setNote(groupEntity.getNote());
        return groupEntity1;
    }
}
