package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.StudentDTO;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import lombok.Data;

import java.util.*;
import java.util.function.Function;

@Data
public class StudentRepository {

    private static StudentRepository instance;
    private List<StudentEntity> students;
    private static Integer currentId = 1;
    private Map<String, Function<StudentEntity, Object>> functions;


    private StudentRepository() {
        this.students = new ArrayList<>();
        functions = new HashMap<>();
        functions.put("gender", StudentEntity::getGender);
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
        currentId++;
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
        for (StudentEntity studentEntity : this.students) {
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

    public List<StudentEntity> getStudents(Map filters) {
        List<StudentEntity> studentEntities = new ArrayList<>();
        this.students.forEach(studentEntity -> {
            if (this.isFit(studentEntity, filters)) studentEntities.add(studentEntity);
        });
        return studentEntities;
    }

    private boolean isFit(StudentEntity studentEntity, Map filters) {
        boolean res = true;

        for (Object key : filters.keySet()) {
            if (this.functions.containsKey((String) key)
                    && !(this.functions.get(key).apply(studentEntity)).equals(filters.get(key)))
                res = false;
        }
        return res;
    }

    public StudentEntity updateStudentById(Integer studentId, StudentEntity student) {
        StudentEntity studentEntity = this.getStudentById(studentId);
        Objects.requireNonNull(studentEntity);
        studentEntity.setName(student.getName());
        studentEntity.setGender(student.getGender());
        studentEntity.setNote(student.getNote());
        return studentEntity;
    }
}
