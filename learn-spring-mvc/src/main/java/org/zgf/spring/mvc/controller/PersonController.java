package org.zgf.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	
	@RequestMapping("/hello")
	public void hello(){
		System.out.println("hello,world");
	}

}
