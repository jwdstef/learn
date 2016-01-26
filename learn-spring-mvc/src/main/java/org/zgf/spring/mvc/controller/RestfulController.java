package org.zgf.spring.mvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful")
public class RestfulController {
	
	
	@RequestMapping("/hello")
	public String hello(){
		
		System.out.println("hello,world");
		return "hello";
	}
}
