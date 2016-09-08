//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MessageBody;
import com.ibm.cloud.im.model.response.MsgRespondBody;

public class SubscribeInitEvent extends IMNewMsgEvent{
	final String  eventName=IMConstants.SubscribeEvent;
	List <MsgRespondBody> msgs;
	String topicId;
	String parentMsgId;
	String topicOwner;
	String topicName;
	long  topicType;

	long time;
	long msgTotalCountInGroup;
	long msgCountSinceLastLogoff;
	
	public long getTopicType() {
		return topicType;
	}
	public void setTopicType(long topicType) {
		this.topicType = topicType;
	}

	List <String> users;
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	public long getMsgTotalCountInGroup() {
		return msgTotalCountInGroup;
	}
	public void setMsgTotalCountInGroup(long msgTotalCountInGroup) {
		this.msgTotalCountInGroup = msgTotalCountInGroup;
	}
	public long getMsgCountSinceLastLogoff() {
		return msgCountSinceLastLogoff;
	}
	public void setMsgCountSinceLastLogoff(long msgCountSinceLastLogoff) {
		this.msgCountSinceLastLogoff = msgCountSinceLastLogoff;
	}

	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getParentMsgId() {
		return parentMsgId;
	}
	public void setParentMsgId(String parentMsgId) {
		this.parentMsgId = parentMsgId;
	}
	public String getTopicOwner() {
		return topicOwner;
	}
	public void setTopicOwner(String topicOwner) {
		this.topicOwner = topicOwner;
	}

	public List<MsgRespondBody> getMsgs() {
		return msgs;
	}
	public SubscribeInitEvent()
	{
		msgs=new ArrayList <MsgRespondBody>();
		time = new Date().getTime();
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public void setMsgs(List<MessageBody> msgs) {
		for (MessageBody item : msgs)
		{
			MsgRespondBody body = new MsgRespondBody(item);
			this.msgs.add(body);
		}
	}
	public void setMsg(MsgRespondBody msg)
	{
		this.msgs.add(msg);
	}	

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getEventName() {
        return eventName;
    }

}
