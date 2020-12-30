package com.baizhi.controller;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import com.baizhi.service.AdminService;
import com.baizhi.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author AdminCountroller
 * @time 2020/12/19--23:16
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Resource
    AdminMapper adminMapper;
    @RequestMapping("/login")
    public String queryone(String username,String password,String ucode,HttpSession session,String mess){
        try{
            String icode = (String) session.getAttribute("icode");
            session.removeAttribute("icode");
            if(!icode.equals(ucode))throw new RuntimeException("验证码错误");
            AdminExample example = new AdminExample();
            example.createCriteria().andUsernameEqualTo(username);
            Admin admin = adminMapper.selectOneByExample(example);
            session.setAttribute("username",username);
            //验证账号是否存在            if(admin==null)throw new RuntimeException("该账号不存在");

            //验证密码是否正确
            if(!admin.getPassword().equals(password))throw new RuntimeException("密码错误");


            return "redirect:/main/main.jsp";

        }catch (Exception e){
            e.printStackTrace();
            mess = e.getMessage();
            session.setAttribute("mess",mess);
            return "redirect:/login/login.jsp";
        }
    }

    //验证码部分
    @RequestMapping("/ImageAction")
    public String execute(HttpSession session, HttpServletResponse response) throws Exception {
        //实现验证码的功能
        CreateValidateCode cvc = new CreateValidateCode();
        String icode = cvc.getCode();
        System.out.println(icode);
        session.setAttribute("icode",icode);
        ServletOutputStream out = response.getOutputStream();
        cvc.write(out);
        return null;
    }

    //分页查所有
    @ResponseBody
    @RequestMapping("/queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return adminService.queryUserPage(page, rows);
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(Admin admin, String oper){



        if(oper.equals("add")){
            adminService.add(admin);
        }
        if(oper.equals("edit")){


            adminService.edit(admin);
        }
        if(oper.equals("del")){
            adminService.del(admin);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("updateStatus")
    public void updateStatus(String id){
        adminService.update(id);
    }
}
