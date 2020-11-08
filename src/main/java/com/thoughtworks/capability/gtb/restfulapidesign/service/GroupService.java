package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final static int GROUP_NUM = 6;

    public GroupService() {
        studentRepository = StudentRepository.getInstance();
        groupRepository = GroupRepository.getInstance();
    }

    public List<GroupEntity> group() {
        List<StudentEntity> studentEntities = studentRepository.getStudents();
        List<StudentEntity> studentEntities1 = studentEntities.subList(0, studentEntities.size());
        Collections.shuffle(studentEntities1);
        int groupStudentNum = studentEntities1.size() / GROUP_NUM;
        int resStudentNum = studentEntities1.size() % GROUP_NUM;

        int currentIndex = 0;
        int groupIndex = 0;
        groupRepository.setGroups(new ArrayList<>());
        while (currentIndex < studentEntities1.size()) {
            int currentGroupStudentNum = groupIndex < resStudentNum ? groupStudentNum + 1 : groupStudentNum;
            groupRepository.addGroup(new GroupEntity(null, String.format("%d 组", groupIndex + 1), "",
                    studentEntities1.subList(currentIndex, currentIndex + currentGroupStudentNum)));
            currentIndex += currentGroupStudentNum;
            groupIndex ++;
        }
        return groupRepository.getGroups();
    }
}
