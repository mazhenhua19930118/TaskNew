package com.example.task;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.impl.TaskServiceImpl;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RunTwo implements Runnable{
    /*@Resource
    TaskMapper taskMapper;*/
    /**
     * 在Runnable @Autowired注入会null  所以需要手动注入
     */
    private TaskMapper taskMapper=SpringContextUtils.getApplicationContext().getBean(TaskMapper.class);;
    private DataSourceTransactionManager dataSourceTransactionManager=SpringContextUtils.getApplicationContext().getBean(DataSourceTransactionManager.class);
    private TransactionDefinition transactionDefinition=SpringContextUtils.getApplicationContext().getBean(TransactionDefinition.class);;
    @Override
    public void run() {
        insert2();
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

    public  void insert2(){
        List<Task> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+simpleDateFormat.format(date));
        for(int i= 0;i<10;i++){
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
    }

}
