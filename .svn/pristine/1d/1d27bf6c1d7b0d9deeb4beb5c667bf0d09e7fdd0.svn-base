//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.im.constant.IMConstants;

public class QueryDiscussGroupsResponse {
	String  eventName=IMConstants.queryDiscussGroupResponse;

	List<DiscussGroupInfo> discussGroups;
	List<DiscussGroupInfo> caseGroups;
	List<DiscussGroupInfo> fileGroups;

	public List<DiscussGroupInfo> getCaseGroups() {
		return caseGroups;
	}
	public QueryDiscussGroupsResponse()
	{
		discussGroups = new ArrayList<DiscussGroupInfo> ();
		caseGroups = new ArrayList<DiscussGroupInfo> ();
		fileGroups = new ArrayList<DiscussGroupInfo> ();
	}
	public void addGroup(DiscussGroupInfo group)
	{
		if (group.getType()== IMConstants.typeIM)
		{
			discussGroups.add(group);
		}else if(group.getType()== IMConstants.typeCaseSubcriber)
		{
			caseGroups.add(group);
		}else if(group.getType()== IMConstants.typeFileSubscriber)
		{
			fileGroups.add(group);
		}
	}
	public void setCaseGroups(List<DiscussGroupInfo> caseGroups) {
		this.caseGroups = caseGroups;
	}

	public List<DiscussGroupInfo> getFileGroups() {
		return fileGroups;
	}

	public void setFileGroups(List<DiscussGroupInfo> fileGroups) {
		this.fileGroups = fileGroups;
	}

	public List<DiscussGroupInfo> getDiscussGroups() {
		return discussGroups;
	}

	public void setDiscussGroups(List<DiscussGroupInfo> discussGroups) {
		this.discussGroups = discussGroups;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}

}
