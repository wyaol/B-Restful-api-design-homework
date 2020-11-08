package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
}
