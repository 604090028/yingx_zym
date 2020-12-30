package com.baizhi;

import com.baizhi.dao.UserMapper;
import com.baizhi.serviceImpl.VideoServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author T
 * @time 2020/12/27--18:59
 */
@SpringBootTest
public class T {
    @Autowired
    private VideoServiceImpl dao;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void t1(){
        List a = dao.queryByReleaseTime();
        a.forEach(b -> System.out.println(b));
        //videoPOS.forEach(a -> System.out.println(a));
    }
//    @Test
//    public void t2(){
//        List<UserPO> a = userMapper.query("男");
//        a.forEach(c -> System.out.println(c));
//    }
}
