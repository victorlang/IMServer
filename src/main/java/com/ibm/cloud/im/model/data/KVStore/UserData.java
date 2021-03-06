//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStore;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ibm.cloud.im.constant.IMConstants;

@Document(collection = IMConstants.cnUsers)
public class UserData {
	@Id
	@Field(IMConstants.userId)
	private String userId;//用户ID
	@Field(IMConstants.discussGroupList)
	private List <String> discussGroupList;//订阅的讨论组列表的ID
	@Field(IMConstants.caseGroupList)
	private List <String> caseGroupList;
	public List<String> getCaseGroupList() {
		return caseGroupList;
	}
	public void setCaseGroupList(List<String> caseGroupList) {
		this.caseGroupList = caseGroupList;
	}
	public List<String> getFileGroupList() {
		return fileGroupList;
	}
	public void setFileGroupList(List<String> fileGroupList) {
		this.fileGroupList = fileGroupList;
	}
	@Field(IMConstants.fileGroupList)
	private List <String> fileGroupList;
	@Field(IMConstants.lastLogOffTime)
	private long lastLogOffTime;//用户上次logoff的时间
	@Field(IMConstants.lastLogInTime)
	private long lastLogInTime;
	@Field(IMConstants.topicId)
	ObjectId topicId;

	public ObjectId getTopicId() {
		return topicId;
	}
	public void setTopicId(ObjectId topicId) {
		this.topicId = topicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getDiscussGroupList() {
		return discussGroupList;
	}
	public void setDiscussGroupList(List<String> discussGroupList) {
		this.discussGroupList = discussGroupList;
	}
	public long getLastLogOffTime() {
		return lastLogOffTime;
	}
	public void setLastLogOffTime(long lastLogOffTime) {
		this.lastLogOffTime = lastLogOffTime;
	}
	public long getLastLogInTime() {
		return lastLogInTime;
	}
	public void setLastLogInTime(long lastLogInTime) {
		this.lastLogInTime = lastLogInTime;
	}
}
