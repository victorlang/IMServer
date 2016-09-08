//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.config;

import com.ibm.cloud.im.server.service.infrastructure.MongoConnectionMgr;
import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
@ComponentScan("com.learn.sdjpa")
public class SpringMongoConfig{
    
	@Value("${mongo_IMsg.host}")
    public String imMsg_host;

    @Value("${mongo_IMsg.port}")
    public int imMsg_port;
  
    @Value ("${mongo_IMail.database}")
    public String imMsg_database;    
    
    @Value ("${mongo_IMail.database}")
    public String imMail_database;

	@Value("${mongo_IMail.host}")
    public String imMail_host;

    @Value("${mongo_IMail.port}")
    public int imMail_port;
    
    
    @Value ("${mongo_IMUserData.database}")
    public String imUserdata_db;

	@Value("${mongo_IMUserData.host}")
    public String imUserdata_host;

    @Value("${mongo_IMUserData.port}")
    public int imUserdata_port;
   
    
    @Value ("${mongo_IMTopicGroup.database}")
    public String imTopicGroup_db;

	@Value("${mongo_IMTopicGroup.host}")
    public String imTopicGroup_host;

    @Value("${mongo_IMTopicGroup.port}")
    public int imTopicGroup_port;    
    /*
    public @Bean MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("localhost");
		mongo.setPort(port);
		//mongo.setCredentials(credentials)
        return mongo;
   }

    
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		
		return new SimpleMongoDbFactory(new MongoClient(), database);
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());	
		return mongoTemplate;
		
	}
*/

//不同的业务分开部署，。。暂时的思路。。。后续编程逻辑上比较复杂，  以后再完善。
    public @Bean
    MongoConnectionMgr initMongoConnectionMgr()throws Exception
    {
		MongoConnectionMgr mgr = new MongoConnectionMgr();
		SimpleMongoDbFactory factory = new SimpleMongoDbFactory(new MongoClient(imMsg_host,imMsg_port), imMsg_database);
		MongoTemplate accessor = new MongoTemplate(factory);
		mgr.setImMsgAccessor(accessor);
		factory = new SimpleMongoDbFactory(new MongoClient(imMail_host,imMail_port), imMail_database);
		accessor = new MongoTemplate(factory);
		mgr.setImMailAccessor(accessor);

		factory = new SimpleMongoDbFactory(new MongoClient(imUserdata_host,imUserdata_port), imUserdata_db);
		accessor = new MongoTemplate(factory);
		mgr.setImUserdataAccessor(accessor);	
		
		factory = new SimpleMongoDbFactory(new MongoClient(imTopicGroup_host,imTopicGroup_port), imTopicGroup_db);
		accessor = new MongoTemplate(factory);
		mgr.setImTopicGroupAccessor(accessor);	
		
		return mgr;
    }
    
// 不同业务都使用同一个mongodb的情况， 方便程序实现， 不方便分布式部署。
/*	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(imMsg_host,imMsg_port), imMsg_database);
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}*/
}