package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getPost(int cateNo){
		System.out.println("PostDao > getPost");
		List<PostVo> pList = sqlSession.selectList("post.getPost", cateNo);
		
		return pList;
	}
	
	public int addPost(PostVo postVo) {
		System.out.println("PostDao > addPost");
		
		int count = sqlSession.insert("post.addPost", postVo);
		
		return count;
	}
}
