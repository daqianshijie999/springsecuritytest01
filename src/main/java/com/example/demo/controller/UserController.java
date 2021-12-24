package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

@Autowired
UserMapper userMapper;

@Autowired
RoleMapper roleMapper;

    @GetMapping
    public List getUserAll(){
        List<Users> userList = userMapper.selectList(null);
        return userList;
    }
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id")Long id){
        Users user = userMapper.selectById(id);
        return user;
    }

    @PostMapping
    public String addUser(@RequestBody Users user) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String pwd = passwordEncoder.encode(user.getPassword());
//        user.setPassword(pwd);
        userMapper.insert(user);
        return "添加成功";
    }

    @GetMapping("/role")
    public List getRoleAll(){
        List<Role> roleList = roleMapper.selectList(null);
        return roleList;
    }
}
