package com.west.domain.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: Demo</p>  
 * <p>Description: []</p>  
 * @author youngZeu  
 * created 2019年8月6日
 */
@RestController
@RequestMapping("/toUp")
public class Demo {


	@PostMapping("/{str}")
	public String test(@PathVariable String str) {
		return str.toUpperCase();
	}

}
