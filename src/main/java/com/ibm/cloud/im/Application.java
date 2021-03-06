//Main entry of IM server app
package com.ibm.cloud.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ibm.cloud.im.server.service.infrastructure.KafkaSimpleProducer","com.ibm.cloud.im.*","com.ibm.cloud.im.server.service.business.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
