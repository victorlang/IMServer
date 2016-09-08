//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MessageBody;

public class MsgRespondBody {

	private String id;//消息的Id
	
	private long msgType;//消息类型
	
	private String ownerId;//创建者的userId
	
	private String content;//消息内容
	
	private long msgSeq;//同主题内的消息序号
	
	private int _childCount;//child消息总数
	
	
	private int state;//是否被撤销。
	private boolean bEncrypted;//内容是否加密
	private String parentMsgId;


	public String getParentMsgId() {
		return parentMsgId;
	}
	public void setParentMsgId(String parentMsgId) {
		this.parentMsgId = parentMsgId;
	}
	public boolean isbEncrypted() {
		return bEncrypted;
	}
	public void setbEncrypted(boolean bEncrypted) {
		this.bEncrypted = bEncrypted;
	}	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int get_childCount() {
		return _childCount;
	}

	public void set_childCount(int _childCount) {
		this._childCount = _childCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMsgType() {
		return msgType;
	}

	public void setMsgType(long msgType) {
		this.msgType = msgType;
	}


	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getMsgSeq() {
		return msgSeq;
	}

	public void setMsgSeq(long msgSeq) {
		this.msgSeq = msgSeq;
	}
	public MsgRespondBody()
	{
		
	}
	public MsgRespondBody(MessageBody body)
	{
		id=body.getId().toHexString();
		msgType=body.getMType();
		ownerId=body.getOwnerId();
		if (body.getState()==0)
			content=body.getContent();
		state = body.getState();
		msgSeq = body.getMsgSeq();	
		_childCount=body.get_kidSum();
		bEncrypted= body.isbEncrypted();
		if (body.getPMsgId() != null)
		{
			parentMsgId = body.getPMsgId().toHexString();
		}
	}
}
