package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public PostVo getPost(int cateNo){
		System.out.println("PostDao > getPost");
		PostVo pVo = sqlSession.selectOne("post.getPost", cateNo);
		
		return pVo;
	}
}
