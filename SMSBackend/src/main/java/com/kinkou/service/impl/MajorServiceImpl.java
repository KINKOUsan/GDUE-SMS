package com.kinkou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kinkou.mapper.MajorMapper;
import com.kinkou.pojo.*;
import com.kinkou.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;


    @Override
    public PageResult page(MajorQueryParam majorQueryParam) {
        PageHelper.startPage(majorQueryParam.getPage(), majorQueryParam.getPageSize());
        List<Major> list = majorMapper.list(majorQueryParam);
        Page<Major> page = (Page<Major>) list;
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Major getById(Integer id) {
        return majorMapper.getById(id);
    }

    @Override
    public void delete(Integer id) {
        majorMapper.delete(id);
    }

    @Override
    public void update(Major major) {
        majorMapper.update(major);
    }

    @Override
    public void insert(Major major) {
        majorMapper.insert(major);
    }
}
