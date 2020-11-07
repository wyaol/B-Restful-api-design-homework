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
    private Gender gender;
    private String note;
}

enum Gender {
    MALE, FEMALE
}
