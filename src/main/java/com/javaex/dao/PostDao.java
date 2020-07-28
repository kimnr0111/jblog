package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public void write(PostVo postVo) {
		System.out.println("PostDao:write");
		
		sqlSession.insert("post.write", postVo);
	}
	
	public List<PostVo> getPostList(int cateNo) {
		System.out.println("PostDao:getPostList");
		
		return sqlSession.selectList("post.getPostList", cateNo);
	}
	
	public PostVo getPostByNo(int postNo) {
		System.out.println("PostDao:getPostByNo");
		
		return sqlSession.selectOne("post.getPostByNo", postNo);
	}
	
	public int getPostNum(int cateNo) {
		
		return sqlSession.selectOne("post.getPostNum", cateNo);
	}

}
