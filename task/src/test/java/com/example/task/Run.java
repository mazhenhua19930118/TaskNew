package com.example.task;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.impl.TaskServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Run implements Runnable{
    private  int i;
    /*@Resource
    TaskMapper taskMapper;*/
    /**
     * 在Runnable @Autowired注入会null  所以需要手动注入
     */
    private TaskMapper taskMapper=SpringContextUtils.getApplicationContext().getBean(TaskMapper.class);

    @Override
    public void run() {
       insert();
    }



    public Run(int i) {
        this.i = i;
    }
    /*用UUID生成十六位数唯一任务序列号*/
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
//         0 代表前面补充0
//         4 代表长度为4
//         d 代表参数为正数型
        return  machineId+ String.format("%015d", hashCodeV);
    }
    public  void insert(){
        Date date = new Date();
        Integer a = Integer.valueOf(i);
        Task task = new Task();
        task.setTaskNumber(getOrderIdByUUId());
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
        int count = taskMapper.add(task);
        if (1 == count) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    public  void insert2(){
        Date date = new Date();
        Integer a = Integer.valueOf(i);
        Task task = new Task();
        List<Task> list = new ArrayList<>();
        for(int i= 0;i<10000;i++){
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
    }


}
