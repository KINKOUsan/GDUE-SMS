package com.kinkou.service;

import com.kinkou.pojo.PageResult;
import com.kinkou.pojo.Student;
import com.kinkou.pojo.StudentQueryParam;

public interface StudentService {
    PageResult page(StudentQueryParam studentQueryParam);

    Student getById(Integer id);

    void insert(Student student);

    void update(Student student);

    void delete(Integer id);
}
