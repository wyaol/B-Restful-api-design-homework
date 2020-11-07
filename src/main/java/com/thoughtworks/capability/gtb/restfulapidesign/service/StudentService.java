package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDTO;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.CommonException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService() {
        studentRepository = StudentRepository.getInstance();
    }

    private StudentDTO studentEntityToStudentDTO(StudentEntity studentEntity) {
        return new StudentDTO(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getGender(),
                studentEntity.getNote()
        );
    }

    private StudentEntity studentDTOToStudentEntity(StudentDTO studentDTO) {
        return new StudentEntity(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getGender(),
                studentDTO.getNote()
        );
    }

    public StudentDTO addStudent(StudentDTO studentDTO) throws CommonException {
        if (studentRepository.getStudentByName(studentDTO.getName()) != null)
            throw new CommonException("该用户名已存在");
        StudentEntity studentEntity = studentRepository.addStudent(studentDTOToStudentEntity(studentDTO));
        return studentEntityToStudentDTO(studentEntity);
    }
}
