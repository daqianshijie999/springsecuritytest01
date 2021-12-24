package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class Users {
private Integer id;
private String username;
private String password;
private Integer type;
}
