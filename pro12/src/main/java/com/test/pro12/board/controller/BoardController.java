package com.test.pro12.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.pro12.board.dto.ArticleDTO;

public interface BoardController {
	public ModelAndView listArticles(
			HttpServletRequest request, 
			HttpServletResponse response);
	public ModelAndView form(
			@RequestParam(value="parentNo", required=false) 
				Integer parentNo,
			HttpServletRequest request, 
			HttpServletResponse response);
	public ResponseEntity<String> addArticle(
			MultipartHttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	public ModelAndView viewArticle(
			@RequestParam("articleNo") int articleNo,
			HttpServletRequest request, 
			HttpServletResponse response);
	public ModelAndView modArticle(
			MultipartHttpServletRequest request, 
			HttpServletResponse response);
	public ModelAndView removeArticle(
			@RequestParam("articleNo") int articleNo,
			HttpServletRequest request, 
			HttpServletResponse response);
	public ModelAndView deleteImage(
			@RequestParam("image") String imageFileName,
			HttpServletRequest request, 
			HttpServletResponse response);
}
