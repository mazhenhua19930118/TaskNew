package com.example.task.batch;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.impl.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class batch {
   /* @Resource
    private TaskMapper taskMapper;
    //cron表达式
    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional
    public void singleThreadTest1() {
        try{
            List<Task> list = new ArrayList<>();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("开始时间："+simpleDateFormat.format(date));
            for(int i= 0;i<10000;i++){
                Integer a = Integer.valueOf(i);
                Task task = new Task();
                task.setTaskNumber(TaskServiceImpl.getOrderIdByUUId());
                task.setTaskName("任务"+a);
                task.setUserName("小"+a);
                task.setTaskKind("英雄联盟");
                if(i%2==0){
                    task.setStartRank("白银");
                    task.setEndRank("黄金");
                }else{
                    task.setStartRank("黄金");
                    task.setEndRank("白金");
                }
                task.setMoney(BigDecimal.valueOf(i));
                task.setStatus("1");
                task.setStartTime(date);
                task.setEndTime(date);
                list.add(task);
            }
            taskMapper.addList(list);
            Date date1 =new Date();
            System.out.println("结束时间："+simpleDateFormat.format(date1));
            System.out.println("耗时："+((System.currentTimeMillis()-date.getTime())/1000)+"秒");
        }catch(Exception e){
            e.printStackTrace();
        }

    }*/
}
