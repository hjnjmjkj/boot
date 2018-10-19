package com.hk.ssm4.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	private RedissonClient redissonClient;
	@Autowired
	private CacheManager cacheManager;
	@Cacheable(value="testMap",key="1")
	public String test(){
		return "hk";
	}
	public String getKey(){
		return cacheManager.getCache("testMap").get(1).get().toString();
	}
	public void lock() throws InterruptedException{
		RLock lock = redissonClient.getLock("anyLock");
		// Most familiar locking method
		lock.lock();
		Thread.sleep(10000);
		lock.unlock();
	}
	public void lock2(){
		RLock lock = redissonClient.getLock("anyLock");
		// Most familiar locking method
		lock.lock();
		
		lock.unlock();
	}
	public void redissonClientTest(){
		System.out.println(redissonClient.getMap("testMap").get("1"));
	}
	public void testBlockQueue() throws InterruptedException {
		RBlockingQueue<SomeObject> queue = redissonClient.getBlockingQueue("anyQueue");
		queue.offer(new SomeObject("123"));

		SomeObject obj = queue.peek();
		SomeObject someObj = queue.poll();
		SomeObject ob = queue.poll(10, TimeUnit.MINUTES);
		System.out.println(ob.name);
	}
	public static class SomeObject{
		private String name;
		
		public SomeObject() {
			super();
		}

		public SomeObject(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
