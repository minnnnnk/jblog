package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BlogDao bDao;
	
	//ajax로 작업
	public CategoryVo addCategory(CategoryVo cateVo) {
		System.out.println("CategoryService  >  addCategory");
		categoryDao.addCategory(cateVo);
		
		String id = cateVo.getId();
		
		List<CategoryVo> cList = categoryDao.getCategory(id);
		
		int cateNo = cList.get(0).getCateNo();
		
		System.out.println(cateNo);
		
		CategoryVo cVo = categoryDao.getCateOne(cateNo);
		
		return cVo;
	}
	
	//카테고리 메인에넣기
	public Map<String,Object> getCategory(String id){
		System.out.println("CategoryService  >  getCategory");
		System.out.println(id);
		
		Map<String,Object> bMap = bDao.getBlog(id);
		
		List<CategoryVo> cList = categoryDao.getCategory(id);
		
		Map<String,Object> cMap = new HashMap<String,Object>();
		
		cMap.put("cList", cList);
		cMap.put("bMap", bMap);
		System.out.println(cMap);
		
		return cMap;
	}
	
	//ajax로 뺴줘서 랜더하려고
	public List<CategoryVo> getCategoryList(String id){
		System.out.println("CategoryService  >  getCategoryList");
		
		id = id.substring(1,id.length()-1); //"" 빼주는 작업
		
		List<CategoryVo> cList = categoryDao.getCategory(id);
		
		return cList;
	}
	
	//삭제
	public int cateDelete(int cateNo) {
		System.out.println("CategoryService  >  cateDelete");
	  
		int count = categoryDao.cateDelete(cateNo);
	  
		return count;
	}
	
	 
}
