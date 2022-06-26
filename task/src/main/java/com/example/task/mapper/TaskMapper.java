package com.example.task.mapper;

import com.example.task.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    int add(Task task);
    int addList(@Param("list")List<Task> list);
    List<Task> findAll(@Param("status") String status);
    int pass(@Param("status")String status,@Param("taskNumber")String taskNumber);
}
