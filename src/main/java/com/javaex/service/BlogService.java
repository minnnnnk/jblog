package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired 
	private CategoryDao cateDao;
	
	@Autowired
	private PostDao postDao;
	
	public Map<String,Object> blogList(String id, int cateNo){
		System.out.println("BlogService > blogList");
		System.out.println(id);
		
		//블로그값 가져오기
		Map<String,Object> bMap = blogDao.getBlog(id);
		System.out.println(bMap);
		List<CategoryVo> cList = cateDao.getCategory(id);
		
		List<PostVo> pList = postDao.getPost(cateNo);
		System.out.println(pList);
		bMap.put("cList", cList);
		bMap.put("pList", pList);
		
		System.out.println(bMap);
		
		return bMap;
	}
	
	public Map<String,Object> blogBasic(String id){
		System.out.println("BlogService > blogList");
		System.out.println(id);
		
		//블로그값 가져오기
		Map<String,Object> bMap = blogDao.getBlog(id);
		
		System.out.println(bMap);
		
		return bMap;
	}
	

	
	public String basicUpdate(MultipartFile file,BlogVo blogVo) {
		System.out.println("BlogService > basicUpdaete");
		
			System.out.println("1");
			
			String saveDir = "C:\\javaStudy\\upload";
			
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
