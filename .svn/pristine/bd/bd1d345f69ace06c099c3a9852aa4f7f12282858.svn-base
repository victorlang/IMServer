//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.util.List;

import com.ibm.cloud.im.model.data.KVStore.TopicGroup;

public class DiscussGroupInfo {
		String groupId;
		String groupName;
		String objectId;
		long lastMsgTime;
		long type;
		String creator;
		List <String> users;
		long totalMsgCount;
		List <String> refTopicGroupIds;
		boolean bAccesible;


		public boolean isbAccesible() {
			return bAccesible;
		}
		public void setbAccesible(boolean bAccesible) {
			this.bAccesible = bAccesible;
		}
		public List<String> getRefTopicGroupIds() {
			return refTopicGroupIds;
		}
		public void setRefTopicGroupIds(List<String> refTopicGroupIds) {
			this.refTopicGroupIds = refTopicGroupIds;
		}
		public long getTotalMsgCount() {
			return totalMsgCount;
		}
		public void setTotalMsgCount(long totalMsgCount) {
			this.totalMsgCount = totalMsgCount;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public List<String> getUsers() {
			return users;
		}
		public void setUsers(List<String> users) {
			this.users = users;
		}
		public String getObjectId() {
			return objectId;
		}
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		public long getType() {
			return type;
		}
		public void setType(long type) {
			this.type = type;
		}
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public long getLastMsgTime() {
			return lastMsgTime;
		}
		public void setLastMsgTime(long lastMsgTime) {
			this.lastMsgTime = lastMsgTime;
		}
		public void copyObj(TopicGroup obj)
		{
			 groupId=obj.getId().toHexString();
			 groupName=obj.getTopicName();
			 objectId=obj.getObjId();
			 lastMsgTime=obj.getLastMsgTime();
			 type=obj.getTopicType();
			 creator=obj.getCreator();
			 users=obj.getUsers();
			 totalMsgCount=obj.getLastMsgSeq();
			 refTopicGroupIds = obj.retrieveRefTopicGroupStringIds();
		}
}
