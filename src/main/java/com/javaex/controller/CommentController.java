package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.vo.CommentVo;

@Controller
public class CommentController {

	@ResponseBody
	@RequestMapping(value="/{id}/Comment/add")
	public String cmtAdd(@RequestBody CommentVo CommentVo) {
		System.out.println("CommentController > cmtAdd");
		
		return "";
	}
}
