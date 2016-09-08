//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MailBody;

public class MailResponseBody {
	private String id;//消息的Id
		
	private String content;//消息内容
	
	private int state;//是否被撤销。
	
	private String sender;
	private String objectId;
	private boolean unRead;
	private boolean bEncrypted;//内容是否加密
	

	public boolean isbEncrypted() {
		return bEncrypted;
	}
	public void setbEncrypted(boolean bEncrypted) {
		this.bEncrypted = bEncrypted;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public void setState(int state) {
		this.state = state;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public boolean isUnRead() {
		return unRead;
	}
	public void setUnRead(boolean unRead) {
		this.unRead = unRead;
	}
	public MailResponseBody()
	{
		
	}
	public MailResponseBody(MailBody body)
	{
		id=body.getId().toHexString();
		if (body.getState()==0)
			content=body.getContent();
		this.objectId = body.getObjectId();
		this.sender= body.getSenderId();
		this.state = body.getState();
		this.unRead = body.isUnRead();
		bEncrypted = body.isbEncrypted();
	}
}
