package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
	
	//블로그 연결
	@RequestMapping(value="/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public String blog(Model model) {
		System.out.println("BlogController >  blog");
		
		
		return "/blog/blog-main";
	}
	
	
}
