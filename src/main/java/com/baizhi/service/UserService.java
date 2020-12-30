package com.baizhi.service;


import com.baizhi.entity.User;
import com.baizhi.po.CityPO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    String add(User user);

    void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request);

    String edit(User user);

    void del(User user);

    void update(String id);

    void query();

    List<CityPO> findAllBySex(String sex);

    Integer findAllByMonth(String sex,Integer month);
}
