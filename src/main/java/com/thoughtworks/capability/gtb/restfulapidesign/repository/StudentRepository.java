package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StudentRepository {

    private static StudentRepository instance;
    private List<StudentEntity> students;

    private StudentRepository() {
        this.students = new ArrayList<>();
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public StudentEntity addStudent(StudentEntity studentEntity) {
        studentEntity.setId(students.size() + 1);
        this.students.add(studentEntity);
        return studentEntity;
    }

    public StudentEntity getStudentByName(String name) {
        StudentEntity res = null;
        for (StudentEntity studentEntity: this.students) {
            if (studentEntity.getName().equals(name))
                res = studentEntity;
        }
        return res;
    }
}
