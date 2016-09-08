//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.server.service.infrastructure;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ibm.cloud.im.config.KafkaIMConfig;
import com.ibm.cloud.im.server.controller.ControllerService;
import kafka.consumer.ConsumerIterator;
@Component
public class KafkaSimpleConsumer {
	
	KafkaIMConfig appConfig;
	
	@Autowired
    public KafkaSimpleConsumer(KafkaIMConfig appConfig,ControllerService controller) {
		this.appConfig = appConfig;
    	if(appConfig.enableConsumer)
    	{
    		ConsumerConfig config = createConsumerConfig(appConfig.zookeeper, appConfig.groupId);
	        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config
	                );
	        run(1, controller);
    	}else
    	{
    		consumer=null;
    	}
    }	
	
    private final ConsumerConnector consumer;
    private  ExecutorService executor;
    public class WorkThread implements Runnable
    {
    	private Object request;
    	private String serviceName;
    	ControllerService controller;
    	WorkThread(ControllerService c, String s, Object r)
    	{
    		controller=c;
    		serviceName=s;
    		request=r;
    	}
    	public ControllerService getController() {
			return controller;
		}

		public void setController(ControllerService controller) {
			this.controller = controller;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public Object getRequest() {
			return request;
		}

		public void setRequest(Object request) {
			this.request = request;
		}
        public void run(){  
			try {
				Method method = controller.getClass().getMethod(serviceName, request.getClass());
        		method.invoke(controller, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

    }
    public class ConsumerThead implements Runnable {
        private KafkaStream m_stream;
        private int m_threadNumber;
        private ControllerService controller;
        public ConsumerThead(KafkaStream a_stream, int a_threadNumber, ControllerService c) {
            m_threadNumber = a_threadNumber;
            m_stream = a_stream;
            controller=c;
        }
     
        public void run() {
            ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
            while (it.hasNext())
            {
            	try
            	{
            		String msg = new String(it.next().message());
            		System.out.print("kafka consumer is processing "+msg);
                	//Kafka收到消息以后统一处理
    	        	JSONObject jsonobject = JSONObject.fromObject(msg);
    	        	String className = jsonobject.getString("className");
    	        	String serviceName = jsonobject.getString("serviceName");
    	        	Object request=null; 
    	        	request =JSONObject.toBean(jsonobject, Class.forName (className));
    	        	if(request != null)
    	        	{
    	        		WorkThread worker = new WorkThread(controller,serviceName,request );
    	        	
    	        	     Thread t = new Thread(worker);  
    	        	     
    	        	        t.start();
    	        	}
   	        	
            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}
	        	
            }
            System.out.println("Shutting down Thread: " + m_threadNumber);
        }
    }    
 
    public KafkaSimpleConsumer(String a_zookeeper, String a_groupId, String a_topic) {
    	ConsumerConfig config = createConsumerConfig(a_zookeeper, a_groupId);
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config
                );
    }
 
    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown, exiting uncleanly");
        }
   }
 
    public void run(int a_numThreads, ControllerService controller) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(appConfig.topic, new Integer(a_numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(appConfig.topic);
 
        // now launch all the threads
        //
        executor = Executors.newFixedThreadPool(a_numThreads);
 
        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerThead(stream, threadNumber, controller));
            threadNumber++;
        }
    }
 
    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "6000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
 
        return new ConsumerConfig(props);
    }
 
    public static void main(String[] args) {
 
        KafkaSimpleConsumer example = new KafkaSimpleConsumer("9.112.248.126:2181", "IMGroup", "test");
        example.run(2, null);
 
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException ie) {
 
        }
        example.shutdown();
    }
}