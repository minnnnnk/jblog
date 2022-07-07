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
		
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그입니다");
		blogVo.setLogoFile("/assets/images/spring-logo.jpg");
		
		int count = blogDao.blogCreate(blogVo);
		
		System.out.println(userVo);
		System.out.println(blogVo);
		
		return count;
	}
	
	public UserVo loginUser(UserVo userVo) {
		System.out.println("userService > loginUser");
		UserVo uVo  = userDao.loginUser(userVo);
		
		return uVo;
	}
	
	
	public boolean userIdCheck(String id){
		System.out.println("userService > userIdCheck");
		List<String> idList = userDao.userCheck();
		System.out.println(id);
		
		id = id.substring(1,id.length()-1); //"" 빼주는 작업
		
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
