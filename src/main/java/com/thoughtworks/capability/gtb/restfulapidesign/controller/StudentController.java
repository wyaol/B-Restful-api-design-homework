package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDTO;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.CommonException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) throws CommonException {
        return studentService.addStudent(studentDTO);
    }

    @DeleteMapping("{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable Integer studentId) {
        studentService.deleteStudentById(studentId);
    }

    @GetMapping
    public List<StudentDTO> getStudents(@RequestParam(required = false) Map filters) {
        return studentService.getStudents(filters);
    }
}
