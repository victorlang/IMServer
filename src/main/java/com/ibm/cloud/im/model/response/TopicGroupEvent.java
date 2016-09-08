//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.io.Serializable;
import java.util.List;

import com.ibm.cloud.im.constant.IMConstants;

//被动加入到一个订阅组（讨论组， 项目， 文件）的通知事件数据结构 
//通过用户个人消息队列通知到个人。
//然后由客户端调用stomp协议 订阅新的讨论组。
public class TopicGroupEvent implements Serializable{
	private String  eventName=IMConstants.TopicGroupEvent;
 
	private String evtype;
	private String operatorId;//引起这个事件的操作者ID
	private String topicId;
    private String founderID;
    private List<String> userList;
    private String topicName;
    private long topicType;
    public void setEventName(String eventName) {
 		this.eventName = eventName;
 	}   

	public long getTopicType() {
		return topicType;
	}
	public void setTopicType(long topicType) {
		this.topicType = topicType;
	}

	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public void setFounderID(String founderID) {
		this.founderID = founderID;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public void setEvtype(String evtype) {
		this.evtype = evtype;
	}
	public TopicGroupEvent()
	{
		
	}
	public TopicGroupEvent(String topicId,String founderUid, String eventType)
	{
		this.topicId = topicId;
		founderID = founderUid;
		evtype = eventType;
	}
	
	public String getEventName() {
        return eventName;
    }
	public String getTopicId()
	{
		return topicId;
	}
	public List<String> getUserList()
	{
		return userList;
	}
	public String getFounderID()
	{
		return founderID;
	}
	public String getEvtype()
	{
		return evtype;
	}
    public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}	
}
