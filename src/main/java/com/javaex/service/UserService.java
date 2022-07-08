package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao cateDao;
	
	public int userJoin(UserVo userVo,BlogVo blogVo,CategoryVo cateVo) {
		System.out.println("userService > userJoin");
		
		//유저 생성
		userDao.userJoin(userVo);
		
		//블로그Vo에다가 담아주기
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그입니다");
		blogVo.setLogoFile("/assets/images/spring-logo.jpg");
		//블로그 생성
		blogDao.blogCreate(blogVo);
		
		//카테고리
		cateVo.setCateName("미분류");
		cateVo.setDescription("기본으로 만들어지는 카테고리 입니다");
		
		int count = cateDao.addCategory(cateVo);
		
		System.out.println(userVo);
		System.out.println(blogVo);
		System.out.println(cateVo);
		
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
