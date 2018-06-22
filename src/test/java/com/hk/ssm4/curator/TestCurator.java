package com.hk.ssm4.curator;



import java.util.concurrent.CountDownLatch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * 测试可重入锁（可以多次获得锁不会被阻塞，释放时也需释放多把锁）
 * @author 76524
 *
 */
public class TestCurator{
		/** zookeeper地址 */
	    static final String CONNECT_ADDR = "127.0.0.1:2181";
	    /** session超时时间 */
	    static final int SESSION_OUTTIME = 5000;
	    
	    static int count = 10;
	    public static void genarNo(){
	        try {
	            count--;
	            System.out.println(count);
	        } finally {
	        
	        }
	    }
	    
	    public static void main(String[] args) throws Exception {
	        
	        //1 重试策略：初试时间为1s 重试10次
	        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
	        //2 通过工厂创建连接
	        CuratorFramework cf = CuratorFrameworkFactory.builder()
	                    .connectString(CONNECT_ADDR)
	                    .sessionTimeoutMs(SESSION_OUTTIME)
	                    .retryPolicy(retryPolicy)
//	                    .namespace("super")
	                    .build();
	        //3 开启连接
	        cf.start();
	        
	        //4 分布式锁
	        final InterProcessMutex lock = new InterProcessMutex(cf, "/super");
	       /* final CountDownLatch countdown = new CountDownLatch(1);
	        
	        for(int i = 0; i < 10; i++){
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    try {
	                        countdown.await();
	                        //加锁
	                        lock.acquire();
	                        //-------------业务处理开始
	                        genarNo();
	                        //-------------业务处理结束
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    } finally {
	                        try {
	                            //释放
	                            lock.release();
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            },"t" + i).start();
	        }
	        countdown.countDown();*/
	        lock.acquire();
	        lock.release();
	        CloseableUtils.closeQuietly(cf);
	    }
}