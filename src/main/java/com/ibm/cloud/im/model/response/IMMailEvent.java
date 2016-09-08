//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MailBody;

public class IMMailEvent {
	List <MailResponseBody> mails;

	final String  eventName=IMConstants.IMNewMailEvent;
	
	public List<MailResponseBody> getMails() {
		return mails;
	}
	public IMMailEvent()
	{
		mails=new ArrayList <MailResponseBody>();
	}
	public void setMails(List<MailBody> msgs) {
		for (MailBody item : msgs)
		{
			MailResponseBody body = new MailResponseBody(item);
			this.mails.add(body);
		}
	}
	@JsonIgnore
	public void putMail(MailResponseBody mail)
	{
		this.mails.clear();
		this.mails.add(mail);
	}
	@JsonIgnore
	public void putMail(MailBody mail)
	{
		this.mails.clear();
		this.mails.add(new MailResponseBody(mail));
	}	
	public String getEventName() {
        return eventName;
    }
}
