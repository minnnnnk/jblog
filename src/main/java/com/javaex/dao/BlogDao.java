package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int blogCreate(BlogVo blogVo) {
		System.out.println("BlogDao > blogCreate");
		
		int count = sqlSession.insert("blog.blogInsert", blogVo);
		
		return count;
	}
	
}
