package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.po.VdieoVO;
import com.baizhi.po.VideoPO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {
    HashMap<String, Object> queryVideoPage(Integer page, Integer rows);

    String add(Video video);
    void edit(Video video);

    void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request);
    void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);


    void del(Video video);
    List<VdieoVO> queryByReleaseTime();
}