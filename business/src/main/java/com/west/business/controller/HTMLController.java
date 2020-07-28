package com.west.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * description: [html跳转测试demo]
 * title: HTMLController
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/17
 */
@Controller
@RequestMapping("/html")
public class HTMLController {

    // http://localhost:8081/busi/html/weekly
    @GetMapping("/weekly")
    public String redirectHTML(HashMap<String, Object> map) {
        map.put("TEST", "test_yz_str");
        return "/index";
    }
}
