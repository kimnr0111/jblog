package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CateService;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("cate")
public class CateController {
	
	@Autowired
	private CateService cateService;
	
	@ResponseBody
	@RequestMapping(value="list", method={RequestMethod.GET, RequestMethod.POST})
	public List<CateVo> list(HttpSession session) {
		System.out.println("cate/list");
		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		List<CateVo> cateList = cateService.getCateList(adminId);
		System.out.println(cateList.toString());
		
		
		return cateList;
	}
	
	@ResponseBody
	@RequestMapping(value="cateInsert", method={RequestMethod.GET, RequestMethod.POST})
	public CateVo cateInsert(@RequestBody CateVo cateVo) {
		System.out.println("cate/cateInsert");
		
		System.out.println(cateVo.toString());
		
		CateVo vo = cateService.cateInsert(cateVo);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="cateDelete", method={RequestMethod.GET, RequestMethod.POST})
	public int cateDelete(@RequestBody CateVo cateVo) {
		System.out.println("cate/cateDelete");
		int cateNo = cateVo.getCateNo(); //받아온 카테고리번호 저장
		int count = cateService.cateDelete(cateNo);
		return count;
	}

}
