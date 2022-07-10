package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//블로그 연결
	@RequestMapping(value="/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public String blog(Model model
					   ,@PathVariable("id") String id
					   ,@RequestParam(value ="cateNo", required = false, defaultValue = "8") int cateNo) {
		System.out.println("BlogController >  blog");
		
		Map<String,Object> bMap = blogService.blogList(id, cateNo);
		
		model.addAttribute("cList", bMap.get("cList"));
		model.addAttribute("pList", bMap.get("pList"));
		
		model.addAttribute("bMap", bMap);
		
		return "/blog/blog-main";
	}

	
	////////////////블로그 일반창 폼가기
	@RequestMapping(value="/{id}/admin/basic",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogBasic(Model model,@PathVariable("id") String id) {
		System.out.println("BlogController >  blogBasic");
		
		Map<String,Object> bMap = blogService.blogBasic(id);
		
		
		model.addAttribute("bMap", bMap);

		
		return "/blog/admin/blog-admin-basic";
	}
	///////////////블로그 일반 변경
	@RequestMapping(value="/{id}/admin/basic/update",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogBasicUpdate(@RequestParam("file") MultipartFile file,@ModelAttribute BlogVo blogVo) {
		System.out.println("BlogController >  blogBasicUpdate");
		
		blogService.basicUpdate(file, blogVo);
		
		return "redirect:/{id}/admin/basic";
	}


}
