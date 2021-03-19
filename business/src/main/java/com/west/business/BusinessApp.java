package com.west.business;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Duration;
import java.time.LocalDateTime;

import java.time.LocalDateTime;


// @EnableEurekaClient		// 将当前项目标记为客户端
// @EnableFeignClients
@ComponentScan(basePackages = {"com.west.business.config","com.west.business.controller",
        "com.west.business.service"})
@MapperScan(value = "com.west.domain.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BusinessApp{
	
    public static void main(String[] args){
        // test();
        SpringApplication.run(BusinessApp.class, args);
    }

    private static void test() {
        LocalDateTime now = LocalDateTime.now();
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalDateTime now1 = LocalDateTime.now().plusDays(11);
        System.out.println(now);
        System.out.println(now1);
        Duration between = Duration.between(now1, now);
        long toDays = between.toDays();
        System.out.println(toDays);
    }
}