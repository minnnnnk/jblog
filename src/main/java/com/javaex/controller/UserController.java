package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
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
	public String join(@ModelAttribute UserVo userVo,@ModelAttribute BlogVo blogVo,@ModelAttribute CategoryVo cateVo) {
		System.out.println("UserController  >  join");
		
		System.out.println(userVo);
		
		
		userService.userJoin(userVo,blogVo,cateVo);
		
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

	@RequestMapping(value="/login",method= {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println(userVo);
		UserVo uVo = userService.loginUser(userVo);
		
		if(uVo != null) {
			session.setAttribute("authUser", uVo);
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
		}
		
		
	}
	
	
	@RequestMapping(value="/logout",method= {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController > logout");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/blog/{id}";
	}
}
