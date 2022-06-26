package com.example.task.service;

import com.example.task.entity.Task;
import com.example.task.result.Result;



public interface TaskService {
    Result add(Task task);
    Result findAll(String status);
    Result pass(String status,String taskNumber);
}
