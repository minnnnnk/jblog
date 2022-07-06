package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/joinForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController  >  joinForm");
		return "/user/joinForm";
	}
	
	@RequestMapping(value="/join",method= {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController  >  join");
		
		userService.userJoin(userVo);
		
		return "/user/joinSuccess";
	}
}
