//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.infrastructure;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.cloud.im.config.KafkaIMConfig;

import java.util.*;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.Partitioner;
import kafka.producer.ProducerConfig;
import kafka.utils.VerifiableProperties;
@Service
public class KafkaSimpleProducer {

	KafkaMessageHandler handler;
	
	KafkaIMConfig appConfig;
	
	 
	public KafkaMessageHandler getHandler() {
		return handler;
	}
	public void setHandler(KafkaMessageHandler handler) {
		this.handler = handler;
	}
	public boolean isProducer()
	{
		return appConfig.enableProducer;
	}
	public void convertAndSend(Object evt)
	{
		this.handler.convertAndSend(evt);
	}
	public class KafkaMessageHandler
	{
		private Producer<String, String> handler=null; 
		
		private boolean enableProducer;
		
		private boolean enableConsumer;
		
		KafkaMessageHandler(Producer<String, String> pro, boolean eP, boolean eC)
		{
			handler=pro;
			enableProducer=eP;
			enableConsumer=eC;
		}
		public boolean isProducer()
		{
			return enableProducer;
		}
		public boolean isConsumer()
		{
			return enableConsumer;
		}
		public boolean send(String msg)
		{
			boolean bret=false;
			if (handler != null)
			{
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(appConfig.topic, msg);
				try
				{
					handler.send(data);
					bret=true;
				}catch(Exception e)
				{
					e.printStackTrace();
			        Properties props = new Properties();
			        props.put("metadata.broker.list", appConfig.brokerList);
			        props.put("serializer.class", "kafka.serializer.StringEncoder");
			        //props.put("partitioner.class", KafkaSimpleBroker.class.toString());
			        props.put("request.required.acks", "1");
			        ProducerConfig config = new ProducerConfig(props);
			        handler = new Producer<String, String>(config);					
				}
			}
			return bret;
		}
		public boolean convertAndSend(Object evt)
		{
	    	JSONObject jsonObject = new JSONObject().fromObject(evt);
	    	String msg = jsonObject.toString();			
			boolean bret=false;
			if (handler != null)
			{
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(appConfig.topic, msg);
				try
				{
					synchronized(this)
					{
						handler.send(data);
					}
					bret=true;
				}catch(Exception e)
				{
					e.printStackTrace();
			        Properties props = new Properties();
			        props.put("metadata.broker.list", appConfig.brokerList);
			        props.put("serializer.class", "kafka.serializer.StringEncoder");
			        //props.put("partitioner.class", KafkaSimpleBroker.class.toString());
			        props.put("request.required.acks", "1");
			 
			        ProducerConfig config = new ProducerConfig(props);
			 
			        handler = new Producer<String, String>(config);					
				}
			}
			return bret;
		}		
	}
	public class KafkaSimpleBroker implements Partitioner{
	    public KafkaSimpleBroker (VerifiableProperties  props) {
	   	 
	    }
	 
	    public int partition(Object key, int a_numPartitions) {
	        int partition = 0;
	        String stringKey = (String) key;
	        int offset = stringKey.lastIndexOf('.');
	        if (offset > 0) {
	           partition = Integer.parseInt( stringKey.substring(offset+1)) % a_numPartitions;
	        }
	       return partition;
	  }
	}
	@Autowired
	public  KafkaSimpleProducer(KafkaIMConfig appConfig)
	{ 
		this.appConfig = appConfig;
        if (appConfig.enableProducer)
        {
	        Properties props = new Properties();
	        props.put("metadata.broker.list", appConfig.brokerList);
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        //props.put("partitioner.class", KafkaSimpleBroker.class.toString());
	        props.put("request.required.acks", "1");
	 
	        ProducerConfig config = new ProducerConfig(props);
	 
	        Producer<String, String> producer =null;

        	producer = new Producer<String, String>(config);
	 
	        /*for (long nEvents = 0; nEvents < 10; nEvents++) { 
	               long runtime = new Date().getTime();  
	               String ip = "192.168.2." + rnd.nextInt(255); 
	               String msg = "this is test"+nEvents; 
	               KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", msg);
	               producer.send(data);
	        }*/
            KafkaMessageHandler handler = new KafkaMessageHandler(producer,appConfig.enableProducer, appConfig.enableConsumer);
            this.setHandler(handler);
       }
	}
}
