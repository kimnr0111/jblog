package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	PostDao postDao;
	
	public void write(PostVo postVo) {
		System.out.println("PostService:write");
		postDao.write(postVo);
	}
	
	public List<PostVo> getPostList(int cateNo) {
		System.out.println("PostService:getPostList");
		return postDao.getPostList(cateNo);
	}
	
	public PostVo getPostByNo(int postNo) {
		System.out.println("PostService:getPostByNo");
		
		return postDao.getPostByNo(postNo);
	}
	
	public int getPostNum(int cateNo) {
		
		return postDao.getPostNum(cateNo);
	}

}
