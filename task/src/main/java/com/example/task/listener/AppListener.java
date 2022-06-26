package com.example.task.listener;

import com.example.task.redisUtil.redisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Slf4j
@Component("StartupListener")
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private redisService<String,Object> redisService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            //root application context 没有parent，他就是老大.
            // 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            log.info("项目启动开始加载配置...");
            redisService.redisPut("redis_limit_time",24);
            log.info("配置redis默认时间24小时成功...");
            redisService.redisPut("1234","5678");
            redisService.redisPutNoTime("1234-1","5678-1");
            log.info("redis缓存的1234值为"+redisService.redisGet("1234"));
            log.info("redis缓存的1234值为"+redisService.redisGet("1234-1"));
            log.info("redis缓存的1234时间为"+redisService.redisGetTimeByKey("1234"));
            log.info("redis缓存的1234-1时间为"+redisService.redisGetTimeByKey("1234-1"));
          }
    }
}
