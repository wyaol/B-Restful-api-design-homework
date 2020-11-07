package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDTO;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Resource
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final StudentRepository studentRepository = StudentRepository.getInstance();

    @AfterEach
    void clean() {
        studentRepository.setStudents(new ArrayList<>());
    }

    @Test
    void shouldAddStudent() throws Exception {
        int studentLength = studentRepository.getStudents().size();
        StudentDTO studentDTO = new StudentDTO(null, "Tom", "male", "111");

        mockMvc.perform(
                post("/students")
                        .content(objectMapper.writeValueAsString(studentDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());
        assertEquals(studentLength + 1, studentRepository.getStudents().size());
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        StudentEntity studentEntity = new StudentEntity(null, "Tom", "male", "111");
        studentRepository.addStudent(studentEntity);
        int studentLength = studentRepository.getStudents().size();
        Integer studentId = studentRepository.getStudentByName("Tom").getId();

        mockMvc.perform(delete("/students/" + studentId))
                .andExpect(status().isNoContent());
        assertEquals(studentLength - 1, studentRepository.getStudents().size());
    }

    @Test
    void shouldGetStudents() throws Exception {
        studentRepository.addStudent(new StudentEntity(null, "Tom", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Jerry", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Jack", "female", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Black", "male", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Martin", "female", "111"));
        studentRepository.addStudent(new StudentEntity(null, "Harry", "male", "111"));

        mockMvc.perform(get("/students?gender=male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
