package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VdieoVO
 * @time 2020/12/27--18:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VdieoVO {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private Integer likeCount;
    private String cateName;
    private String userPhoto;
}
