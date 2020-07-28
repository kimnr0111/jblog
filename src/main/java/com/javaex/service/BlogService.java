package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getBlog(String newPath) {
		System.out.println("BlogService:getBlog");
		BlogVo blogVo = blogDao.getBlog(newPath);
		
		return blogVo;
	}
	
	public void updateAdmin(MultipartFile file, String blogTitle, String adminId) {
		System.out.println("BlogService:updateAdmin");
		if(file.isEmpty()) {
			blogDao.updateBlogTitle(blogTitle, adminId);
		} else {
			//파일카피////////////////////////////////////////
			String saveDir = "C:\\javaStudy\\upload";
			
			//원파일이름
			String orgName = file.getOriginalFilename();
			System.out.println("orgName:" + orgName);
			
			//확장자
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			System.out.println("exName:" + exName);
			
			//저장파일이름
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			System.out.println("saveName:" + saveName);
			
			//파일경로
			String filePath = saveDir + "\\" + saveName;
			System.out.println(filePath);
			
			//파일사이즈
			long fileSize = file.getSize();
			System.out.println(fileSize);
			
			//파일 서버에 복사///////////////////////////////////
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
				bout.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("실패");
			}
		
		
		blogDao.updateAdmin(saveName, blogTitle, adminId);
		}
		
		
	}

}
