package com.redis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Ivan on 2016/7/8.
 */
public class RedisJava {

    public static RedisConnectionFactory jedisConnectionFactory() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        JedisConnectionFactory jedisConnetionFactory = (JedisConnectionFactory) ctx
                .getBean("connectionFactory");
        return jedisConnetionFactory;
    }

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
        jedis.set("w3ckey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: " + jedis.get("w3ckey"));

        //StringRedisTemplate只支持string类型
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        RedisBaseCacheDao rbc = new RedisBaseCacheDao(stringRedisTemplate);
        rbc.setScore("402", "ss", 22.0);
        rbc.setScore("402", "ss1", 23.0);
        rbc.setScore("402", "ss2", 2.0);
        rbc.setScore("402", "ss3", 32.0);
        rbc.setScore("402", "ss4", 12.0);

        Map<String, Double> map = rbc.getTopMembers("402", 3l);//按一定顺序获取前三名数据
        List<SortedSetModel> list = new ArrayList<SortedSetModel>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            SortedSetModel ssm = new SortedSetModel();
            ssm.setMember(entry.getKey());
            ssm.setScore(entry.getValue());
            list.add(ssm);
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Collections.sort(list);

        for (SortedSetModel ssm : list) {
            System.out.println(ssm);
        }
        // ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();

        //可以持久化各种类型的key和value，并不仅限于字节数组
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        /*
        * 使用RedisTemplateAPI
方法	子API接口	描述
opsForValue()	ValueOperations 	描述具有简单值的条目
opsForList()	ListOperations 	操作具有list值的条目
opsForSet()	SetOperations 	操作具有set值的条目
opsForZSet()	ZSetOperations 	操作具有ZSet值（排序的set）的条目
opsForHash()	HashOperations 	操作具有hash值的条目
boundValueOps(K)	BoundValueOperations 	以绑定指定key的方式，操作具有简单值的条目
boundListOps(K)	BoundListOperations 	以绑定指定key的方式，操作具有list的条目
boundSetOps(K)	BoundSetOperations 	以绑定指定key的方式，操作具有set的条目
boundZSet(K)	BoundZSetOperations 	以绑定指定key的方式，操作具有ZSet（排序的set）的条目
boundHashOps(K)	BoundHashOperations 	以绑定指定key的方式，操作具有hash值的条目
        * */
        redisTemplate.opsForValue().set("ssd", "guoruan");
        System.out.println(redisTemplate.opsForValue().get("ssd"));


//        BoundListOperations<String, Object> list2 = redisTemplate.boundListOps("numbers");
        ListOperations<String,Object> list2 = redisTemplate.opsForList();
        list2.rightPush("number","0");
//        list2.index(100l);
//        System.out.println("size:" + list2.size());
//        //list2.leftPop();
//        list2.set(0l, "2");//set操作是根据索引改变值
//        list2.rightPush("3");//在其后添加一个值，也可以在链表的前面加一个值
//        list2.rightPush("4");
        List<Object> list1 = redisTemplate.opsForList().range("number", 0l, 8l);
        for (Object o : list1) {
            System.out.println(o);
        }

        HashOperations<String,String,Object> hashOperations=redisTemplate.opsForHash();
        Map<String,Object> students = new HashMap<String, Object>();
        students.put("name","yingzheng");
        students.put("age",38);
        students.put("gender",true);
        hashOperations.put("student","001","ssd");
        hashOperations.put("student","002","ssd1");
        hashOperations.put("student","003",students);
        Map<String,Object>map1=hashOperations.entries("student");
        for(Map.Entry<String,Object> entry:map1.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }
}
