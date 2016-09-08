//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.infrastructure;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.ibm.cloud.im.config.RedisConfiguration;
import com.ibm.cloud.im.constant.IMConstants;


@Component
public class RedisMq2LocalMq {
	
	
	@Autowired
	RedisConfiguration config;
	
	@Autowired
	RedisMq2LocalMq(SimpMessagingTemplate webSocketMsgHandler, RedisMessageHandler subscriber)
	{
        new Thread(new Runnable() {
            @Override
            public void run() {
            	Jedis jedis=null;
                try {
                	Thread.sleep(2500);
                	InetAddress addr = InetAddress.getLocalHost();
                	String ip = addr.getHostAddress();                	
                	jedis = new Jedis(config.host, config.port);
                	System.out.println("brokerIP"+ip +" redis subscriber thread Connected to Redis");
                	subscriber.setMsgHandler(webSocketMsgHandler);
                	jedis.subscribe(subscriber, IMConstants.IMServersBroadCastMsg);
                } catch (Exception e) {
                	e.printStackTrace(); 
                }
            	if (jedis != null)
            		jedis.close();
            }
        }).start(); 		
	}
	
}
