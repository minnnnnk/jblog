package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
	
	public Map<String,Object> getBlog(String id) {
		System.out.println("BlogDao > getBlog");
		Map<String,Object> bMap =sqlSession.selectOne("blog.getUser", id);
		
		return bMap;
	}
	
	public int basicUpdate(BlogVo blogVo) {
		System.out.println("BlogDao > basicUpdate");
		
		int count = sqlSession.insert("blog.blogBasicUpdate", blogVo);
		
		return count;
	}
	
	public int basicUpdate2(BlogVo blogVo) {
		System.out.println("BlogDao > basicUpdate");
		
		int count = sqlSession.insert("blog.blogBasicUpdate2", blogVo);
		
		return count;
	}
	
}
