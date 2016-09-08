//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.controller;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.exit.service.UserAccountsMgr;
import com.ibm.cloud.im.model.response.SubscribeInitEvent;
import com.ibm.cloud.im.model.rquest.MessageRequest;
import com.ibm.cloud.im.model.rquest.SendMailRequest;
import com.ibm.cloud.im.model.rquest.SubScriptionRequest;
import com.ibm.cloud.im.server.service.business.UnifyMsgService;
import com.ibm.cloud.im.server.service.infrastructure.KafkaSimpleProducer;
import com.ibm.cloud.im.server.service.infrastructure.SessionMgr;

@Controller
public class UnifyMsgController {
	private static Log logger = LogFactory.getLog(UnifyMsgController.class);
	@Autowired
	private UnifyMsgService messagingTemplate;
	
    @Autowired
    SessionMgr sessionMgr;
    
    @Autowired
    UserAccountsMgr accountMgr;
    
	@Autowired
	private ControllerService service;
	@Autowired
    KafkaSimpleProducer kafkaHandler;
    
    //处理客户端的subcribe订阅事件
    @SubscribeMapping("/{topicId}")
    public boolean subScribeInitWithoutParameter(SimpMessageHeaderAccessor accessor,@DestinationVariable String topicId)
    {
    	
    	String dest = accessor.getDestination();
    	String operator = sessionMgr.getOperatorName(accessor);
    	//在这里查询返回一个讨论组最近的几条消息
    	//使得用户刚刚订阅一个讨论组的时候， 可以看到讨论组最近的几条消息。
    	//在这里连接Redis的消息队列主题。
	    String command = (String) accessor.getHeader("stompCommand").toString();

	    if (command.equalsIgnoreCase("SUBSCRIBE"))
    	{
	    	if (sessionMgr.checkRegisteredStompDest(dest,accessor.getSessionId()))
	    	{
	    		if (dest.startsWith(IMConstants.destDiscuss) || dest.startsWith(IMConstants.destTopic))
	        	{
	    			SubScriptionRequest req = new SubScriptionRequest();
	    			req.setDest(dest);
	    			req.setOperator(operator);
	    			req.setTopicId(topicId);
	    	    	if(kafkaHandler.isProducer())
	    	    	{
		    			req.setServiceName("subScribeInitWithoutParameter");
	    	    		kafkaHandler.convertAndSend(req);
	    	    	}
	    	    	else{	    			
	    	    		service.subScribeInitWithoutParameter(req);
	    	    	}
	        	}
	    	}
    	}
	    return true;
    }
    @SubscribeMapping("/{path1}/{path2}")
    public SubscribeInitEvent subScribeInitWithOneParameter(@DestinationVariable String path1,SimpMessageHeaderAccessor accessor)
    {
    	SubscribeInitEvent evt = new SubscribeInitEvent();
    	return evt;
    }

    //没有主题群组管理的内容。 也会发通知广播消息通知在线用户， 消息不会被保存和持久化。 
    @MessageMapping("addChildMessage")
    public void addMsg4Msg(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	service.addMsg4Msg(request);

    }
    @RequestMapping(value = "/IMServer/addChildMessage", method= RequestMethod.POST)
    public @ResponseBody void addMsg4Msg(@RequestBody MessageRequest request)
    {
    	addMsg4Msg(null, request);
    }


    //发给讨论组。
    @MessageMapping("publish2DiscussGroup")
    public void publish2DiscussGroup(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setMsgType(IMConstants.typeIM);
    	if  (sessionMgr != null && !sessionMgr.checkRegisteredStompDest(IMConstants.destDiscuss+"/"+request.getTopicId(),accessor.getSessionId()))
    	{
    		return;
    	}
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("publish2Group");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{	      	
    		service.publish2Group( request);
    	}
    }   
    @MessageMapping("publish2CaseGroup")
    public void publish2CaseGroup(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setMsgType(IMConstants.typeCaseSubcriber);
    	if  (sessionMgr != null && !sessionMgr.checkRegisteredStompDest(IMConstants.destDiscuss+"/"+request.getTopicId(),accessor.getSessionId()))
    	{
    		return;
    	}
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("publish2Group");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{    	
    		service.publish2Group(request);
    	}
    }
    @MessageMapping("publish2FileGroup")
    public void publish2FileGroup(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setMsgType(IMConstants.typeFileSubscriber);
    	if  (sessionMgr != null && !sessionMgr.checkRegisteredStompDest(IMConstants.destDiscuss+"/"+request.getTopicId(),accessor.getSessionId()))
    	{
    		return;
    	}    	
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("publish2Group");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{    	
    		service.publish2Group(request);
    	}
    }
    //讨论组中增加一条消息。(for REST API)
    @RequestMapping(value = "/IMServer/publish2DiscussGroup", method= RequestMethod.POST)
    public @ResponseBody void publish2DiscussGroup(@RequestBody MessageRequest request)
    {
    	publish2DiscussGroup(null, request);
    }
    @RequestMapping(value = "/IMServer/publish2CaseGroup", method= RequestMethod.POST)
    public @ResponseBody void publish2CaseGroup(@RequestBody MessageRequest request)
    {
    	publish2CaseGroup(null, request);
    }
    @RequestMapping(value = "/IMServer/publish2FileGroup", method= RequestMethod.POST)
    public @ResponseBody void publish2FileGroup(@RequestBody MessageRequest request)
    {
    	publish2FileGroup(null, request);
    }

