package com.example.task.service.impl;
import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.result.Result;
import com.example.task.service.TaskService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public Result add(Task task) {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("kk","333");
        String a =valueOperations.get("kk");
        task.setStatus("0");
        task.setTaskNumber(TaskServiceImpl.getOrderIdByUUId());
        int count = taskMapper.add(task);
        if (1 == count) {
            return new Result("200", "创建成功", a);
        } else {
            return new Result("100", "创建失败", a);
        }

    }

    @Override
    public Result findAll(String status) {
        if("first".equals(status)){
            status="";
        }else if("second".equals(status)){
            status="0";
        }else{
            status="1";
        }
        List<Task> taskList = taskMapper.findAll(status);
        Result result = new Result();
        if (!taskList.isEmpty()) {
            result.setData(taskList);
            result.setCode("200");
            result.setMessage("查询成功");
        } else {
            result.setData("");
            result.setCode("100");
            result.setMessage("未查询到数据");
        }
        return result;
    }

    @Override
    public Result pass(String status, String taskNumber) {
        int count =taskMapper.pass(status,taskNumber);
        if (1 == count) {
            if("0".equals(status)){
                return new Result("200", "驳回通过", "");
            }else{
                return new Result("200", "审核通过", "");
            }

        } else {
            if("0".equals(status)){
                return new Result("200", "驳回失败", "");
            }else{
                return new Result("200", "审核失败", "");
            }
        }
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
}
