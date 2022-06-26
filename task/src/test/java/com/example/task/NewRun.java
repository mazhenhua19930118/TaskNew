package com.example.task;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NewRun implements Runnable{
    /**
     * 在Runnable @Autowired注入会null  所以需要手动注入
     */
    private TaskMapper taskMapper=SpringContextUtils.getApplicationContext().getBean(TaskMapper.class);
    /**
     * 100条为分界批量导入
     */
    private int batch100 =100;
    /**mysql数据*/
    private List<Task> list;
    /**线程池*/
    private ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2,
                    Runtime.getRuntime().availableProcessors(),
                    2L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );

    public NewRun(List<Task> list) {
        this.list = list;
    }

    @Override
    public void run() {
        batchOp(list);
    }
    private void batchOp(List<Task> list) {
        System.out.println("我是线程："+Thread.currentThread().getName());
        if(!list.isEmpty()){
            Integer size = list.size();
            if(size<=batch100){
                taskMapper.addList(list);
            }else if(size>batch100){
                batchOpSpilit(list,batch100);
            }
        }
    }
    //集合切割类，就是把一个大集合切割成多个指定条数的小集合，方便往数据库插入数据
    public static <T> java.util.List<java.util.List<T>> pagingList(java.util.List<T> list, int pageSize){
        int length = list.size();
        int num = (length+pageSize-1)/pageSize;
        java.util.List<java.util.List<T>> newList =  new ArrayList<>();
        for(int i=0;i<num;i++){
            int fromIndex = i*pageSize;
            int toIndex = (i+1)*pageSize<length?(i+1)*pageSize:length;
            newList.add(list.subList(fromIndex,toIndex));
        }
        return newList;
    }
    //切割
    private void batchOpSpilit(List<Task> list, int batch100) {
        System.out.println("开始切割………………");
        Long t1 = System.currentTimeMillis();
        java.util.List<java.util.List<Task>> list1 = pagingList(list,batch100);
        try {
            for(java.util.List<Task> list2:list1){
                //再调batchOp方法，这里的多线程是多个小集合往数据库插
                threadPoolExecutor.execute(()->{
                    System.out.println("我是线程："+Thread.currentThread().getName() );
                    batchOp(list2);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
            Long t2 = System.currentTimeMillis();
            System.out.println("执行完成,用时…………"+(t2-t1));
        }
    }


}
