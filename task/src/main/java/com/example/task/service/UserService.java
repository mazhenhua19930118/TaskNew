package com.example.task.service;
import com.example.task.result.Result;

public interface UserService {
    Result findUser(String name);
    Result register(String name,String password);
}
