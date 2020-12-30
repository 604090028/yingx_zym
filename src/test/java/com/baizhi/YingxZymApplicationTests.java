package com.baizhi;

import com.baizhi.dao.AdminMapper;
import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.AdminService;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class YingxZymApplicationTests {
    @Autowired
    AdminService adminServices;
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    AdminMapper adminMapper;
    @Test
    void contextLoads() {
        //条件对象
        AdminExample example = new AdminExample();
        //设置条件
        example.createCriteria().andUsernameEqualTo("zhangsan");
        //使用方法
        Admin admin = adminMapper.selectOneByExample(example);

        System.out.println(admin);
    }
    @Test
    void select(){
        Category category = new Category();
        category.setId("7");
        System.out.println(category+"这个对象是");
//查询数据
        Category categorye = categoryMapper.selectOne(category);
        System.out.println("这个对象是"+categorye);

    }
    @Test
    void selecte(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andLevelsEqualTo(1);

        RowBounds rowBounds = new RowBounds(0,5);
        List<Category> admins = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Object object: admins) {
            System.out.println(object);
        }
        int records = categoryMapper.selectCountByExample(example);
        System.out.println("一共"+records);


    }

    @Test
    void selecte1(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo("1");

        RowBounds rowBounds = new RowBounds(0,5);
        List<Category> admins = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Object object: admins) {
            System.out.println(object);
        }
        int records = categoryMapper.selectCountByExample(example);
        System.out.println("一共"+records);


    }
    @Test
    void select5(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo("1");
        List<Category> category = categoryMapper.selectByExample(example);
        for (Object object: category) {
            System.out.println(category);
        }
    }
//    @Autowired
//    UserMapper userMapper;
//    @Test
//    public void t1(){
//        List<UserPO> a = userMapper.queryaa("女");
//    }

}
