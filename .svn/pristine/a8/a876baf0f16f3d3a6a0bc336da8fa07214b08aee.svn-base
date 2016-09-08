//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStore;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.cloud.im.constant.IMConstants;

@Document(collection = IMConstants.cnMailBody)
public class MailBody {
	@Id
	private ObjectId  id;//消息自己的Id  id里包含时间信息。
	
	@Field(IMConstants.topicId)
	private String topicId;//消息所属主题的Id

	@Field(IMConstants.ownerId)
	private String owner;//拥有者的userId
	@Field(IMConstants.content)
	private String content;//消息内容
	@Field(IMConstants.msgState)
	private int state;//消息状态
	@Field(IMConstants.objectId)
	private String objectId;
	@Field(IMConstants.shareMsg)
	String sharedMsgId;//共享内容
	@Field(IMConstants.msgUnread)
	private boolean unRead=true;//需要有客户端发请求才能改变未读状态。
	@Field(IMConstants.msgSender)
	private String senderId;
	@Field(IMConstants.isEncrypted)
	private boolean bEncrypted;//内容是否加密
	

	public boolean isbEncrypted() {
		return bEncrypted;
	}
	public void setbEncrypted(boolean bEncrypted) {
		this.bEncrypted = bEncrypted;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isUnRead() {
		return unRead;
	}

	public void setUnRead(boolean unRead) {
		this.unRead = unRead;
	}

	public String getSharedMsgId() {
		return sharedMsgId;
	}

	public void setSharedMsgId(String sharedMsgId) {
		this.sharedMsgId = sharedMsgId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int msgState) {
		this.state = msgState;
	}
}
