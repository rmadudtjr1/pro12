package com.test.pro12.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.pro12.board.dto.ArticleDTO;
import com.test.pro12.board.dto.ImageDTO;
import com.test.pro12.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardControllerImpl 
	implements BoardController{
	@Autowired
	BoardService service;
	private static final String ARTICLE_REPO
							= "D:\\20241216\\image";
	@Override
	@RequestMapping("/listArticles.do")
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<ArticleDTO> articleList =
				service.listArticles();
		String viewName = 
			(String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleList", articleList);
		
		return mav;
	}

	@Override
	@RequestMapping("/articleForm.do")
	public ModelAndView form(
			@RequestParam(value="parentNo", 
					required=false) Integer parentNo, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String viewName = 
				(String)request.getAttribute("viewName");
		
		return new ModelAndView(viewName);
	}

	@Override
	@RequestMapping(value="/addArticle.do")
	public ResponseEntity<String> addArticle(
			MultipartHttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> articleMap =
				new HashMap<String, Object>();
		
		Enumeration<String> enu =
				request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			articleMap.put(name, value);
		}
		
		// upload 를 통해서 파일이름을 가져오기
		List<String> fileList = 
							upload(request);
		List<ImageDTO> imageFileList = 
							new ArrayList<ImageDTO>();
		System.out.println(fileList.size());
		if(fileList != null && fileList.size() != 0) {
			for(String fileName : fileList) {
				ImageDTO image = new ImageDTO();
				image.setImageFileName(fileName);
				imageFileList.add(image);
			}
			articleMap.put("imageFileList", imageFileList);
		}
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(articleMap.get("parentNo") == null) {
			articleMap.put("parentNo", 0);
		} else {
			int parentNo = 
				Integer.parseInt
					((String)articleMap.get("parentNo"));
			articleMap.put("parentNo", parentNo);
		}
		
		articleMap.put("id", id);
				
		ResponseEntity<String> resEntity = null;
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type", 
					"text/html;charset=utf-8");
		String msg = null;
		
		try {
			int articleNo = 
					service.addArticle(articleMap);
			if(imageFileList != null && 
					imageFileList.size() != 0) {
				for(ImageDTO image : imageFileList) {
					File srcFile = new 
						File(ARTICLE_REPO+"//"+"temp//"+
								image.getImageFileName());
					File destDir = 
						new File(ARTICLE_REPO + "//" + articleNo);
					FileUtils.moveFileToDirectory
						(srcFile, destDir, true);
				}
			}
			msg = "<script>";
			msg += "alert('새글이 추가되었습니다.');";
			msg += "location.href='/pro12/board/listArticles.do';";
			msg += "</script>";
			resEntity = new ResponseEntity<String>
					(msg, resHeader, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			if(imageFileList != null &&
				imageFileList.size() != 0) {
				for(ImageDTO image : imageFileList) {
					File srcFile = new 
						File(ARTICLE_REPO+"//"+"temp//"
								+image.getImageFileName());
					srcFile.delete();	
				}
				
			}
			
			
			msg = "<script>";
			msg += "alert('오류가 발생했습니다. 다시 시도하세요.');";
			msg += "location.href='/pro12/board/articleForm.do';";
			msg += "</script>";
			resEntity = new ResponseEntity<String>
					(msg, resHeader, HttpStatus.CREATED);
			e.printStackTrace();
			
		}
		return resEntity;
	}
	
	private List<String> upload
			(MultipartHttpServletRequest request) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = request.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = request.getFile(fileName);
			String imageFileName = mFile.getOriginalFilename();
			File file = new File(ARTICLE_REPO + "//" + fileName);
			fileList.add(imageFileName);
			
			if(mFile.getSize() != 0) {
				if(!file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_REPO+"//"
						+"temp//"+imageFileName));
			}
		}
		
		return fileList;
	}
	
	@Override
	@RequestMapping("/viewArticle.do")
	public ModelAndView viewArticle(
			int articleNo, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String viewName = 
				(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		Map articleMap = service.viewArticle(articleNo);
		
		mav.addObject("articleMap", articleMap);
		return mav;
	}

	@Override
	public ModelAndView modArticle(MultipartHttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView removeArticle(int articleNo, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView deleteImage(String imageFileName, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
