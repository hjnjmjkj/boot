package com.hk.ssm4.retry;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class RetryTest {
	public static void main(String[] args) throws Exception {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		  for (int i = 0; i < 10; i++) {
		   final int index = i;
		   fixedThreadPool.execute(new Runnable() {
		    public void run() {
		     try {
		    	 RetryTest retry=new RetryTest();
		    			 retry.testRetry();
		     } catch (InterruptedException e) {
		      e.printStackTrace();
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		   });
		  }
		  fixedThreadPool.shutdown();
		  /*RetryTest retry=new RetryTest();
			 retry.testRetry();*/
	}
	private void testRetry() throws Exception {
		RetryTemplate template = new RetryTemplate();

		SimpleRetryPolicy retryPolicy =new SimpleRetryPolicy(3);
        template.setRetryPolicy(retryPolicy);
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(1500);
        exponentialBackOffPolicy.setMultiplier(3);
        exponentialBackOffPolicy.setMaxInterval(6000);
        template.setBackOffPolicy(exponentialBackOffPolicy);

		RetryTest.Foo result = template.execute(new RetryCallback<Foo, Exception>() {

			@Override
			public Foo doWithRetry(RetryContext context) throws Exception {
				Foo foo=new Foo();
				foo.setName("hk");
				throw new Exception("异常");
			}
		},new RecoveryCallback<Foo>() {

			@Override
			public Foo recover(RetryContext context) throws Exception {
				Foo foo=new Foo();
				foo.setName("huangkai");
				return foo;
			}
		});
		System.out.println(result.getName());
	}
	public static class Foo implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
