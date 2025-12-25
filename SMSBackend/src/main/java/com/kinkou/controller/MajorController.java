package com.kinkou.controller;

import com.kinkou.pojo.Major;
import com.kinkou.pojo.MajorQueryParam;
import com.kinkou.pojo.PageResult;
import com.kinkou.pojo.Result;
import com.kinkou.service.MajorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/major")
@RestController
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping
    public Result page(MajorQueryParam majorQueryParam) {
        log.info("专业条件分页查询: {}", majorQueryParam);
        PageResult pageResult = majorService.page(majorQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询id为{}的专业信息", id);
        Major major = majorService.getById(id);
        return Result.success(major);
    }

    @PostMapping
    public Result insert(@RequestBody Major major) {
        log.info("新增专业信息{}", major);
        majorService.insert(major);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Major major) {
        log.info("修改专业信息{}", major);
        majorService.update(major);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除专业信息{}", id);
        majorService.delete(id);
        return Result.success();
    }
}