    //查询讨论组中已经存在的消息
    @MessageMapping("queryDiscussGroupMessage")
    public void queryGroupMessage(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	if  (sessionMgr != null && !sessionMgr.checkRegisteredStompDest(IMConstants.destDiscuss+"/"+request.getTopicId(),accessor.getSessionId()))
    	{
    		return;
    	}
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryGroupMessage");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{    	
    		service.queryGroupMessage(request);
    	}
    	    	
    	
    }
    @RequestMapping(value = "/IMServer/queryDiscussGroupMessage", method= RequestMethod.POST)
    public @ResponseBody  void queryGroupMessageREST(@RequestBody MessageRequest request)
    {
    	queryGroupMessage(null, request);
    }
    //撤销一条消息
    @MessageMapping("delDiscussGroupMessage")
    public void delGroupMessage(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	if  (sessionMgr != null && !sessionMgr.checkRegisteredStompDest(IMConstants.destDiscuss+"/"+request.getTopicId(),accessor.getSessionId()))
    	{
    		return;
    	}
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("delGroupMessage");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{  
    		service.delGroupMessage(request);
    	}
    }
    @RequestMapping(value = "/IMServer/delDiscussGroupMessage", method= RequestMethod.POST)
    public @ResponseBody void delGroupMessage(@RequestBody MessageRequest request)
    {
    	delGroupMessage(null, request);
    }

    @MessageMapping("queryUserMails")
    public void queryUserMails(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {    
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryUserMails");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{      	
    		service.queryUserMails(request);
    	}
    }
    @RequestMapping(value = "/IMServer/queryUserMails", method= RequestMethod.POST)
    public @ResponseBody void queryUserMails(@RequestBody MessageRequest request)
    {
    	queryUserMails(null, request);
    }

    //发邮件
    @MessageMapping("sendMail2User")
    public void sendMail2User(SimpMessageHeaderAccessor accessor,SendMailRequest request)
    {
    	request.setSenderId(sessionMgr.getOperatorName(accessor));
    	//检查过滤用户组
    	request.setToIdList(accountMgr.getValidatedUsers(request.getToIdList()));
    	if (request.getToIdList().size()==0)
    	{
    		return;
    	}
    	//检查是否是合法操作， 操作员是否是在讨论组成员列表里
    	if (!StringUtils.isEmpty(request.getContent()))
    	{
        	if(kafkaHandler.isProducer())
        	{
        		request.setServiceName("sendMail2User");
        		kafkaHandler.convertAndSend(request);
        	}
        	else{ 
        		service.sendMail2User(request);
        	}
    	}
    }
    //(for REST API)
    @RequestMapping(value = "/IMServer/sendMail2User", method= RequestMethod.POST)
    public @ResponseBody void sendMail2User(@RequestBody SendMailRequest request)
    {
    	sendMail2User(null, request);
    } 
    //标示为已读邮件
    @MessageMapping("markReadedMail")
    public void markReadedMail(SimpMessageHeaderAccessor accessor,SendMailRequest request)
    {
    	request.setSenderId(sessionMgr.getOperatorName(accessor));
    	//检查是否是合法操作， 操作员是否是在讨论组成员列表里
    	if (!StringUtils.isEmpty(request.getMsgId()))
    	{
    		service.markReadedMail(request);
    	}
    }
    //(for REST API)
    @RequestMapping(value = "/IMServer/markReadedMail", method= RequestMethod.POST)
    public @ResponseBody void markReadedMail(@RequestBody SendMailRequest request)
    {
    	sendMail2User(null, request);
    }
    @MessageMapping("removeMail")
    public void removeMail(SimpMessageHeaderAccessor accessor,SendMailRequest request)
    {
    	request.setSenderId(sessionMgr.getOperatorName(accessor));
    	//检查是否是合法操作， 操作员是否是在讨论组成员列表里
    	if (!StringUtils.isEmpty(request.getMsgId()))
    	{
        	if(kafkaHandler.isProducer())
        	{
        		request.setServiceName("removeMail");
        		kafkaHandler.convertAndSend(request);
        	}
        	else{     		
        		service.removeMail(request);
        	}
    	}
    }
    //(for REST API)
    @RequestMapping(value = "/IMServer/removeMail", method= RequestMethod.POST)
    public @ResponseBody void removeMail(@RequestBody SendMailRequest request)
    {
    	sendMail2User(null, request);
    }
    //给评论增加子评论。
    @MessageMapping("publishChildComment")
    public void publishChildComment(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	request.setMsgType(IMConstants.typeIM);
    	if (StringUtils.isEmpty(request.getMsgId()) || (StringUtils.isEmpty(request.getTopicId())))
    	{
    		return;
    	}
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("publishChildComment");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{	      	
    		service.publishChildComment( request);
    	}
    }
    @RequestMapping(value = "/IMServer/publishChildComment", method= RequestMethod.POST)
    public @ResponseBody void publishChildComment(@RequestBody MessageRequest request)
    {
    	publishChildComment(null, request);
    }    
    //查询讨论组中已经存在的消息
    @MessageMapping("queryChildMessages")
    public void queryChildMessages(SimpMessageHeaderAccessor accessor,MessageRequest request)
    {
    	request.putOperatorId(sessionMgr.getOperatorName(accessor));
    	if(kafkaHandler.isProducer())
    	{
    		request.setServiceName("queryChildMessages");
    		kafkaHandler.convertAndSend(request);
    	}
    	else{    	
    		service.queryChildMessages(request);
    	}
    	    	
    	
    }  
    @RequestMapping(value = "/IMServer/queryChildMessages", method= RequestMethod.POST)
    public @ResponseBody void queryChildMessages(@RequestBody MessageRequest request)
    {
    	queryChildMessages(null, request);
    }      
    /*
    //security websocket token
    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }    */
}
