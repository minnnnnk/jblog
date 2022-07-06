package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/////////////회원가입
	@RequestMapping(value="/joinForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController  >  joinForm");
		return "/user/joinForm";
	}
	
	@RequestMapping(value="/join",method= {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo,@ModelAttribute BlogVo blogVo) {
		System.out.println("UserController  >  join");
		
		System.out.println(userVo);
		
		
		userService.userJoin(userVo,blogVo);
		
		return "/user/joinSuccess";
	}
	
	///////////////아이디체크 아직 미완성 내일 물어봄
	@ResponseBody
	@RequestMapping(value="/check",method= {RequestMethod.GET,RequestMethod.POST})
	public boolean idCheck(@RequestBody String id) {
		System.out.println("UserController  >  idCheck");
		
		boolean check = userService.userIdCheck(id);
		
		System.out.println(check);
		
		return check;
	}
	
	
	
	/////////////////로그인
	@RequestMapping(value="/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController  >  loginForm");
		
		return "/user/loginForm";
	}

	@RequestMapping(value="")
	public String login() {
		
		return "";
	}
	
	
}