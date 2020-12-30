package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


//http://192.168.62.1/
@MapperScan("com.baizhi.dao")
@org.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
@SpringBootApplication
public class YingxZymApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxZymApplication.class, args);
    }

}
