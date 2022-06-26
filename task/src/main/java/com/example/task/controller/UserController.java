package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.result.Result;
import com.example.task.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    public Result<User> findUser(@RequestBody User user, HttpServletRequest request) {
        log.info("-----开始查询-----"+user+"-----");
        Result result = userService.findUser(user.getName());
        if("200".equals(result.getCode())){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }
        return result;
    }

    @PostMapping("register")
    public Result register(@RequestBody User user) {
        log.info("-----开始注册-----"+user+"-----");
        return userService.register(user.getName(),user.getPassword());
    }

}
