package com.west.business.controller.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * description: [这是给景瑞雪写的测试demo]
 * title: JingrxDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/5
 */
@Slf4j
public class JingrxDemo {

    public static void main(String[] args) {
        log.debug("args:{}",args);

        // demo1();
        //demo2String4Json();
        tradeIdGenerator();

    }

    private static void tradeIdGenerator() {
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            System.out.println(integer.get());
        }
    }

    private static void demo2String4Json() {
        List<HashMap<String, Object>> list = buildList();
        TradeAttr tradeAttr = new TradeAttr(11, "22", null);
        String jsonString = JSON.toJSONString(tradeAttr);
        log.debug("json string : {}", jsonString);
    }

    private static List<HashMap<String, Object>> buildList() {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int attrCode = new Random().nextInt(100);
            TradeAttr tradeAttr = new TradeAttr(attrCode, "value_" + i, null);
            JSON.toJSONString(tradeAttr);
        }
        return list;
    }

    private static void demo1() {
    /*TradeAttr tradeAttr10 = new TradeAttr(10, "10Str");
        TradeAttr tradeAttr11 = new TradeAttr(11, "11Str");
        TradeAttr tradeAttr22 = new TradeAttr(22, "22Str");
        TradeAttr tradeAttr32 = new TradeAttr(32, "32Str");
        List<TradeAttr> tradeAttrs = Arrays.asList(tradeAttr10, tradeAttr11, tradeAttr22, tradeAttr32);
        log.debug("main.tradeAttrs:{}", tradeAttrs);
        // 过滤出 attrCode < 20 的
        List<TradeAttr> filtedAttrs = tradeAttrs.stream().filter(e -> e.getAttrCode() < 20).collect(Collectors.toList());
        log.debug("main.filtedAttrs:{}", filtedAttrs);
        for (TradeAttr tradeAttr : filtedAttrs) {
            tradeAttr.setAttrValue(tradeAttr.getAttrCode()+"Replace");
        }
        log.debug("main.tradeAttrs:{};\nmain.filtedAttrs:{}", tradeAttrs, filtedAttrs);*/

    }

    /*private <T> String objToJson(T t) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(t);
    }*/

}

@Getter
@Setter
@AllArgsConstructor
class TradeAttr{
    private Integer attrCode;
    private String attrValue;
    private List<HashMap<String, Object>> tradeAttrs;
}