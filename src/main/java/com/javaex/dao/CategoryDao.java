package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getCategory(String id){
		System.out.println("CategoryDao > getCategory");
		List<CategoryVo>  cList = sqlSession.selectList("category.getCategory", id);
		System.out.println(cList);
		return cList;
	}
	
	public CategoryVo getCateOne(int cateNo) {
		System.out.println("CategoryDao > getCateOne");
		CategoryVo cVo = sqlSession.selectOne("category.getCateone",cateNo);
		
		return cVo;
	}
	
	public int addCategory(CategoryVo cateVo) {
		System.out.println("CategoryDao > addCategory");
		System.out.println(cateVo);
		int count = sqlSession.insert("category.addCategory", cateVo);
		System.out.println(count);
		return count;
	}
	
	public int cateDelete(int cateNo) {
		System.out.println("CategoryDao > cateDelete");
		int count = sqlSession.delete("category.cateDelete",cateNo);
		return count;
	}
}
