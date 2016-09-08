//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStore;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.cloud.im.constant.IMConstants;

@Document(collection = IMConstants.cnTopicGroup)
public class TopicGroup extends TopicGroupBase {
	
	@JsonIgnore
	@Id
	private ObjectId  id;


	@JsonIgnore
	public ObjectId getId() {
		return id;
	}

	@JsonIgnore
	public void setId(ObjectId id) {
		this.id = id;
	}
	@JsonIgnore
	public TopicGroup()
	{
		
	}
	@JsonIgnore
	public TopicGroup(TopicGroupBase base, String topicId)
	{
		modifyTime=base.getModifyTime();
		state=base.getState();
		creator=base.getCreator();
		objId=base.getObjId();
		topicName=base.getTopicName();
		users=base.getUsers();
		id=new ObjectId(topicId);
		topicType=base.getTopicType();
		lastMsgSeq = base.getLastMsgSeq();
		lastMsgTime = base.getLastMsgTime();
		refTopicGroupIds = base.getRefTopicGroupIds();
	}
	@JsonIgnore
	public TopicGroupBase copyBase()
	{
		TopicGroupBase base= new TopicGroupBase();
		base.setModifyTime(modifyTime);
		base.setState(state);
		base.setCreator(creator);
		base.setObjId(objId);
		base.setTopicName(topicName);
		base.setUsers(users);
		base.setTopicType(topicType);
		base.setLastMsgSeq(lastMsgSeq);
		base.setLastMsgTime(lastMsgTime);
		base.setRefTopicGroupIds(refTopicGroupIds);
		return base;
	}

}
