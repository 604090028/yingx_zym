package com.baizhi.dao;

import com.baizhi.entity.User;

import java.util.List;

import com.baizhi.po.CityPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    List<CityPO> findAllBySex(String sex);

    Integer findAllByMonth(@Param("sex") String sex,
                           @Param("month") Integer month);
}