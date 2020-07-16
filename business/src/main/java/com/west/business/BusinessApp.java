package com.west.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// @EnableEurekaClient		// 将当前项目标记为客户端
public class BusinessApp{
	
    public static void main(String[] args){
        SpringApplication.run(BusinessApp.class, args);
    }
}