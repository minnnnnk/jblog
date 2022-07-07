package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public CategoryVo getCategory(String id){
		System.out.println("CategoryDao > getCategory");
		CategoryVo cVo = sqlSession.selectOne("category.getCategory", id);
		
		return cVo;
	}
}
