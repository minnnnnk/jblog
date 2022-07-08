package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/add", method= {RequestMethod.GET,RequestMethod.POST})
	public int cateAdd(@ModelAttribute CategoryVo cateVo) {
		System.out.println("CategoryController > cateAdd");
		
		int count = categoryService.addCategory(cateVo);
		
		return count;
	}
	

}
