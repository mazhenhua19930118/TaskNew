package com.example.task.mapper;

import com.example.task.entity.User;


public interface UserMapper {
    User findUser(String name);
    int register(String name,String password);
}
