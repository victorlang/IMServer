//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.business;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MailBody;
import com.ibm.cloud.im.model.data.KVStoredao.MailBodyDao;
import com.ibm.cloud.im.model.data.KVStoredao.TopicGroupDao;
import com.ibm.cloud.im.model.data.KVStoredao.UserDataDao;
import com.ibm.cloud.im.model.response.IMMailEvent;
import com.ibm.cloud.im.model.rquest.SendMailRequest;
import com.ibm.cloud.im.server.utils.Utils;

//消息状态模块（与用户个人有关）
@Service
public class MailDataService {
	private static Log logger = LogFactory.getLog(MailDataService.class);
	@Autowired
	private UnifyMsgService messagingTemplate;
	
	@Autowired
	private MailBodyDao mailBodyhandler;
	
    @Autowired
    private TopicGroupDao TopicGroupDao;
    @Autowired
    private UserDataDao userTopicsDao;   
    @Autowired
    private TopicAndGroupService topicGroupService;
    
    
	//删除一条消息 （假删除）
	public boolean removeMailById(SendMailRequest request)
	{
		return mailBodyhandler.removeMail(request);
	}

	//发邮件
	public List<MailBody>  sendMails(SendMailRequest request)
	{
		List <MailBody> mailBodys = mailBodyhandler.newMails(request);
		IMMailEvent evt = new IMMailEvent();
	    for (MailBody mail: mailBodys)
	    {
	    	String user = mail.getOwner();
	    	String dest = IMConstants.destNotify +"/"+user;
	    	evt.putMail(mail);
	    	try{
	   			messagingTemplate.convertAndSend(dest, evt);
	   		}catch(Exception e)
	   		{
	   			System.out.println(e);
    		}
		}
		return mailBodys;
	}
	//标记为已读
	public void readMail(SendMailRequest request)
	{
		mailBodyhandler.setRead(request);
	}

	//查询用户邮件
	public  List<MailBody> lookupMails(String userId, long timestamp, int retCount, int direction, boolean read)
	{
		if (retCount > 200 || retCount <= 0)
		{
			retCount = 200;
		}
		return mailBodyhandler.lookupUserMails(userId, Utils.convertTime2Id(timestamp), retCount, direction, read);
	}	
	//统计主题下， 某个时间后产生的消息数量
	public long sumMsgCount(String userId, long timestamp)
	{
		return mailBodyhandler.sumMailCount(userId, Utils.convertTime2Id(timestamp));
	}	
}

