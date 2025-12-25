package com.kinkou.mapper;

import com.kinkou.pojo.Major;
import com.kinkou.pojo.MajorQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper {

    List<Major> list(MajorQueryParam majorQueryParam);

    Major getById(Integer id);

    void insert(Major major);

    void update(Major major);

    void delete(Integer id);
}
