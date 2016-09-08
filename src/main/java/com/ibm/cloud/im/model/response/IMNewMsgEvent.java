//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;



import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MessageBody;


//讨论组中有新消息的事件
public class IMNewMsgEvent {
	List <MsgRespondBody> msgs;

	String  eventName=IMConstants.IMNewMsgEvent;
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	String topicId;
	String topicName;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<MsgRespondBody> getMsgs() {
		return msgs;
	}
	public IMNewMsgEvent()
	{
		msgs=new ArrayList <MsgRespondBody>();
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
