//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MailBody;

public class QueryMailResponse {
	String  eventName=IMConstants.queryMailResponse;
	List <MailResponseBody> mailSinceLastLogOff;
	List <MailResponseBody> mailUnread;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public List<MailResponseBody> getMailSinceLastLogOff() {
		return mailSinceLastLogOff;
	}
	public void setMailSinceLastLogOff(List<MailBody> mailSinceLastLogOff) {
		this.mailSinceLastLogOff = new ArrayList <MailResponseBody>();
		for(MailBody body: mailSinceLastLogOff)
		{
			MailResponseBody resp = new MailResponseBody(body);
			this.mailSinceLastLogOff.add(resp);
		}
	}
	public void setMailUnread(List<MailBody> mailUnread) {
		this.mailUnread = new ArrayList <MailResponseBody>();
		for(MailBody body: mailUnread)
		{
			MailResponseBody resp = new MailResponseBody(body);
			this.mailUnread.add(resp);
		}
	}	
	public List<MailResponseBody> getMailUnread() {
		return mailUnread;
	}

}
