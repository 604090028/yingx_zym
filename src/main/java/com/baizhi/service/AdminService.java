package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.HashMap;

public interface AdminService {
    Admin queryone(String username);

    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    void add(Admin admin);

    void edit(Admin admin);

    void del(Admin admin);

    void update(String id);

}
