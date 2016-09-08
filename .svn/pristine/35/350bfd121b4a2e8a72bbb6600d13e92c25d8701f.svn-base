//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.model.data.KVStoredao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ibm.cloud.im.constant.IMConstants;
import com.ibm.cloud.im.model.data.KVStore.MailBody;
import com.ibm.cloud.im.model.rquest.SendMailRequest;
import com.ibm.cloud.im.server.utils.Utils;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.BasicDBList;

//db.MailBody.ensureIndex({ "ow" : 1, "_id" : 1 },{ "name" : "MailBodyIndex", "unique" : true });

@Repository
public class MailBodyDao extends BaseDao<MailBody>{
	
	//批量增加消息
	public List <MailBody> newMails(SendMailRequest request)
	{
		MailBody shareMsg=null;
		if(request.isbShareMsg())
		{
			shareMsg = newMail4Mail(request);
		}
		List<MailBody> objs= new ArrayList<MailBody>();
		for (String userId: request.getToIdList())
		{
			MailBody mail = new MailBody();
			if(!request.isbShareMsg()) 
				mail.setContent(request.getContent());
			else
				mail.setSharedMsgId(shareMsg.getId().toHexString());
			mail.setObjectId(request.getObjectId());
			mail.setOwner(userId);
			mail.setSenderId(request.getSenderId());
			//msg.setSharedMsgId(sharedMsgId);
			mail.setState(0);
			mail.setTopicId(request.getTopicId());
			if (request.isbAck())//邮件如果不需要用户回执， 直接标示为已读。
				mail.setUnRead(false);
			else
				mail.setUnRead(true);
			mail.setbEncrypted(request.isbEncrypted());
			objs.add(mail);
		}
		if (objs.size()>0)
		{
			insert(objs);
		}
		if(request.isbShareMsg()) 
		{
			for (MailBody body: objs)
			{
				body.setContent(shareMsg.getContent());
			}
		}
		return objs;
	}
	//增加共享消息内容（例如评论）
	public MailBody newMail4Mail(SendMailRequest request)
	{
		MailBody msg = new MailBody();
		msg.setContent(request.getContent());
		msg.setObjectId(request.getObjectId());
		msg.setSenderId(request.getSenderId());
		insert(msg);
		return msg;
	}	
	//删除一条消息， 假删除
	public boolean removeMail(SendMailRequest request)
	{
		Update update = new Update();
		boolean bret = false;
		update.set(IMConstants.msgState, 1);
		//只有owner可以删除消息。
		Query query = new Query(Criteria.where(IMConstants._id).is(new ObjectId(request.getMsgId()))
				);
		WriteResult result = updateFirst(query, update);
		if(result.getN()>0)
		{
			bret = true;
		}
		return bret;
	}
	//标示为已读邮件
	public boolean setRead(SendMailRequest request)
	{
		Update update = new Update();
		boolean bret = false;
		update.set(IMConstants.msgUnread, 1);
		//只有owner可以删除消息。
		Query query = new Query(Criteria.where(IMConstants._id).is(new ObjectId(request.getMsgId()))
				.and(IMConstants.ownerId).is(request.getSenderId())
				);
		WriteResult result = updateFirst(query, update);
		if(result.getN()>0)
		{
			bret = true;
		}
		return bret;
	}
	
	//查询消息的各种函数
	public MailBody lookupMail(String msgId)
	{
		Query query = new Query(Criteria.where(IMConstants._id).is(new ObjectId(msgId)));
		return findOne(query);		
	} 
	/**
	 * 
	 * @param userId  。
	 * @param msgId   消息的ObjectId
	 * @param retCount 要求的最大返回数目
	 * @param direction 查询方向
	 * @return 所有直接评论， 不包括子评论。
	 */
	public List <MailBody> lookupUserMails(String userId, String msgId, int retCount,int direction, boolean read)
	{
		//需要在mongo里建立《topicId， msgSeq》的复合索引， 
		Query query = null;
		List <MailBody> result = null;
		if (direction >0)
		{	query = new Query(Criteria.where(IMConstants.ownerId).is(userId)
					.and(IMConstants._id).gte(new ObjectId(msgId))
					.and(IMConstants.msgState).is(0)//没被删除的邮件
					.and(IMConstants.msgUnread).is(read)
					);
			query.with(new Sort(Sort.Direction.ASC,IMConstants._id));
			if (retCount >0)
				query.limit(retCount);			
			result = find(query);
		}
		else
		{
			query = new Query(Criteria.where(IMConstants.ownerId).is(userId)
					.and(IMConstants._id).lte(new ObjectId(msgId))
					.and(IMConstants.msgState).is(0)//没被删除的邮件
					.and(IMConstants.msgUnread).is(read)
					);	
			query.with(new Sort(Sort.Direction.DESC,IMConstants._id));
			if (retCount >0)
				query.limit(retCount);			
			result = find(query);
			//逆转数据顺序
			Utils.reverseList(result);
		}
		ArrayList<String> shareMailIds= new ArrayList<String>();
		ArrayList <MailBody> toBeAssembly = new ArrayList<MailBody>();
		//装配共享消息体。
		for(MailBody body: result)
		{
			String shareId = body.getSharedMsgId();
			if (shareId != null && !shareId.isEmpty())
			{
				shareMailIds.add(shareId);
				toBeAssembly.add(body);
			}
		}
		if(shareMailIds.size()>0)
		{
			query = new Query(Criteria.where(IMConstants._id).in(shareMailIds)
					);
			List <MailBody> shareMails = this.find(query);
			
			for (MailBody body: toBeAssembly)
			{
				String shareMsgId = body.getSharedMsgId();
				for (MailBody sharedObj : shareMails)
				{
					if(sharedObj.getId().equals(shareMsgId))
					{
						body.setContent(sharedObj.getContent());
					}
				}
			}
		}
		
		return result;				
	}
	/**
	 * 统计某时间后， 某用户的新邮件数量, 排除共享消息体。
	 */
	public long sumMailCount(String userId, String msgId)
	{
		long ret = 0;
		Query query = new Query(Criteria.where(IMConstants.ownerId).is(userId)
				.and(IMConstants.shareMsg).is(null)
				);
		ret = this.count(query);
		return ret;
	}	
}
