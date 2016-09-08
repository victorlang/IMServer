//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.exit.service.UserAccountsMgr;
import com.ibm.cloud.im.model.rquest.MessageRequest;
import com.ibm.cloud.im.model.rquest.TopicGroupRequest;
import com.ibm.cloud.im.server.service.infrastructure.KafkaSimpleProducer;
import com.ibm.cloud.im.server.service.infrastructure.SessionMgr;

@Controller
public class TopicGroupController {
	private static Log logger = LogFactory.getLog(TopicGroupController.class);
	@Autowired
	private ControllerService service;
	
    @Autowired
    SessionMgr sessionMgr;
    
    @Autowired
    UserAccountsMgr accountMgr;
    @Autowired
    KafkaSimpleProducer kafkaHandler;

    //创建一个讨论组
    @MessageMapping("/createDiscussGroup")
    public void createDiscussGroup(SimpMessageHeaderAccessor accessor, TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	request.setTopicType(IMConstants.typeIM);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("createGroup");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
    		service.createGroup(request);
    	}
    }    
    @MessageMapping("/createCaseGroup")
    public void createCaseGroup(SimpMessageHeaderAccessor accessor, TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	request.setTopicType(IMConstants.typeCaseSubcriber);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("createGroup");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
    		service.createGroup(request);
    	}
    }
    @MessageMapping("/createFileGroup")
    public void createFileGroup(SimpMessageHeaderAccessor accessor, TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	request.setTopicType(IMConstants.typeFileSubscriber);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("createGroup");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
    		service.createGroup(request);
    	}
    }
    
    @RequestMapping(value = "/IMServer/createDiscussGroup", method= RequestMethod.POST)
    public @ResponseBody void createGroupREST(@RequestBody TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	createDiscussGroup(null, request);
    }
    @RequestMapping(value = "/IMServer/createCaseGroup", method= RequestMethod.POST)
    public @ResponseBody void createCaseREST(@RequestBody TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	createCaseGroup(null, request);
    }
    @RequestMapping(value = "/IMServer/createFileGroup", method= RequestMethod.POST)
    public @ResponseBody void createFileREST(@RequestBody TopicGroupRequest request/*,@AuthenticationPrincipal User op*/)
    {
    	createFileGroup(null, request);
    }    

    //主题改名字
    public void updateTopicName(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setOperatorId(sessionMgr.getOperatorName(accessor)); 
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("updateTopicName");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.updateTopicName(request);
    	}
    	
    } 
    @MessageMapping("/DiscussGroupRename")
    public void updateDiscussTopicName(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	updateTopicName(accessor, request);
    }
    
    @RequestMapping(value = "/IMServer/DiscussGroupRename", method= RequestMethod.POST)
    public @ResponseBody void updateTopicNameREST(@RequestBody TopicGroupRequest request)
    {
    	updateTopicName(null, request);
    } 

    //主题的创建者删除主题。
    @MessageMapping("/removeDiscussGroup")
    public void removeTopic(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("removeTopic");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.removeTopic(request);
    	}    	

    }  
    @RequestMapping(value = "/IMServer/removeDiscussGroup", method= RequestMethod.POST)
    public  @ResponseBody void removeTopicREST(@RequestBody TopicGroupRequest request)
    {
    	removeTopic(null, request);
    }

    //用来为已经存在的一个讨论组添加成员。
    @MessageMapping("/addDiscussGroupUser")
    public void addDiscussGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeIM);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("addTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.addTopicGroupUser(request);
    	}      	

    }
    @MessageMapping("/addCaseGroupUser")
    public void addCaseGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeCaseSubcriber);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("addTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.addTopicGroupUser(request);
    	}     	
    }
    @MessageMapping("/addFileGroupUser")
    public void addFileGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeFileSubscriber);
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("addTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.addTopicGroupUser(request);
    	} 
    }
    
    @RequestMapping(value = "/IMServer/addDiscussGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void addTopicGroupUserREST(@RequestBody TopicGroupRequest request)
    {
    	addDiscussGroupUser(null, request);
    }    
    @RequestMapping(value = "/IMServer/addCaseGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void addCaseGroupUserREST(@RequestBody TopicGroupRequest request)
    {
    	addCaseGroupUser(null, request);
    }
    @RequestMapping(value = "/IMServer/addFileGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void addFileGroupUserREST(@RequestBody TopicGroupRequest request)
    {
    	addFileGroupUser(null, request);
    }

    //用来为已经存在的一个讨论组踢人。
    @MessageMapping("/removeDiscussGroupUser")
    public void removeDicussGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeIM);
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));    
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("removeTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.removeTopicGroupUser(request);
    	}     	

    }
    @MessageMapping("/removeCaseGroupUser")
    public void removeCaseGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeCaseSubcriber);
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));    
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("removeTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.removeTopicGroupUser(request);
    	}         	

    }
    @MessageMapping("/removeFileGroupUser")
    public void removeFileGroupUser(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setTopicType(IMConstants.typeFileSubscriber);
    	request.setUserList(accountMgr.getValidatedUsers(request.getUserList()));
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));    	
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("removeTopicGroupUser");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.removeTopicGroupUser(request);
    	}    
    }    
    @RequestMapping(value = "/IMServer/removeDiscussGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void removeDicussGroupUser(@RequestBody TopicGroupRequest request)
    {
    	
    	removeDicussGroupUser(null, request);
    }       
    @RequestMapping(value = "/IMServer/removeCaseGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void removeCaseGroupUser(@RequestBody TopicGroupRequest request)
    {
    	
    	removeCaseGroupUser(null, request);
    } 
    @RequestMapping(value = "/IMServer/removeFileGroupUser", method= RequestMethod.POST)
    public  @ResponseBody void removeFileGroupUser(@RequestBody TopicGroupRequest request)
    {
    	
    	removeFileGroupUser(null, request);
    } 

    @MessageMapping("/queryTopicObjsByName")
    public void queryTopicObjsByName(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if (request.getTopicName() == null)
    		return;
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryTopicObjsByName");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.queryTopicObjsByName(request);
    	}        	

    }

    @MessageMapping("/queryTopicObjsByObjectId")
    public void queryTopicObjsByObjectId(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if (request.getObjectId() == null)
    		return;
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryTopicObjsByObjectId");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.queryTopicObjsByObjectId(request);
    	}        	
   	
    }    
    @MessageMapping("/queryTopicObjsByTopicId")
    public void queryTopicObjsByTopicId(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if (request.getObjectId() == null)
    		return;
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryTopicObjsByTopicId");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.queryTopicObjsByTopicId(request);
    	}        	
   	
    } 
    /**
     * 查询个人用户加入的讨论组
     * @param accessor
     * @param request
     * @return
     */
    @MessageMapping("queryUserBookedDiscussGroup")
    public void queryUserBookedDiscussGroup(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {    
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryUserBookedDiscussGroup");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.queryUserBookedDiscussGroup(request);
    	}     	
    }
    @RequestMapping(value = "/IMServer/queryUserBookedDiscussGroup", method= RequestMethod.POST)
    public @ResponseBody void queryUserBookedDiscussGroupRest(@RequestBody MessageRequest request)
    {
    	queryUserBookedDiscussGroup(null, request);
    }
    
    @MessageMapping("addRefTopicGroupIds")
    public void addRefTopicGroupIds(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {    
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("addRefTopicGroupIds");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.addRefTopicGroupIds(request);
    	}     	
    }
    @RequestMapping(value = "/IMServer/addRefTopicGroupIds", method= RequestMethod.POST)
    public @ResponseBody void addRefTopicGroupIds(@RequestBody TopicGroupRequest request)
    {
    	addRefTopicGroupIds(null, request);
    }    

    @MessageMapping("removeRefTopicGroupIds")
    public void removeRefTopicGroupIds(SimpMessageHeaderAccessor accessor,TopicGroupRequest request)
    {    
    	request.setOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("removeRefTopicGroupIds");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{
        	service.removeRefTopicGroupIds(request);
    	}     	
    }
    @RequestMapping(value = "/IMServer/removeRefTopicGroupIds", method= RequestMethod.POST)
    public @ResponseBody void removeRefTopicGroupIds(@RequestBody TopicGroupRequest request)
    {
    	removeRefTopicGroupIds(null, request);
    }      
}
