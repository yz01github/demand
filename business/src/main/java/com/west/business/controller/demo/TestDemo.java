package com.west.business.controller.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.west.business.config.rewrite.anno.AbstractRewriteAnno;
import com.west.business.pojo.vo.demand.DemandInfoVO;
import com.west.business.service.demo.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * description: []
 * title: TestDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/4/1
 */
@Slf4j
public class TestDemo {

    public static void  main(String[] args) throws Exception {
        timeTest();

    }
    public static ApplicationContext context;

    private static void timeTest() throws Exception {
        // . 怎么处理
        boolean matches = Pattern.matches("[A-Z|a-z|0-9]{1,}@[A-Z|a-z|0-9]{1,}.com", "1@w.com");
        System.out.println(matches);
        new ArrayList<>().removeIf(Objects::isNull);
        //        AbstractRewriteAnno rewriteAnno = new AbstractRewriteAnno();
//        rewriteAnno.refresh();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
