package com.baizhi.app;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.commo.CommoResult;
import com.baizhi.po.VdieoVO;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunSendMsgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author InterfaceController
 * @time 2020/12/27--17:33
 */
@RestController
@RequestMapping("app")
public class InterfaceController {
    @Resource
    VideoService videoService;

    @RequestMapping("getPhoneCode")
    public HashMap<String,Object> getPhoneCode(String phone){
        HashMap<String,Object> map = new HashMap<>();
        String message=null;
        try{
            String random = AliyunSendMsgUtil.getRandom(4);
            message = AliyunSendMsgUtil.sendPhoneCode(phone, random);
            map.put("status","100");
            map.put("data",phone);
        }catch (ClientException e){
            e.printStackTrace();

            map.put("status","100");
            map.put("data",phone);
        }
        map.put("message",message);
        return map;
    }
    @RequestMapping("queryByReleaseTime")
    public CommoResult queryByReleaseTime(){
        try {
            List<VdieoVO> videoVOS = videoService.queryByReleaseTime();
            return new CommoResult().success(videoVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommoResult().filed();
        }
        }
        }
