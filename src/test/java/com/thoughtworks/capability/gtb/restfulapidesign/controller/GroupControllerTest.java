package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Resource
    private MockMvc mockMvc;
    private final StudentRepository studentRepository = StudentRepository.getInstance();
    private final GroupRepository groupRepository = GroupRepository.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    private GroupService groupService;

    @BeforeEach
    void init() {
        studentRepository.addStudent(new StudentEntity(null, "Tom", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Jerry", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Jack", "female", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Black", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Martin", "female", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Harry", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Potter", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Lily", "female", "111"));
    }

    @AfterEach
    void clean() {
        studentRepository.setStudents(new ArrayList<>());
    }

    @Test
    void shouldGroup() throws Exception {
        mockMvc.perform(post("/groups"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].students", hasSize(2)))
                .andExpect(jsonPath("$[1].students", hasSize(2)))
                .andExpect(jsonPath("$[2].students", hasSize(1)));
    }

    @Test
    void shouldPatchGroupName() throws Exception {
        List<GroupEntity> groupEntities = groupService.group();
        GroupEntity groupEntityChoose = groupEntities.get(0);
        Integer groupId = groupEntityChoose.getId();
        String note = groupEntityChoose.getNote();
        String newName = "新的名字";
        GroupEntity groupEntity = new GroupEntity(null, newName, null, null);

        mockMvc.perform(patch("/groups/"+ groupId).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newName)))
                .andExpect(jsonPath("$.note", is(note)));
    }

    @Test
    void shouldGetGroups() throws Exception {
        List<GroupEntity> groupEntities = groupService.group();
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].students", hasSize(2)))
                .andExpect(jsonPath("$[1].students", hasSize(2)))
                .andExpect(jsonPath("$[2].students", hasSize(1)));
    }
}
