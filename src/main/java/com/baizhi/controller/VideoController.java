package com.baizhi.controller;

import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author VideoCountroller
 * @time 2020/12/23--18:48
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    @Resource
    VideoService videoService;
    @ResponseBody
    @RequestMapping("/queryVideoPage")
    public HashMap<String, Object> queryVideoPage(Integer page, Integer rows){
        return videoService.queryVideoPage(page, rows);
    }
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(Video video, String oper){
//        HashMap map =new HashMap();
//        if(oper.equals("add")){
//            String id = videoService.add(video);
//            map.put("id",id);
//        }
//        if(oper.equals("edit")){
//            String id=videoService.edit(video);
//            map.put("id",id);
//        }
//        if(oper.equals("del")){
//            videoService.del(video);
//
//        }
//        return map;
        if (oper.equals("add")) {
            String id = videoService.add(video);
            return id;
        }

        if (oper.equals("edit")) {
            videoService.edit(video);
        }

        if (oper.equals("del")) {
            videoService.del(video);
        }

        return "";
    }
    @ResponseBody
    @RequestMapping("uploadUserCover")
    public void uploadUserCover(MultipartFile videoPath, String id, HttpServletRequest request){
        System.out.println(videoPath+"123123aaa");
        System.out.println(id+"123123aaa");
        videoService.uploadVdieosAliyun(videoPath, id, request);

        System.out.println("上传成功");
    }
}
