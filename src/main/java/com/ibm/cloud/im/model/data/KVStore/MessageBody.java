//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStore;
/**
 * 不需要复位的消息， 如即时聊天， 评论
 */
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.cloud.im.constant.IMConstants;
@Document(collection = IMConstants.cnMessageBody)
public class MessageBody {
	@Id
	private ObjectId  id;//消息自己的Id
	
	@Field(IMConstants.topicId)
	private ObjectId tId;//消息所属主题的Id
	@Field(IMConstants.msgType)
	private long mType;//消息类型

	@Field(IMConstants.ownerId)
	private String owner;//创建者的userId
	@Field(IMConstants.content)
	private String content;//消息内容
	@Field(IMConstants.msgSeq)
	private long msgSeq;//同主题内的消息序号
	@Field(IMConstants.msgState)
	private int state;//消息状态
	@Field(IMConstants.parentMsgId)
	private ObjectId pMsgId;//父评论ID
	@Field(IMConstants._childCount)
	private int _kidSum;//子评论数量
	@Field(IMConstants.objectId)
	private String objectId;
	@Field(IMConstants.isEncrypted)
	private boolean bEncrypted;//内容是否加密
	public boolean isbEncrypted() {
		return bEncrypted;
	}
	public void setbEncrypted(boolean bEncrypted) {
		this.bEncrypted = bEncrypted;
	}	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public int get_kidSum() {
		return _kidSum;
	}

	public void set_kidSum(int _childCount) {
		this._kidSum = _childCount;
	}

	
	//创建时间可以从id中获取， 不单独创建该属性。
	
	public ObjectId getPMsgId() {
		return pMsgId;
	}

	public void setPMsgId(ObjectId parentMsgId) {
		this.pMsgId = parentMsgId;
	}
	@JsonIgnore
	public void setParentMsgIdByStr(String parentMsgId) {
		this.pMsgId = new ObjectId(parentMsgId);
	}	

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getTId() {
		return tId;
	}

	public void setTId(ObjectId topicId) {
		this.tId = topicId;
	}

	public long getMType() {
		return mType;
	}

	public void setMType(long msgType) {
		this.mType = msgType;
	}


	public String getOwnerId() {
		return owner;
	}

	public void setOwnerId(String ownerId) {
		this.owner = ownerId;
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

	public int getState() {
		return state;
	}

	public void setState(int msgState) {
		this.state = msgState;
	}
}
