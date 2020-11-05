package com.west.business;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


// @EnableEurekaClient		// 将当前项目标记为客户端
// @EnableFeignClients
@ComponentScan(basePackages = {"com.west.business.config","com.west.business.controller",
        "com.west.business.service","com.west.business.util"})
@MapperScan(value = "com.west.domain.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BusinessApp{
	
    public static void main(String[] args){
        SpringApplication.run(BusinessApp.class, args);
    }

}