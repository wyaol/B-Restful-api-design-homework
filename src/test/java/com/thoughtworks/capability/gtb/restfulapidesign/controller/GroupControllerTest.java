package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Resource
    private MockMvc mockMvc;
    private final StudentRepository studentRepository = StudentRepository.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    void shouldGroup() throws Exception {
        mockMvc.perform(post("/groups"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].students", hasSize(2)))
                .andExpect(jsonPath("$[1].students", hasSize(2)))
                .andExpect(jsonPath("$[2].students", hasSize(1)));
    }
}
