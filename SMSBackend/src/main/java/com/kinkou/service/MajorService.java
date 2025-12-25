package com.kinkou.service;

import com.kinkou.pojo.Major;
import com.kinkou.pojo.MajorQueryParam;
import com.kinkou.pojo.PageResult;

public interface MajorService {
    PageResult page(MajorQueryParam majorQueryParam);

    Major getById(Integer id);

    void delete(Integer id);

    void update(Major major);

    void insert(Major major);
}
