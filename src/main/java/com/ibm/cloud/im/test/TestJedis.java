//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.test;

import com.ibm.cloud.im.server.service.infrastructure.RedisMessageHandler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {
    //redis服务器主机    
   static String host = "9.115.42.112";    
   //端口号    
   static int port = 6379;     
       
   public static void main(String[] args) {   
	   JedisPoolConfig config = new JedisPoolConfig();
	   JedisPool pool =  new JedisPool(config, host, port, 0);
	   Jedis jedis =  pool.getResource();
       //根据redis主机和端口号实例化Jedis对象    
       //Jedis jedis = new Jedis(host, port);    
       //添加key-value对象，如果key对象存在就覆盖该对象    
       /*jedis.set("name", "xmong");    
       //查取key的value值，如果key不存在返回null    
       String value = jedis.get("name");    
       System.out.println(value);    
       //删除key-value对象，如果key不存在则忽略此操作    
       jedis.del("name");    
       //判断key是否存在，不存在返回false存在返回true    
       jedis.exists("name"); */
	   RedisMessageHandler subscriber = new RedisMessageHandler(jedis);

       new Thread(new Runnable() {
           @Override
           public void run() {
        	   while(true)
        	   {

        		   try {
	               	jedis.subscribe(subscriber, "XXXX", "YYYY");
	               } catch (Exception e) {
	               	System.out.println(e); 
	               }
        		   try {
        			   Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        		   
        	   }
           }
       }).start(); 	
       
       new Thread(new Runnable() {
           @Override
           public void run() {
        	   while(true)
        	   {

        		   try {
	               	String topic="";
	               	if (!topic.isEmpty())
	               	{
	               		
	               		subscriber.unsubscribe("XXXX");
	               		subscriber.unsubscribe("UUUU");
	               	}
	               } catch (Exception e) {
	               	System.out.println(e); 
	               }
        		   try {
        			   Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        		   
        	   }
           }
       }).start(); 	       
   }    
}
