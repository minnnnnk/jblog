package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//////////////////카테고리
	@RequestMapping(value="/{id}/admin/category",method= {RequestMethod.GET,RequestMethod.POST})
	public String blogCategory(Model model,@PathVariable("id") String id) {
		System.out.println("BlogController >  blogCategory");
		Map<String,Object> cMap = categoryService.getCategory(id);
		
		model.addAttribute("bMap", cMap.get("bMap"));
		
		return "/blog/admin/blog-admin-cate";
	}
	
	/////////////카테고리추가
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/add", method= {RequestMethod.GET,RequestMethod.POST})
	public CategoryVo cateAdd(@RequestBody CategoryVo cateVo) {
		System.out.println("CategoryController > cateAdd");
		
		CategoryVo cVo = categoryService.addCategory(cateVo);
		System.out.println(cVo);
		return cVo;
	}
	
	/////////////////////////////리스트뽑기
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/getList",method= {RequestMethod.GET,RequestMethod.POST})
	public List<CategoryVo> cateList(@RequestBody String id) {
		System.out.println("CategoryController > cateList(ajax)");
		List<CategoryVo> cList = categoryService.getCategoryList(id);
		return cList;
	}
	
	//////////////카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/Delete",method= {RequestMethod.GET,RequestMethod.POST})
	public int cateDelete(@RequestBody int cateNo) {
		System.out.println("CategoryController > cateDelete(ajax)");
		int count =categoryService.cateDelete(cateNo);
		
		return count;
	}
	
}
