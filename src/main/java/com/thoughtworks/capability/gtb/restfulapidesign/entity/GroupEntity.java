package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntity {
    private Integer id;
    private String name;
    private String note;
    private List<StudentEntity> students;
}
