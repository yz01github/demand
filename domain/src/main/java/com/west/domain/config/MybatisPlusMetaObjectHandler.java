package com.west.domain.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

// 自动注入属性
@Slf4j
@Configuration
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        log.debug("insertFill begin...");
//        setFieldValByName("insertUser", "DEFAULT_INSERT", metaObject);
//        setFieldValByName("insertTime", LocalDateTime.now(), metaObject);
//        setFieldValByName("isDelete", '0', metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.debug("updateFill begin...");
//        setFieldValByName("updateUser", "DEFAULT_UPDATE", metaObject);
//        setFieldValByName("updateTime",LocalDateTime.now(), metaObject);
    }
}
