//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.rquest;

import java.util.ArrayList;
import java.util.List;

public class SendMailRequest {
	String senderId;//发送者ID，可缺。
	List <String> toIdList;//接收者ID list
	String topicId;//接收者为topic里的用户组。
	String content;//邮件内容。
	boolean bShareMsg;//邮件内容是否共享存放， 还是单独存放。
	boolean bAck;//邮件内容是否有状态， 需要客户端回执， 客户端已读未读状态的
	String objectId;//用来关联邮件内容和数据对象的ID
	String msgId;
	private boolean bEncrypted;//内容是否加密
	private String serviceName;//kafka用的参数
	private String className;
	

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}	

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public boolean isbEncrypted() {
		return bEncrypted;
	}
	public void setbEncrypted(boolean bEncrypted) {
		this.bEncrypted = bEncrypted;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public SendMailRequest()
	{
		className = this.getClass().getName();
		toIdList = new ArrayList<String>();
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		if (senderId !=null)
			this.senderId = senderId;
	}
	public List<String> getToIdList() {
		return toIdList;
	}
	public void setToIdList(List<String> toIdList) {
		this.toIdList = toIdList;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isbShareMsg() {
		return bShareMsg;
	}
	public void setbShareMsg(boolean bShareMsg) {
		this.bShareMsg = bShareMsg;
	}
	public boolean isbAck() {
		return bAck;
	}
	public void setbAck(boolean bAck) {
		this.bAck = bAck;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
}
