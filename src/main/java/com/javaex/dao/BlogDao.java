package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlog(String newPath) {
		System.out.println("BlogDao:getBlog");
		BlogVo blogVo = sqlSession.selectOne("blog.getBlog", newPath);
		System.out.println(blogVo.toString());
		return blogVo;
	}
	
	public void updateAdmin(String orgName, String blogTitle, String id) {
		System.out.println("BlogDao.updateAdmin");
		
		BlogVo blogVo = new BlogVo(id, blogTitle, orgName);
		System.out.println(blogVo.toString());
		
		sqlSession.update("blog.updateBlog", blogVo);
	}
	
	public void updateBlogTitle(String blogTitle, String id) {
		System.out.println("BlogDao.updateBlogTitle");
		
		BlogVo blogVo = new BlogVo(id, blogTitle, "");
		
		sqlSession.update("updateBlogTitle", blogVo);
	}

}
