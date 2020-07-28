package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@RequestMapping(value="write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PostVo postVo, HttpSession session) {
		System.out.println("post/write");
		System.out.println(postVo.toString());
		postService.write(postVo);
		
		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		
		return "redirect:/" + adminId + "/admin/write";
		
	}

}