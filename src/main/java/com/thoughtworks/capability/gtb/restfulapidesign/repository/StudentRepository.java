package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class StudentRepository {

    private static StudentRepository instance;
    private List<StudentEntity> students;
    private static Integer currentId = 1;

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
        studentEntity.setId(currentId);
        this.students.add(studentEntity);
        currentId ++;
        return studentEntity;
    }

    public StudentEntity getStudentByName(String name) {
        return this.getStudent(name, StudentEntity::getName);
    }

    public StudentEntity getStudentById(Integer id) {
        return this.getStudent(id, StudentEntity::getId);
    }

    private StudentEntity getStudent(Object target, Function<StudentEntity, Object> func) {
        StudentEntity res = null;
        for (StudentEntity studentEntity: this.students) {
            if (func.apply(studentEntity).equals(target))
                res = studentEntity;
        }
        return res;
    }

    public void deleteStudentById(Integer studentId) {
        StudentEntity studentEntity = this.getStudentById(studentId);
        if (studentEntity != null)
            this.students.remove(studentEntity);
    }
}
