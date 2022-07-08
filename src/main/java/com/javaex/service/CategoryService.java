package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public int addCategory(CategoryVo cateVo) {
		System.out.println("CategoryService  >  addCategory");
		System.out.println(cateVo);
		int count = categoryDao.addCategory(cateVo);
		
		return count;
	}
	
	public Map<String,Object> getCategory(String id){
		System.out.println("CategoryService  >  getCategory");
		System.out.println(id);
		BlogDao bDao = new BlogDao();
		
		Map<String,Object> bMap = bDao.getBlog(id);
		
		List<CategoryVo> cList = categoryDao.getCategory(id);
		
		Map<String,Object> cMap = new HashMap<String,Object>();
		
		cMap.put("cList", cList);
		cMap.put("bMap", bMap);
		System.out.println(cMap);
		
		return cMap;
	}
}
