package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private Integer id;
    private String name;
    private String gender;
    private String note;

    public StudentEntity(String name, String gender, String note) {
        this.name = name;
        this.gender = gender;
        this.note = note;
    }
}
