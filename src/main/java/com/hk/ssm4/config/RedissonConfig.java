package com.hk.ssm4.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.LocalCachedMapOptions.EvictionPolicy;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableCaching
public class RedissonConfig {

	@Bean(destroyMethod="shutdown")
    RedissonClient redisson() throws IOException {
		Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		//config.useMasterSlaveServers().setMasterAddress("redis://127.0.0.1:63791").addSlaveAddress("redis://127.0.0.1:63792","redis://127.0.0.1:63793");
		//config.useSentinelServers().setMasterName("mymaster").addSentinelAddress("redis://127.0.0.1:32769", "redis://127.0.0.1:32770", "redis://127.0.0.1:32771");
		RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
    	Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        config.put("testMap", new CacheConfig(60000, 600000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}

