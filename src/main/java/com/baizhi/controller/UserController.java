package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.po.CityPO;
import com.baizhi.po.SexPO;
import com.baizhi.service.UserService;
import com.baizhi.util.MonthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author UserCountroller
 * @time 2020/12/21--17:43
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @ResponseBody
    @RequestMapping("/queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return userService.queryUserPage(page, rows);
    }


    /*
     * 添加带图片的数据
     * 1.数据入库
     *    问题：
     *          文件上传问题
     *         图片地址有问题
     *  返回数据id
     * 2.文件上传
     * 3.修改图片数据
     * */
    @ResponseBody
    @RequestMapping("edit")
    public String edit(User user, String oper){
        String id =null;
        if(oper.equals("add")){
            id = userService.add(user);
        }
        if(oper.equals("edit")){
            id=userService.edit(user);
        }
        if(oper.equals("del")){
            userService.del(user);
        }
        return id;
    }

    @ResponseBody
    @RequestMapping("uploadUserCover")
    public void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request){
        userService.uploadUserCover(headImg, id, request);
    }
    @ResponseBody
    @RequestMapping("updateStatus")
    public void updateStatus(String id){
        userService.update(id);
    }

    @ResponseBody
    @RequestMapping("query")
    public void query(){
        userService.query();
    }



    @ResponseBody
    @RequestMapping("findAllBySes")
    public List<SexPO> findAllBySes() {
        List<SexPO> sexList = new ArrayList<>();
        List<CityPO> cityBoy = userService.findAllBySex("男");
        sexList.add(new SexPO("小男孩", cityBoy));
        List<CityPO> cityGitl = userService.findAllBySex("女");
        sexList.add(new SexPO("小姑娘", cityGitl));
        return sexList;
    }

    @RequestMapping("findAllByMonth")
    @ResponseBody
    public Map<String, Object> findAllByMonth() {
        HashMap<String, Object> map = new HashMap<>();

        List<Integer> list = MonthUtil.queryMonths();
        List<Integer> boys = new ArrayList<>();
        List<Integer> gitls = new ArrayList<>();
        List<String> monthName = new ArrayList<>();


        String mm = new SimpleDateFormat("MM").format(new Date());
        Integer month = Integer.valueOf(mm);
        for (Integer integer : list) {
            boys.add(userService.findAllByMonth("男", integer));
            gitls.add(userService.findAllByMonth("女", integer));
            monthName.add(integer + "月");
        }
        map.put("months", monthName);
        map.put("boys", boys);
        map.put("gitls", gitls);
        return map;

    }
}
