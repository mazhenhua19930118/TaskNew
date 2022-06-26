package com.example.task.controller;

import com.example.task.entity.Task;
import com.example.task.result.Result;
import com.example.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("add")
    public Result add(@RequestBody Task task) {
        return taskService.add(task);
    }

    @GetMapping("findAll")
    public Result findAll(String status) {
        return taskService.findAll(status);
    }
    @PostMapping("pass")
    public Result pass(String status,String taskNumber){
        return  taskService.pass(status,taskNumber);
    }
}
