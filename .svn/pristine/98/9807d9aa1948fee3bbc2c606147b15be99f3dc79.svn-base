package com.ibm.cloud.im.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class KafkaIMConfig {
	@Value("${kafka.brokerlist}")
    public String brokerList;
	
	@Value("${kafka.topic}")
	public String topic;

	@Value("${kafka.zookeeper}")
	public String zookeeper;
	
	@Value("${kafka.groupId}")
	public String groupId;
	
	@Value("${kafka.enableProducer}")
	public boolean enableProducer;
	
	@Value("${kafka.enableConsumer}")
	public boolean enableConsumer;
}
