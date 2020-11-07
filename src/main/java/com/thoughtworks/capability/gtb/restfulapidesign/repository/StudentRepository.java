package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;

import java.util.List;

public class StudentRepository {

    private static StudentRepository instance;
    private List<StudentEntity> students;

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }
}
