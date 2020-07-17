package com.west.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient		// Eureka客户端
//@EnableCircuitBreaker
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DoMainApp{

    public static void main(String[] args){
        SpringApplication.run(DoMainApp.class, args);
    }
}
