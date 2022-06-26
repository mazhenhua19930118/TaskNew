package com.example.task;

import com.example.task.entity.Task;

import com.example.task.entity.User;
import com.example.task.mapper.ShardingMapper;
import com.example.task.mapper.TaskMapper;
import com.example.task.redisUtil.redisService;
import com.example.task.service.impl.TaskServiceImpl;
import com.example.task.sharding.UserSharding;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TaskApplicationTests {
    @Resource
    private redisService<String, Object> redisService;

    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALGORITHM_INFO = "PBEWithMD5AndDES";

    private static final String PASSWORD_INFO = "Za:8ei_Pa5swd_gp";
    @Autowired
    private StringEncryptor stringEncryptor;
    @Resource
    private ShardingMapper shardingMapper;


    @Resource
    private TaskMapper taskMapper;

    @Test
    void contextLoads() {
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = new User();
        user.setName("aa");
        user.setPassword("2323");
        valueOperations.set("123", user);
        User a = valueOperations.get("123");
        System.out.println(a);
    }

    /**
     * 精度0.00
     */
    @Test
    void aa() {
        String a = "8";
        System.out.println(a);
        BigDecimal bd = new BigDecimal(a);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        a = bd.toString();
        System.out.println("输出为" + a);
    }

    /**
     * split  '|'
     */
    @Test
    void a1() {
        String a = "";
        String[] newarr = a.split("[|]");
        System.out.println("输出为" + newarr);
        for (int i = 0; i < newarr.length; i++) {
            System.out.println("输出为" + newarr[i]);
        }

    }


    /**
     * jasypt加密
     */
    @Test
    public void encryptPwd() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        //配置文件中配置如下的算法
        standardPBEStringEncryptor.setAlgorithm(ALGORITHM_INFO);
        //配置文件中配置的password
        standardPBEStringEncryptor.setPassword(PASSWORD_INFO);
        //要加密的文本
        String name = standardPBEStringEncryptor.encrypt("root");
        String password = standardPBEStringEncryptor.encrypt("root");
        String redisPassword = standardPBEStringEncryptor.encrypt("123456");
        //将加密的文本写到配置文件中
        System.out.println("name=" + name);
        System.out.println("password=" + password);
        System.out.println("redisPassword=" + redisPassword);

      /*  //要解密的文本
        String name2 = standardPBEStringEncryptor.decrypt("FarrmxSQX5uwtH/NZRxy+g==");
        String password2 = standardPBEStringEncryptor.decrypt("vhiaYB1gl9zPj16yu7uMkA==");
        String redisPassword2 = standardPBEStringEncryptor.decrypt("ZII7UphhbVuJ8c3oxPUeyw==");
        //解密后的文本
        System.out.println("name2=" + name2);
        System.out.println("password2=" + password2);
        System.out.println("redisPassword2=" + redisPassword2);*/

    }

    @Test
    public void jasypt() {
        //加密方法
        System.out.println(stringEncryptor.encrypt("Za:8ei_Pa5swd_gp"));
        System.out.println(stringEncryptor.encrypt("Za:8ei_Pa5swd_gp"));
       /* //解密方法
        System.out.println(stringEncryptor.decrypt("uaNBj4ZmzCD83uedRYUXqQ=="));
        System.out.println(stringEncryptor.decrypt("oKBQENfbbQiMyPvECAgPGA=="));*/
    }

    /**
     * http请求
     */
    @Test
    public void http() throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("https://open.douyin.com/video/list");
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        //设置参数
        postMethod.setParameter("open_id", "ccbd2d16-e65b-4e18-ae73-22ec04d2135c");
        postMethod.setParameter("access_token", "act.e9caa63e16b51bf19a46024b90b6450c3KSoo8RW6GkZpQW0P44bUdBCLPb8");
        postMethod.setParameter("count", "10");
        postMethod.setParameter("cursor", "0");
        //执行，返回一个结果码
        int code = httpClient.executeMethod(postMethod);
        System.out.println("code = " + code);
        //获取结果
        String result = postMethod.getResponseBodyAsString();
        System.out.println("result = " + result);
        //释放资源
        postMethod.releaseConnection();
        ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
    }

    /**
     * try catch() finally()
     */
    @Test
    public void trycatch() {
        int a = 0;
        String s = "t";
        try {
            a = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.print("1");
            a = Integer.parseInt(s);
        } finally {
            try {
                System.out.print("2");
                a = Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("3");
                a = Integer.parseInt(s);
            } finally {
                System.out.print("4");
            }
        }
    }

    /**
     * java8 stream().map().collect()用法
     */
    @Test
    public void streamMap() {
        /*List users = getList(); //从数据库查询的用户集合

        现在想获取User的身份证号码；在后续的逻辑处理中要用；

        常用的方法我们大家都知道，用for循环，

        List idcards=new ArrayList();//定义一个集合来装身份证号码

        for(int i=0;i<users.size();i++){
            idcards.add(users.get(i).getIdcard());

        }

        这种方法要写好几行代码，有没有简单点的，有，java8 API能一行搞定：

        List idcards= users.stream().map(User::getIdcard).collect(Collectors.toList())

        解释下一这行代码：

        users：一个实体类的集合，类型为List
        User：实体类
        getIdcard：实体类中的get方法，为获取User的idcard

        stream()优点

        无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
        为函数式编程而生。对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
        惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
        可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
        stream().map()方法的使用示例:

        再看几个例子：数组字母小写变大写
        List list= Arrays.asList(“a”, “b”, “c”, “d”);

        List collect =list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect); //[A, B, C, D]

        数组所有元素，按某种规律计算：
        List num = Arrays.asList(1,2,3,4,5);
        List collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(collect1); //[2, 4, 6, 8, 10]*/

    }

    @Test
    public void radomnextInt() {
        SecureRandom random = new SecureRandom();
        random.nextInt(4);
        System.out.println("......" + random.nextInt(4)); //[2, 4, 6, 8, 10]*/
    }

    @Test
    public void insert() {
        int maxmumPoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, maxmumPoolSize, 2L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 750849; i <= 5000000; i++) {
            threadPoolExecutor.execute(new Run(i));
        }

    }

    @Test
    public void insert2() {
        Date date = new Date();
        Task task = new Task();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1000; i < 5000000; i++) {
            Integer a = Integer.valueOf(i);
            task.setTaskNumber(TaskServiceImpl.getOrderIdByUUId());
            task.setTaskName("任务" + a);
            task.setUserName("小" + a);
            task.setTaskKind("英雄联盟");
            if (i % 2 == 0) {
                task.setStartRank("白银");
                task.setEndRank("黄金");
            } else {
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
        System.out.println("结束时间：" + simpleDateFormat.format(date));
        System.out.println("耗时：" + ((System.currentTimeMillis() - date.getTime()) / 1000) + "秒");

    }

    //多线程批量插入数据库500万条
    @Test
    public void insertNew() {
        List<Task> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(date));
        for (int i = 10000; i < 200000; i++) {
            Integer a = Integer.valueOf(i);
            Task task = new Task();
            task.setTaskNumber(TaskServiceImpl.getOrderIdByUUId());
            task.setTaskName("任务" + a);
            task.setUserName("小" + a);
            task.setTaskKind("英雄联盟");
            if (i % 2 == 0) {
                task.setStartRank("白银");
                task.setEndRank("黄金");
            } else {
                task.setStartRank("黄金");
                task.setEndRank("白金");
            }
            task.setMoney(BigDecimal.valueOf(i));
            task.setStatus("1");
            task.setStartTime(date);
            task.setEndTime(date);
            list.add(task);
        }
        Date date1 = new Date();
        System.out.println("结束时间：" + simpleDateFormat.format(date1));
        System.out.println("耗时：" + (System.currentTimeMillis() - date.getTime()) + "秒");

        int maxmumPoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, maxmumPoolSize, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(new NewRun(list));
        System.out.println("插入结束");
    }

    @Test
    public void a() {
        List<Task> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(date));
        for (int i = 0; i < 10000; i++) {
            Integer a = Integer.valueOf(i);
            Task task = new Task();
            task.setTaskNumber(TaskServiceImpl.getOrderIdByUUId());
            task.setTaskName("任务" + a);
            task.setUserName("小" + a);
            task.setTaskKind("英雄联盟");
            if (i % 2 == 0) {
                task.setStartRank("白银");
                task.setEndRank("黄金");
            } else {
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
        Date date1 = new Date();
        System.out.println("结束时间：" + simpleDateFormat.format(date1));
        System.out.println("耗时：" + ((System.currentTimeMillis() - date.getTime()) / 1000) + "秒");
    }

    @Test
    public void bacth() {
        RunTwo run = new RunTwo();
        Thread thread1 = new Thread(run, "线程1");
        thread1.start();

    }

    @Test
    public void time() {
        Timer timer = new Timer();
        timer.schedule(new CommitTimer(), 10 * 1000, 5 * 60 * 1000);

    }

    //生成sql数据文件导入mysql
    @Test
    public void sql() throws IOException {
        //SHOW VARIABLES LIKE 'local_infile';
        //set global local_infile=ON;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:/Task_new/Task/t_task.sql"), true));
        for (int i = 0; i < 5000000; i++) {
            writer.write(i + "\t" + "哈哈");
        }
        writer.close();
    }

    //sharding分表
    @Test
    public void shardingInsert() {
        UserSharding user1 = new UserSharding();

        user1.setAge(10);
        user1.setUsername("张三");
        user1.setBirthday(new Date());
        user1.setCmd("张三今年10岁");
        UserSharding user2 = new UserSharding();
        shardingMapper.shardingInsert(user1);
        user2.setAge(20);
        user2.setUsername("李四");
        user2.setBirthday(new Date());
        user2.setCmd("李四今年20岁");
        shardingMapper.shardingInsert(user2);

    }

    //sharding分库分表
    @Test
    public void shardingInsertDateBase() {
        UserSharding user1 = new UserSharding();
        user1.setAge(10);
        user1.setUsername("张三");
        user1.setBirthday(new Date());
        user1.setCmd("张三今年10岁");
        UserSharding user2 = new UserSharding();
        shardingMapper.shardingInsertDateBase(user1);
        user2.setAge(20);
        user2.setUsername("李四");
        user2.setBirthday(new Date());
        user2.setCmd("李四今年20岁");
        shardingMapper.shardingInsertDateBase(user2);

    }
    //sharding读写分离
    @Test
    public void shardingSelect() {
        UserSharding user1 = new UserSharding();
        user1.setAge(10);
        user1.setUsername("王五");
        user1.setBirthday(new Date());
        user1.setCmd("王五今年10岁");
        shardingMapper.shardingInsertDateBase(user1);
        UserSharding user2 = new UserSharding();
        user2.setUsername("李四");
        UserSharding user3 = shardingMapper.select(user2);
        System.out.println(user3.getUsername());
    }
}
