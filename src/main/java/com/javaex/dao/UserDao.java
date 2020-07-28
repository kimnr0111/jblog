package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int join(UserVo userVo) {
		System.out.println("UserDao:join");
		
		return sqlSession.insert("user.join", userVo);
	}
	
	public void blog(UserVo userVo) {
		System.out.println("UserDao:blog");
		System.out.println(userVo.toString());
		
		sqlSession.insert("user.createBlog", userVo);
		
		
	}
	
	public UserVo selectUser(String uId) {
		System.out.println("UserDao:selectUser");
		
		return sqlSession.selectOne("user.selectById", uId);
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("UserDao.login");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}

}
