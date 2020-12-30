package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserMapper;

import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.po.CityPO;
import com.baizhi.service.UserService;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author UserServiceImpl
 * @time 2020/12/21--20:03
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Autowired
    private HttpServletRequest request;
    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        UserExample example = new UserExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<User> users = userMapper.selectByExampleAndRowBounds(example, rowBounds);

        //遍历集合
        for (User user : users) {
            //根据用户id查询学分  redis
        String id = user.getId();
        //查询学分并赋值
        user.setScore("88");
    }

        map.put("rows",users);

        //查询总条数
        int records = userMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }

    @Override
    public String add(User user) {
        System.out.println(user+"这个是业务层");
        String uuid = UUIDUtil.getUUID();
        user.setId(uuid);
        user.setCreateDate(new Date());
        user.setStatus("1");

        userMapper.insertSelective(user);

        //添加方法返回id
        return uuid;
    }

    @Override
    public void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request) {
            //1.获取文件名
            String filename = headImg.getOriginalFilename();
            //图片拼接时间戳
            String newName=new Date().getTime()+"-"+filename;

            //2.根据相对路径获取绝对路径
            String realPath = request.getServletContext().getRealPath("/upload/cover");

            //获取文件夹
            File file = new File(realPath);
            //判断文件夹是否存在
            if(!file.exists()){
                file.mkdirs();//创建文件夹
            }

        //3.文件上传
        try {
            headImg.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId(id);
        user.setHeadImg(newName);

        //4.修改数据
        //System.out.println(user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public String edit(User user) {

        if (user.getHeadImg()==null||user.getHeadImg()==""){
            //设置查询条件对象
            User admin = new User();
            admin.setId(user.getId());
//查询数据
            User admins = userMapper.selectOne(admin);
            user.setHeadImg(admins.getHeadImg());
           userMapper.updateByPrimaryKeySelective(user);
            return null;
        }
        userMapper.updateByPrimaryKeySelective(user);
        return user.getId();
    }

    @Override
    public void del(User user) {
        userMapper.deleteByPrimaryKey(user);
    }

    @Override
    public void update(String id) {
        //设置查询条件对象
        User admin = new User();
        admin.setId(id);
//查询数据
        User user = userMapper.selectOne(admin);



        if(user.getStatus().equals("1")){





            admin.setStatus("0");
//修改数据
            userMapper.updateByPrimaryKeySelective(admin);
        }else{

            admin.setStatus("1");
//修改数据
            userMapper.updateByPrimaryKeySelective(admin);
        }
    }

    @Override
    public void query() {

        List<User> admins = userMapper.selectAll();

        //设置导出参数  参数：标题，工作表名字
        ExportParams exportParams = new ExportParams("用户信息", "用户");

        //创建导出的表格   参数：设置导出参数,类对象,数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class, admins);

        try {
            //导出表格
            workbook.write(new FileOutputStream(new File("E:\\三阶段资料\\三阶段项目\\后期项目\\导出表格//easyPoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CityPO> findAllBySex(String sex) {
        return userMapper.findAllBySex(sex);
    }
    @Override public Integer findAllByMonth(String sex,Integer month){
            return userMapper.findAllByMonth(sex,month);
    }
}


