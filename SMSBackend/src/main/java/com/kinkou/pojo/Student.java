package com.kinkou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String studentId;
    private String gender;
    private Integer age;
    private Long majorId;
    private Double score;

    private String majorName;
}