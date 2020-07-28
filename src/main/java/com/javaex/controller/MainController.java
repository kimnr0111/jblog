package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	@Autowired
	private PostService postService;

	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String jblog(HttpSession session) {
		System.out.println("main");
		UserVo uInfo = (UserVo) session.getAttribute("authUser");
		if(uInfo != null) {
			System.out.println(uInfo.toString());
		}
		
		return "main/index";
	}

	@RequestMapping(value="{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String jblogConnect(Model model, @PathVariable("id") String id,
							   @RequestParam(value="crtCateNo", required = false, defaultValue="0") int cateNo,
							   @RequestParam(value="postNo", required = false, defaultValue="0") int postNo,
							   @RequestParam(value="page", required=false, defaultValue="1") int curPage) {
		
		System.out.println(id);
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		List<CateVo> cateList = cateService.getCateList(id);
		model.addAttribute("cateList", cateList);
		System.out.println(cateList.toString());
		if(cateNo == 0) {
			cateNo = cateService.getCateNum(id);
			System.out.println(cateNo);
		}
		boolean cateListFlag = false;
		for(int i=0;i<cateList.size();i++) {
			if(cateList.get(i).getCateNo() == cateNo) {
				cateListFlag = true;
			}
		}
		
		if(cateListFlag) {
			Map<String, Object> postMap = postService.getPostList(cateNo, curPage);
			System.out.println(postMap.toString());
			
			model.addAttribute("postMap", postMap);
			if(postNo == 0) {
				System.out.println("카테고리넘버"+cateNo);
				postNo = postService.getPostNum(cateNo);
			}
			PostVo postVo = postService.getPostByNo(postNo);
			if(cateNo == postVo.getCateNo()) {
				System.out.println("같음");
			}
			model.addAttribute("postVo", postVo);
		}
		
		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="upload", method={RequestMethod.GET, RequestMethod.POST})
	public String upload() {
		System.out.println("upload");
		return "";
	}
	
	@RequestMapping(value="{id}/admin/basic", method={RequestMethod.GET, RequestMethod.POST})
	public String admin(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("admin");
		
		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		System.out.println(id);
		System.out.println(adminId);
		if(adminId.equals(id)) {
			BlogVo blogVo = blogService.getBlog(adminId);
			model.addAttribute("blogVo", blogVo);
			
			return "blog/admin/blog-admin-basic";
		} else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="{id}/admin/cate", method={RequestMethod.GET, RequestMethod.POST})
	public String cate(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("admin/cate");
		
		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		System.out.println(id);
		System.out.println(adminId);
		if(adminId.equals(id)) {
			BlogVo blogVo = blogService.getBlog(adminId); //블로그정보 객체에 저장
			
			model.addAttribute("blogVo", blogVo); //모델에 블로그정보 추가
			
			
			return "blog/admin/blog-admin-cate";
		} else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="{id}/admin/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(Model model, HttpSession session, @PathVariable("id") String id) {
		System.out.println("admin/write");
		
		
		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		System.out.println(id);
		System.out.println(adminId);
		if(adminId.equals(id)) {
			BlogVo blogVo = blogService.getBlog(adminId);
			model.addAttribute("blogVo", blogVo);
			List<CateVo> cateList = cateService.getCateList(adminId);
			System.out.println(cateList.toString());
			model.addAttribute("cateList", cateList);
			return "blog/admin/blog-admin-write";
		} else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="admin/update", method={RequestMethod.GET, RequestMethod.POST})
	public String updateAdmin(@RequestParam("blogTitle") String blogTitle,
							  @RequestParam(value="file", required = false) MultipartFile file,
							  HttpSession session) {
		System.out.println("admin/update");

		String adminId = ((UserVo) session.getAttribute("authUser")).getId();
		
		blogService.updateAdmin(file, blogTitle, adminId);
		
		return "redirect:/" + adminId;
	}

}
