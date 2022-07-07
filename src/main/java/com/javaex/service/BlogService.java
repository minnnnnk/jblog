package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	
	
	public Map<String,Object> blogList(String id){
		System.out.println("BlogService > blogList");
		//카테고리 다오 불러오기
		//CategoryDao categoryDao = new CategoryDao();
		//포스트 다오 불러오기
		//PostDao postDao = new PostDao();
		System.out.println(id);
		
		
		//유저값 가져오기
		
		//카테고리값 가져오기
		//CategoryVo cVo = categoryDao.getCategory(id);
		
		//int cateNo = cVo.getCateNo();
		
		//카테고리 넘버로 포스트값 가져옥;
		//PostVo pVo = postDao.getPost(cateNo);
		
		//블로그값 가져오기
		Map<String,Object> bMap = blogDao.getBlog(id);
		
		//bMap.put("pVo", pVo);
		//bMap.put("cVo", cVo);
		//bMap.put("bVo", bVo);
		
		System.out.println(bMap);
		
		return bMap;
	}
	
	public String basicUpdate(MultipartFile file,BlogVo blogVo) {
		System.out.println("BlogService > basicUpdaete");
		
		if(file.equals(file)) {
			System.out.println("2");
			blogDao.basicUpdate2(blogVo);
			
			return "";
		}else {
			System.out.println("1");
			String saveDir = "C:\\javaStudy\\workspace_web\\jblog\\webapp\\assets\\images";
			
			String orgName = file.getOriginalFilename();
			
			String exName = orgName.substring(orgName.lastIndexOf("."));
					
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
					
			
			String filePath = saveDir +"\\"+ saveName;
			
			blogVo.setLogoFile(saveName);
			blogDao.basicUpdate(blogVo);
			
			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return saveName;
			}
		
	}
	
}
