package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<PostVo> getPostList(int cateNo, int startRnum, int endRnum) {
		System.out.println("PostDao:getPostList");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cateNo", cateNo);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		System.out.println("PostDao:Map" + map.toString());
		
		List<PostVo> postList = sqlSession.selectList("post.getPostList", map);
		
		System.out.println(postList.toString());
		
		return postList;
	}
	
	public PostVo getPostByNo(int postNo) {
		System.out.println("PostDao:getPostByNo");
		
		return sqlSession.selectOne("post.getPostByNo", postNo);
	}
	
	public int getPostNum(int cateNo) {
		
		return sqlSession.selectOne("post.getPostNum", cateNo);
	}
	
	public int getList(int cateNo) {
		
		return sqlSession.selectOne("post.getList", cateNo);
	}

}
