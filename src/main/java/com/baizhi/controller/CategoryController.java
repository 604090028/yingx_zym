package com.baizhi.controller;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Category
 * @time 2020/12/22--16:12
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    CategoryService categoryService;
    @ResponseBody
    @RequestMapping("/select")
    public HashMap<String,Object>queryselect(Integer page, Integer rows){

        return categoryService.queryUserPage(page, rows);
    }
    @ResponseBody
    @RequestMapping("/selecttow")
    public HashMap<String,Object>queryselect(Integer page, Integer rows,String id){

        return categoryService.queryUserPage(page, rows,id);
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Category category, String oper,String ida){




        if(oper.equals("add")){
            if(ida==null){
                category.setLevels(1);
                //修改数据
                System.out.println(category);
                categoryService.add(category);
            }else{
                category.setLevels(2);
                category.setParentId(ida);
                //修改数据
                categoryMapper.updateByPrimaryKeySelective(category);
                categoryService.add(category);

            }


        }
        if(oper.equals("edit")){
            if (ida!=null){
                category.setLevels(2);
                category.setParentId(ida);
                categoryMapper.updateByPrimaryKeySelective(category);
                categoryService.edit(category);

            }else {
                categoryService.edit(category);
            }
        }
        if(oper.equals("del")){

            categoryService.del(category);
        }
        return null;
    }
}
