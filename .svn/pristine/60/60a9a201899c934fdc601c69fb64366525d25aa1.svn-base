//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.utils;

import java.util.List;

import org.bson.types.ObjectId;

import com.ibm.cloud.im.model.data.KVStore.MessageBody;


public class Utils {
	public static String makeFarFutureIdString()
	{
		ObjectId  id= new ObjectId();
		String timestamp = "9"+ id.toHexString().substring(1);
		return timestamp;
	}
	public static String convertTime2Id(long time)
	{
		return Long.toHexString(time/1000)+"0000000000000000";
	}
	public static <T> List <T>reverseList(List<T> result)
	{
		for (int i=0,j=result.size()-1;i<j; i++,j--)
		{
			T obj = result.get(i);
			result.set(i, result.get(j));
			result.set(j, obj);
		}
		return result;
	}
}
