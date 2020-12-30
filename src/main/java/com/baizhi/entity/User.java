package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Table(name = "yx_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Excel(name = "ID")
    @Id
    private String id;
    @Excel(name = "电话")
    private String phone;
    @Excel(name = "名字")
    private String username;
    @Excel(name = "头像路径")
@Column(name = "head_img")
    private String headImg;
    @Excel(name = "简介")
    private String brief;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "创建时间",exportFormat="yyyy-MM-dd",importFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date createDate;
    @Transient
    private String score;
    private String sex;
    private String diz;
}