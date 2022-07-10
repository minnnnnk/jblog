package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	//////////////포스트 폼 가기
	@RequestMapping(value="/{id}/admin/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm(Model model,@PathVariable("id") String id) {
		System.out.println("CategoryController > writeForm");
		
		Map<String,Object> cMap = categoryService.getCategory(id);
		System.out.println(cMap);
		model.addAttribute("bMap", cMap.get("bMap"));
		model.addAttribute("cList", cMap.get("cList"));
		
		return "/blog/admin/blog-admin-write";
	}
	
	
	//////////////////포스트 등록
	@RequestMapping(value="/{id}/admin/write",method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo) {
		System.out.println("PostController > write");
		
		postService.addPost(postVo);
		
		return "redirect:/{id}/admin/writeForm";
	}
}
