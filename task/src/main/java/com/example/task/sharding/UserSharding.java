package com.example.task.sharding;

import lombok.Data;

import java.util.Date;

@Data
public class UserSharding {
    private int id;
    private String username;
    private Integer age;
    private Date birthday;
    private String cmd;

}
