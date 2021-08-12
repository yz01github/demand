package com.west.business.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import com.west.business.pojo.pub.ResResult;
import com.west.business.service.demo.DemoService;
import com.west.business.util.ClassUtils;
import com.west.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: Demo</p>  
 * <p>Description: []</p>  
 * @author youngZeu  
 * created 2019年8月6日
 */
@Api(tags = "接口Demo")
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${spring.profiles.active}")
    private String profilesActive;

	@Autowired
	private DemoService demoService;

    @Cacheable("TEST_CACHE")
	@GetMapping("/test/{str}")
	public String test(@PathVariable String str, HttpServletRequest request) {
        String demoRequest = demoService.demoRequest(str + request.getRequestURI());
        return str+request.getRequestURI();
	}

    @Cacheable(value = "TEST_CACHE_2", key = "#str")
    @GetMapping("/test2/{str}")
    public String test2(@PathVariable String str, HttpServletRequest request) {
        String demoRequest = demoService.demoRequest(str + request.getRequestURI());
        return str+"_2_"+request.getRequestURI();
    }

    @GetMapping("/class/reload")
    public String reloadClass(String clazzName, HttpServletRequest request) throws Exception {
        log.debug("进入："+request.getRequestURI());
        ClassUtils.reloadClass();
        return clazzName+"_3_"+request.getRequestURI();
    }

    @ApiOperation(value = "查看运行环境", notes = "查看当前所运行的环境(dev/prd/pre/test)")
    @GetMapping("/runtimeSystem")
    public String lookNowSys() {
        return profilesActive;
    }

	@ApiOperation(value = "作用:接收多条数据", notes = "info备注")
	@PostMapping("/info")
	public List<User> addUser(@RequestBody List<User> user) {
		//System.out.println(user.get(0).getDate().toLocaleString());
		System.out.println(user);
		return user;
	}


	public String aopTest(String str) {
		log.debug("aopTest:{}", str);
		return null;
	}
}
