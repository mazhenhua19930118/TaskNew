package com.example.task.service.impl;

import com.example.task.entity.User;
import com.example.task.mapper.UserMapper;
import com.example.task.result.Result;
import com.example.task.service.UserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Result findUser(String name) {
        User user = userMapper.findUser(name);
        if(user!=null){
            return  new Result("200","success",user);
        }else{
            return new Result("100","用户名不存在","");
        }

    }
    @Override
    public Result register(String name,String password) {
        User user = userMapper.findUser(name);
        if(user!=null && name.equals(user.getName())){
            return new Result("100","用户已经存在");
        }
        int result = userMapper.register(name,password);
        if(result == 1){
            return new Result("200","注册成功");
        }else{
            return new Result("200","注册失败");
        }

    }
}
