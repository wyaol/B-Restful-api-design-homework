package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;

import java.util.List;

public class GroupRepository {

    private static GroupRepository instance;
    private List<GroupEntity> groups;

    public GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepository();
        }
        return instance;
    }
}
