package com.kinkou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kinkou.mapper.StudentMapper;
import com.kinkou.pojo.PageResult;
import com.kinkou.pojo.Student;
import com.kinkou.pojo.StudentQueryParam;
import com.kinkou.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.list(studentQueryParam);
        Page<Student> page = (Page<Student>) list;
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(Integer id) {
        studentMapper.delete(id);
    }
}
