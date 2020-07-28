package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CateVo> getCateList(String id) {
		System.out.println("CateDao:getCateList");
		
		List<CateVo> cateList = sqlSession.selectList("cate.getCateList", id);
		return cateList;
	}
	
	public void cateInsert(CateVo cateVo) {
		System.out.println("CateDao:cateInsert");
		System.out.println("추가하기 전" + cateVo.toString());
		sqlSession.insert("cate.cateInsert", cateVo);
		System.out.println("추가한 후" + cateVo.toString());
		
	}
	
	public CateVo selectByNo(int no) {
		System.out.println("CateDao:selectByNo");
		System.out.println(no);
		return sqlSession.selectOne("cate.selectByNo", no);
	}
	
	public int cateDelete(int cateNo) {
		System.out.println("cateDao:cateDelete");
		
		return sqlSession.delete("cate.cateDelete", cateNo);
	}
	
	public int getCateNum(String id) {
		
		return sqlSession.selectOne("cate.cateNum", id);
	}
}
