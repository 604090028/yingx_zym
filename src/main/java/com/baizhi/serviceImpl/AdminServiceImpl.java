package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author AdminServiceImpl
 * @time 2020/12/19--22:17
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin queryone(String username) {
        //条件对象
        AdminExample example = new AdminExample();
       //设置条件
        example.createCriteria().andUsernameEqualTo(username);
        //调用方法
        return adminMapper.selectOneByExample(example);
    }

    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        AdminExample example = new AdminExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Admin> users = adminMapper.selectByExampleAndRowBounds(example, rowBounds);



        map.put("rows",users);

        //查询总条数
        int records = adminMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }

    @Override
    public void add(Admin admin) {
        String uuid = UUIDUtil.getUUID();
        admin.setId(uuid);
        admin.setStatus("1");
        adminMapper.insertSelective(admin);
    }


    @Override
    public void edit(Admin admin) {
        admin.setId(admin.getId());
//查询数据


        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void del(Admin admin) {
        adminMapper.deleteByPrimaryKey(admin);
    }

    @Override
    public void update(String id) {
        //设置查询条件对象
        Admin admin = new Admin();
        admin.setId(id);
//查询数据
        Admin user = adminMapper.selectOne(admin);



        if(user.getStatus().equals("1")){





            admin.setStatus("0");
//修改数据
            adminMapper.updateByPrimaryKeySelective(admin);
        }else{

            admin.setStatus("1");
//修改数据
            adminMapper.updateByPrimaryKeySelective(admin);
        }

    }
}
