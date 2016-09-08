//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.infrastructure;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessageMappingInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.response.TopicGroupEvent;
import com.ibm.cloud.im.server.utils.SerializeCompTools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@Component
public class RedisMessageHandler extends JedisPubSub {
	public final static Log logger = LogFactory.getLog(RedisMessageHandler.class);
	private SimpMessagingTemplate messagingTemplate;
    @Autowired
    SessionMgr sessionMgr;
    

	public RedisMessageHandler()
	{
		
	}
	public RedisMessageHandler (Jedis jedis)
	{
	}
	public void setMsgHandler(SimpMessagingTemplate handle)
	{
		messagingTemplate=handle;
	}
    @Override
    public void onMessage( String channel, String message ) {
        System.out.println(String.format("Redis Message received. Channel: %s, Msg: %s", channel, message));
        try
        {
        	if(channel.equals(IMConstants.IMServersBroadCastMsg))
	        {
	        	//向云内所有服务器广播踢人， 等系统消息， 只被服务器处理的消息， 不会发给客户端。 
	        	JSONObject jsonobject = JSONObject.fromObject(message);
	        	String cmd = (String) jsonobject.get(IMConstants.command);
	        	if (cmd!=null && cmd.equals(IMConstants.kickUserCmd))
	        	{
	        		//由于登录导致的被踢
	        		String userName = (String) jsonobject.get(IMConstants.userName);
	        		String device = (String) jsonobject.get(IMConstants.device);
	        		String sessionId =  (String) jsonobject.get(IMConstants.except4Session);
	        		sessionMgr.kickUserConnections(userName, device, sessionId);
	        	}else
	        	{
	        		//由于讨论组加人导致的unsubScribe
	        		//由sessionMgr里的SendkickUsersCmdMsg触发。
	    	    	System.out.println("Redis Thread: "+ Thread.currentThread().getId());   
	    	    	
	        		TopicGroupEvent evt = (TopicGroupEvent)JSONObject.toBean(jsonobject, TopicGroupEvent.class);
	            	List <String > users = evt.getUserList();
	            	if (users.size()>0)
	            	{
	            		sessionMgr.unRegisterStompDest(evt.getTopicName(), users);
	            	}
	        	}
	        }
	        else if (messagingTemplate!=null)
	        {
	        	//收到的是JSON字符串。
	        	messagingTemplate.convertAndSend(channel, message);
	        }
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    //JedisPubSub 这些不是线程安全的。 所以如果要在另一个线程里取消
    //订阅， 需要特殊处理， 虽然不是每次多线程取消订阅， 或者增加订阅都会有问题， 但必须要做特殊处理
    //避免多线程导致的， 较低发生概率的订阅失败， 和取消订阅失败。
    public synchronized void unSub(String channel)
    {
    	this.unsubscribe(channel);
    }
    public synchronized void sub(String channel)
    {
    	this.subscribe(channel);
    }


}
