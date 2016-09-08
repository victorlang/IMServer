//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.rquest;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class TopicGroupRequest {
    private String topicId;

    private String objectId;//主题关联的数据对象ID
	
	private String topicName;//主题名字
	
	private List <String> userList;//用户列表
	
	private long topicType;
	private String serviceName;//kafka用的参数
	private String className;
	
	private List<String> refTopicGroupIds;//引用的topicId列表

	public List<String> getRefTopicGroupIds() {
		return refTopicGroupIds;
	}
	public List<ObjectId> retrieveRefTopicGroupIds(){
		ArrayList <ObjectId>list = new ArrayList<ObjectId>();
		if (refTopicGroupIds != null)
		{
			for (String id: refTopicGroupIds)
			{
				ObjectId idObj = new ObjectId(id);
				list.add(idObj);
			}
			if (list.size()==0)
			{
				list = null;
			}
		}
		return list;
	}
	public void setRefTopicGroupIds(List<String> refTopicGroupIds) {
		this.refTopicGroupIds = refTopicGroupIds;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public TopicGroupRequest()
	{
		className = this.getClass().getName();
		userList= new ArrayList<String>();
	}
	
	public long getTopicType() {
		return topicType;
	}
	public void setTopicType(long topicType) {
		this.topicType = topicType;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	String operatorId;//发起请求的用户ID
	
    public String getTopicId() {
        return topicId;
    }
    public String getOperatorId()
    {
    	return operatorId;
    }
    public void setOperatorId(String newvalue)
    {
    	if (newvalue != null)
    	{
    		operatorId=newvalue;
    	}
    }
    public String getObjectId()
    {
    	return objectId;
    }
    public String getTopicName()
    {
    	return topicName;
    }
}
