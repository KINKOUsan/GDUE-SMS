package com.kinkou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
    private String name;
    private String majorId;
}
