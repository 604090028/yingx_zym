package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;

import java.util.HashMap;

public interface CategoryService {
    HashMap<String,Object> queryUserPage(Integer page, Integer rows);
    HashMap<String,Object> queryUserPage(Integer page, Integer rows,String id);
    void add(Category category);
    void edit(Category category);
    void del(Category category);


}
