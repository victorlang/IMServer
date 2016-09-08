//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisConfiguration {
    @Value("${redis.host}")
    public String host;
    @Value("${redis.port}")
    public int port;
    @Bean
            JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
            JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, 0);
    }

    @Bean
    @Scope("prototype")
            Jedis jedis() {
    	Jedis jedis =  jedisPool().getResource();
    	//jedis.subscribe(pubsub, "NOBODY");
    	/*RedisMessageHandler subscriber = redisMessageHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	Jedis jedis = new Jedis(host, port);
                	jedis.subscribe(subscriber, "XXXX", "YYYY");
                } catch (Exception e) {
                	System.out.println(e); 
                	jedis.close();
                }
                jedis.close();
            }
        }).start();  */  	
    	return jedis;
    }
}
