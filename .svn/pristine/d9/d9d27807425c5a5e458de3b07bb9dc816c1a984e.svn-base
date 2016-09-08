//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.test;


import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.cloud.im.config.SpringMongoConfig;
import com.ibm.cloud.im.model.data.KVStore.TopicGroup;
import com.ibm.cloud.im.model.data.KVStoredao.*;
import com.ibm.cloud.im.model.response.BaseOutputJson;
import com.ibm.cloud.im.model.response.IMNewMsgEvent;
import com.ibm.cloud.im.model.response.SubscribeInitEvent;
import com.ibm.cloud.im.model.response.TopicGroupEvent;
import com.ibm.cloud.im.model.rquest.MessageRequest;
import com.ibm.cloud.im.model.rquest.TopicGroupRequest;
import com.ibm.cloud.im.server.service.business.TopicAndGroupService;
import com.ibm.cloud.im.server.service.business.UnifyMsgService;

import redis.clients.jedis.Jedis;


@Controller
public class TestTopicGroupDao {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
    @Autowired
    private Jedis jedis;
    
    @Autowired
    private TopicAndGroupService topicGroupService;
	
    @Autowired
    private UnifyMsgService unifyMsgService;
    
    @Autowired
    private TopicGroupDao TopicGroupDao;
    
}
