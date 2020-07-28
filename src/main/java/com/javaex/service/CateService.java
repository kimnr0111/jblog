package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;

@Service
public class CateService {
	
	@Autowired
	private CateDao cateDao;
	
	public List<CateVo> getCateList(String id) {
		System.out.println("CateService:getCateList");
		
		List<CateVo> cateList = cateDao.getCateList(id);
		
		return cateList;
	}
	
	public CateVo cateInsert(CateVo cateVo) {
		cateDao.cateInsert(cateVo);
		System.out.println(cateVo.toString());
		int no = cateVo.getCateNo();
		System.out.println(no);
		CateVo vo = cateDao.selectByNo(no);
		return vo;
	}
	
	public int cateDelete(int cateNo) {
		System.out.println("CateService:cateDelete");
		return cateDao.cateDelete(cateNo);
	}
	
	public int getCateNum(String id) {
		
		return cateDao.getCateNum(id);
	}


}
