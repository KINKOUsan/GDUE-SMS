package com.kinkou.controller;

import com.kinkou.pojo.PageResult;
import com.kinkou.pojo.Result;
import com.kinkou.pojo.Student;
import com.kinkou.pojo.StudentQueryParam;
import com.kinkou.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学生条件分页查询: {}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询id为{}的学生", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("新增学生{}", student);
        studentService.insert(student);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生{}", student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除id为{}的学生", id);
        studentService.delete(id);
        return Result.success();
    }
}
