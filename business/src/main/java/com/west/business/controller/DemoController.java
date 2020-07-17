package com.west.business.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.west.business.service.demo.DemoService;
import com.west.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: Demo</p>  
 * <p>Description: []</p>  
 * @author youngZeu  
 * created 2019年8月6日
 */
@Api(tags = "接口Demo")
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@GetMapping("/{str}")
	public String test(@PathVariable String str, HttpServletRequest request) {
		return demoService.demoRequest(str + request.getRequestURI());
	}


//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "user", value = "信息", required = true,
//					paramType = "body",
//					dataType = "String",
//					defaultValue = "")
//	})
	@ApiOperation(value = "作用:接收多条数据", notes = "info备注")
	@PostMapping("/info")
	public String addUser(@RequestBody List<User> user) {
		System.out.println(user.get(0).getDate().toLocaleString());
		System.out.println(user);

		return null;
	}



	public void tree() {
		List<User> list = new ArrayList<User>();
		list.add(buildUser("s", "1", "0"));
		list.add(buildUser("ss", "2", "1"));
		list.add(buildUser("qq", "3", "1"));
		list.add(buildUser("qq1", "4", "2"));
		list.add(buildUser("qq2", "5", "3"));
		list.add(buildUser("ss1", "6", "2"));
		
		List<User> tree = buildTree(list);
		System.out.println(JSONObject.toJSONString(tree));
	}
	
	private List<User> buildTree(List<User> list) {
		list.forEach(o -> {
			list.stream()
			    .filter(s -> Objects.equal(o.getType(), s.getParentType()))
			    .collect(Collectors.toList())
			    .forEach(t -> {
			    	List<User> sub = o.getSub() == null ? new ArrayList<>() : o.getSub();
			    	sub.add(t);
			    	o.setSub(sub);
			    });;
		});
		return list.stream().filter(o -> "1".equals(o.getType())).collect(Collectors.toList());
	}

	private User buildUser(String name, String type, String parent) {
		User user = new User();
		user.setId(new Random().nextLong());
		user.setName(name + type);
		user.setType(type);
		user.setParentType(parent);
		return user;
	}
	
	
	
}
