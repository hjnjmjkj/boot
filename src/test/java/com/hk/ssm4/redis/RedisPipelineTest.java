package com.hk.ssm4.redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RedisPipelineTest {

	    public static void main(String[] args) {
	        String redisIP = "127.0.0.1";
	        int redisPort = 6379;
	        Jedis jedis;
	        try {
	            jedis = new Jedis(redisIP, redisPort);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            System.out.printf("初始化Redis连接错误:%s, %d", redisIP, redisPort);
	            return;
	        }

	        jedis.flushDB();
	        long start = System.currentTimeMillis();
	        notusePipeline(jedis);
	        long end = System.currentTimeMillis();
	        System.out.printf("不使用Pipeline的方式用时:%d毫秒", end-start);

	        start = System.currentTimeMillis();
	        usePipeline(jedis);
	        end = System.currentTimeMillis();
	        System.out.printf("使用Pipeline的方式用时:%d毫秒", end-start);
	        jedis.sdiff("myset1","myset2").forEach(System.out::println);

	    }

	    private static void notusePipeline(Jedis jedis) {
	        try {
	            Pipeline pl = jedis.pipelined();
	            for (int i=0; i<1000008; i++) {
	                pl.sadd("myset1",i+"");
	            }
	            pl.sync();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void usePipeline(Jedis jedis) {
	        try {
	            Pipeline pl = jedis.pipelined();
	            for (int i=0; i<1000000; i++) {
	                pl.sadd("myset2",i+"");
	            }
	            pl.sync();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
