package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> getPostList(int cateNo, int curPage) {
		System.out.println("PostService:getPostList");
		
		//현재페이지 계산
		if(curPage<=0) {
			curPage = 1;
		}
		curPage = (curPage>0) ? curPage : (curPage=1); // curPage가 0보다 작으면 1페이지(삼항연산자)
		//startRnum
		int startRnum = (curPage-1)*5;
		
		//endRnum
		int endRnum = (curPage*5);
		
		
		List<PostVo> postList = postDao.getPostList(cateNo, startRnum, endRnum);
		
		//현재 글 갯수
		int listCnt = postDao.getList(cateNo);
		System.out.println(listCnt);
		
		//페이지당 글갯수
		int listCount = 5;
		//페이지당 버튼수
		int pageBtnCount = 5;
		
		//현재페이지에서 마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil((curPage/(double)pageBtnCount))*pageBtnCount;
		System.out.println(endPageBtnNo);
		//현재페이지에서 시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		System.out.println(startPageBtnNo);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCount < listCnt) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(listCnt/(double)listCount);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("next", next);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("postList", postList);
		
		return pMap;
	}
	
	public PostVo getPostByNo(int postNo) {
		System.out.println("PostService:getPostByNo");
		
		return postDao.getPostByNo(postNo);
	}
	
	public int getPostNum(int cateNo) {
		
		return postDao.getPostNum(cateNo);
	}

}
