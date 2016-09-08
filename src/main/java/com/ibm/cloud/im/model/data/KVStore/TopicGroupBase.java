//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStore;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ibm.cloud.im.constant.IMConstants;
/**
 * 为了存储性能，传输效率， 查询优化， 后来的修改者， 在IMConstants定义属性库属性名请用短名 
 * 类里对应的属性可以用长名。两处的名字可以不一致。
 * @author 张建鑫 2016.3.14
 *
 */
public class TopicGroupBase {
	@Field(IMConstants.objectId)
	String objId;//主题关联的数据对象ID
	
	@Field(IMConstants.topicName)
	String topicName;//主题名字
	@Field(IMConstants.userList)
	List <String> users; 
	@Field(IMConstants.state)
	int state;//状态， 是否被删除
	
	//long _createTime;//创建时间可以通过_id获得
	@Field(IMConstants.modifyTime)
	long modifyTime;//主题信息最后被修改时间
	@Field(IMConstants.founderId)
	String creator;//创建主题组的用户ID
	@Field(IMConstants.topicType)
	long topicType;//主题的类型， 参考常量定义文件
	@Field(IMConstants.lastMsgTime)
	long lastMsgTime;
	@Field(IMConstants.lastMsgSeqence)
	long lastMsgSeq;
	
	@Field(IMConstants.refTopicGroupIds)
	List <ObjectId> refTopicGroupIds;	//继承引用的群组对象数组
	
	public List<ObjectId> getRefTopicGroupIds() {
		return refTopicGroupIds;
	}
	public List<String> retrieveRefTopicGroupStringIds() {
		List <String> ret=null;
		if(refTopicGroupIds != null)
		{
			ret=new ArrayList<String>();
			for(ObjectId id:refTopicGroupIds)
			{
				ret.add(id.toHexString());
			}
		}
		return ret;
	}	
	public void setRefTopicGroupIds(List<ObjectId> refTopicGroupIds) {
		this.refTopicGroupIds = refTopicGroupIds;
	}
	public long getLastMsgSeq() {
		return lastMsgSeq;
	}
	public void setLastMsgSeq(long lastMsgSeq) {
		this.lastMsgSeq = lastMsgSeq;
	}
	public long getLastMsgTime() {
		return lastMsgTime;
	}
	public void setLastMsgTime(long lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getTopicType() {
		return topicType;
	}
	public void setTopicType(long topicType) {
		this.topicType = topicType;
	}
	
	
}
