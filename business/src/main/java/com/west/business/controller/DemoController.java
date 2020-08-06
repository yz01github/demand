package com.west.business.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import com.west.business.pojo.pub.ResResult;
import com.west.business.service.demo.DemoService;
import com.west.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@ApiIgnore
	@GetMapping("/{str}")
	public String test(@PathVariable String str, HttpServletRequest request) {
		return demoService.demoRequest(str + request.getRequestURI());
	}

	@ApiIgnore
	@ApiOperation(value = "作用:接收多条数据", notes = "info备注")
	@PostMapping("/info")
	public String addUser(@RequestBody List<User> user) {
		//System.out.println(user.get(0).getDate().toLocaleString());
		System.out.println(user);

		return null;
	}

    @ApiOperation(value = "查看运行环境", notes = "查看当前所运行的环境(dev/prd/pre/test)")
    @GetMapping("/runtimeSystem")
    public ResResult<String> lookNowSys() {
        return ResResult.successAddData(profilesActive);
    }

	static List list = new ArrayList();
	static List getList() {
		return list;
	}

    public static void main(String[] args) {
        int i = 9;
        switch(i){
            default:System.out.println("default");
            case 0 :System.out.println("zero");break;
            case 1:System.out.println("one");
            case 2:System.out.println("two");
        }
    }

}
