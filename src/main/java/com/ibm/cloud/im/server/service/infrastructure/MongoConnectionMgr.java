//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.infrastructure;

import org.springframework.data.mongodb.core.MongoTemplate;
import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.*;
import com.mongodb.BasicDBObject;
public class MongoConnectionMgr {
	MongoTemplate imMsgAccessor;
	MongoTemplate imMailAccessor;
	MongoTemplate imUserdataAccessor;
	MongoTemplate imTopicGroupAccessor;
	public MongoTemplate getImUserdataAccessor() {
		return imUserdataAccessor;
	}
	public void setImUserdataAccessor(MongoTemplate imUserdataAccessor) {
		this.imUserdataAccessor = imUserdataAccessor;
	}
	public MongoTemplate getImTopicGroupAccessor() {
		return imTopicGroupAccessor;
	}
	public void setImTopicGroupAccessor(MongoTemplate imTopicGroupAccessor) {
		this.imTopicGroupAccessor = imTopicGroupAccessor;
	}
	public void setImMailAccessor(MongoTemplate imMailAccessor) {
		this.imMailAccessor = imMailAccessor;
	}
	public MongoTemplate getImMsgAccessor() {
		return imMsgAccessor;
	}
	public void setImMsgAccessor(MongoTemplate imMsgAccessor) {
		this.imMsgAccessor = imMsgAccessor;
	}
	public MongoTemplate getImMailAccessor() {
		return imMailAccessor;
	}
	public MongoTemplate getMongoAccessor(String kvStoreModelClassName)
	{
		MongoTemplate ret=null;
		
		if(MailBody.class.toString().equals(kvStoreModelClassName))
		{
			ret = this.getImMailAccessor();
			BasicDBObject index = new BasicDBObject(IMConstants.ownerId, 1).append(IMConstants._id, 1);
			ret.getDb().getCollection(IMConstants.cnMailBody).createIndex(index);
		}else if(MessageBody.class.toString().equals(kvStoreModelClassName))
		{
			ret = this.getImMsgAccessor();
			BasicDBObject index = new BasicDBObject(IMConstants.topicId, 1).append(IMConstants._id, 1);
			ret.getDb().getCollection(IMConstants.cnMessageBody).createIndex(index);
		}else if(TopicGroup.class.toString().equals(kvStoreModelClassName))
		{
			ret = this.getImTopicGroupAccessor();
			BasicDBObject index = new BasicDBObject(IMConstants.topicName, 1);
			ret.getDb().getCollection(IMConstants.cnTopicGroup).createIndex(index);			
			index = new BasicDBObject(IMConstants.objectId, 1);
			ret.getDb().getCollection(IMConstants.cnTopicGroup).createIndex(index);			
		}else if(UserData.class.toString().equals(kvStoreModelClassName))
		{
			ret = this.getImUserdataAccessor();			
		}else
		{
			System.out.println("!!!!!Please choose accessor for you DAO class");
		}
		return ret;
	}
}
