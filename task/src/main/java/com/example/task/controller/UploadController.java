package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.result.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {
    @PostMapping("uploadFile")
    public Result<User> upload(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        System.out.println("file"+file);
        return result;
    }
}
