package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public int addPost(PostVo postVo) {
		System.out.println("PostService > addPost");
		System.out.println(postVo);
		int count = postDao.addPost(postVo);
		
		return count;
	}
	
}
