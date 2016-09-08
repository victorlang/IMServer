//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.rquest;
public class MessageRequest {
    private String userid;  //发消息的人ID， 非必须参数， REST可以用。Websocket里设参数也没用。
	private String topicId; //主题的ID
	private String objectId; //对象ID 上线也要单独做索引
	private String content;//新消息内容
	private int msgRetCnt;//查询最新消息返回数目的上限
	private int direction;//查询方向。
	private long msgType;
	private boolean bEncrypted;//内容是否加密
	private String serviceName;//kafka用的参数
	private String className;
	
	public MessageRequest()
	{
		className = this.getClass().getName();
	}

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
	public long getMsgType() {
		return msgType;
	}
	public void setMsgType(long msgType) {
		this.msgType = msgType;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	private String msgId;
	private String parentMsgId;
	public String getParentMsgId() {
		return parentMsgId;
	}
	public void setParentMsgId(String parentMsgId) {
		this.parentMsgId = parentMsgId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public int getMsgRetCnt() {
		return msgRetCnt;
	}
	public void setMsgRetCnt(int msgCnt) {
		this.msgRetCnt = msgCnt;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTopicId() {
        return topicId;
    }
    public String getContent()
    {
    	return content;
    }
    public String getUserid()
    {
    	return userid;
    }
    public void putOperatorId(String value)
    {
    	if(value !=null)
    	{
    		userid=value;
    	}
    }
}
