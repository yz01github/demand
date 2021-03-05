package com.west.business.controller.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * description: [使用@JSONField(serialize = false), 让对象toJson]
 * title: NewZhaNvDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/7
 */
@Slf4j
public class JSONFieldNoSerializeDemo {
    public static void main(String[] args) {
        Person person = Person.builder().name("ppp").age("11").sex("M").build();
        String jsonString = JSONObject.toJSONString(person);
        log.debug(jsonString);
        Object2StrModel model = new Object2StrModel();
        model.setAge("1");
        model.setName("2");
        model.setSex("3");
        log.debug(JSONObject.toJSONString(model));
        log.debug(model.toString());

    }
}

@Builder
@Data
class Person{
    private String name;
    private String age;
    @JSONField(serialize = false)
    private String sex;
}

@Getter
@Setter
class Object2StrModel{
    private String name;
    private String age;
    private String sex;
}