package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public int addCategory(CategoryVo cateVo) {
		System.out.println("CategoryService  >  addCategory");
		
		int count = categoryDao.addCategory(cateVo);
		
		return count;
	}
}
