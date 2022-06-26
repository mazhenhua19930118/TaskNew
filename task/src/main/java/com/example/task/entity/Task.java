package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Task {
    private int id;
    private String taskName;
    private String taskNumber;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
    private String userName;
    private String taskKind;
    private String startRank;
    private String endRank;
    private BigDecimal money;
    private String status;//0待审核；1:已审核

}
