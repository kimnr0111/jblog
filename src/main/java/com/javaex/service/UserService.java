package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo userVo) {
		System.out.println("UserService:join");
		int count;
		count = userDao.join(userVo);
		
		if(count == 1) {
			userDao.blog(userVo);
		}
		
		
	}
	
	public boolean checkId(String uId) {
		System.out.println("UserService:checkId");
		UserVo userVo = userDao.selectUser(uId);
		
		boolean result;
		if(userVo == null) {
			System.out.println("비었음");
			result = true;
		} else {
			System.out.println("값있음");
			result = false;
		}
		
		return result;
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("UserService:login");
		 return userDao.login(userVo);
	}

}
