package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author VideoPO
 * @time 2020/12/27--18:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPO implements Serializable {
    private String id;
    private String title;
    private String description;
    private String videoPath;
    private String coverPath;
    private String uploadTime;
    private String categoryId;
    private String groupId;
    private String userId;
    private String headImg;
    private String cateName;
}
