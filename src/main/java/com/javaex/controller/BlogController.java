package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 연결
	@RequestMapping(value="/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public String blog(Model model,@PathVariable("id") String id) {
		System.out.println("BlogController >  blog");
		System.out.println(id);
		Map<String,Object> bMap = blogService.blogList(id);
		
		
		model.addAttribute("bMap", bMap);
		
		return "/blog/blog-main";
	}
	
	@RequestMapping(value="/{id}/admin/basic",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogBasic(Model model,@PathVariable("id") String id) {
		System.out.println("BlogController >  blogBasic");
		Map<String,Object> bMap = blogService.blogList(id);
		
		model.addAttribute("bMap", bMap);
		
		return "/blog/admin/blog-admin-basic";
	}
	
	@RequestMapping(value="/{id}/admin/basic/update",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogBasicUpdate(@RequestParam("file") MultipartFile file,@ModelAttribute BlogVo blogVo) {
		System.out.println("BlogController >  blogBasicUpdate");
		
		blogService.basicUpdate(file, blogVo);
		
		return "redirect:/{id}/admin/basic";
	}
	
	//////////////////카테고리
	@RequestMapping(value="/{id}/admin/category",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogCategory(Model model,@PathVariable("id") String id) {
		System.out.println("BlogController >  blogCategory");
		
		Map<String,Object> bMap = blogService.blogList(id);
		
		model.addAttribute("bMap", bMap);
		
		return "/blog/admin/blog-admin-cate";
	}
}
