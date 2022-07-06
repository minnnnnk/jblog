package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	
	public int userJoin(UserVo userVo,BlogVo blogVo) {
		System.out.println("userService > userJoin");
		
		userDao.userJoin(userVo);
		System.out.println(blogVo);
		
		int count = blogDao.blogCreate(blogVo);
		
		return count;
	}
	
	public boolean userIdCheck(String id){
		System.out.println("userService > userIdCheck");
		List<String> idList = userDao.userCheck();
		System.out.println(id);
		for(int i =0; i<idList.size(); i++) {
			String uId = idList.get(i);
			System.out.println(uId);
			if(id.equals(uId)) {
				return true;
			}
		}
		return false;
	}
}
