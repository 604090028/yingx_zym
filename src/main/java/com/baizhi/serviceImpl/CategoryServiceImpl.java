package com.baizhi.serviceImpl;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.CategoryExample;
import com.baizhi.service.CategoryService;
import com.baizhi.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author CategoryServiceImpl
 * @time 2020/12/22--15:09
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example = new CategoryExample();

            example.createCriteria().andLevelsEqualTo(1);
            //创建分页对象   参数：从第几条开始，展示几条
            RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
            List<Category> category = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
            map.put("rows",category);

            //查询总条数
            int records = categoryMapper.selectCountByExample(example);
            map.put("records",records);

            //计算总页数
            Integer tolal=records%rows==0?records/rows:records/rows+1;
            map.put("total",tolal);

            return map;

    }

    @Override
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows, String id) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //创建条件对象
        CategoryExample example = new CategoryExample();

        example.createCriteria().andParentIdEqualTo(id);
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Category> category = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",category);

        //查询总条数
        int records = categoryMapper.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }

    @Override
    public void add(Category category) {
        String uuid = UUIDUtil.getUUID();
        category.setId(uuid);
        categoryMapper.insertSelective(category);
    }

    @Override
    public void edit(Category category) {

        if(category.getLevels()==null){
            category.setId(category.getId());
            category.setLevels(1);
            categoryMapper.updateByPrimaryKeySelective(category);

//查询数据
            Category categorye = categoryMapper.selectOne(category);


            categoryMapper.updateByPrimaryKeySelective(categorye);
        }

    }

    @Override
    public void del(Category category) {

        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(category.getId());


        List<Category> categorye = categoryMapper.selectByExample(example);

        System.out.println(categorye);

        if (categorye.size()==0){
            categoryMapper.deleteByPrimaryKey(category);
        }



    }


}
