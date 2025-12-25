package com.kinkou.mapper;

import com.kinkou.pojo.Student;
import com.kinkou.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    Student getById(Integer id);

    void insert(Student student);

    void update(Student student);

    void delete(Integer id);
}
