package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello 游客";
    }

    @GetMapping("/index")
    @Secured("ROLE_nor")
    public String index(){
        return "index ";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_sale","ROLE_manager2"})//只有角色role能用，auth权限admin不能用
    @PreAuthorize("hasAnyAuthority('admin','ROLE_manager')")//方法之前 两种都能用 前提加springboot加开启注解
//    @PostAuthorize("hasAnyAuthority('admin1','ROLE_manager')")//方法之后 两种都能用 前提加springboot加开启注解
    public String update(){
        return "update";
    }

//    @PreFilter()  //传入方法数据进行过滤
//    @PostFilter()  //方法返回数据进行过滤
}
