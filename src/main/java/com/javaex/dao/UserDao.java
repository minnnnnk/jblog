package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int userJoin(UserVo userVo) {
		System.out.println("UserDao > userJoin");
		int count = sqlSession.insert("user.userJoin", userVo);
		
		return count;
	}
	
	public List<String> userCheck(){
		System.out.println("UserDao > userCheck");
		List<String> uId = sqlSession.selectList("user.userIdCheck");
		
		return uId;
	}
	
	public UserVo loginUser(UserVo userVo) {
		System.out.println("UserDao > loginUser");
		
		UserVo uVo =sqlSession.selectOne("user.loginUser", userVo);
		
		return uVo;
	}
}
