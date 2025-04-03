package com.test.pro12.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.pro12.board.dao.BoardDAO;
import com.test.pro12.board.dto.ArticleDTO;
import com.test.pro12.board.dto.ImageDTO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl 
	implements BoardService{
	@Autowired
	BoardDAO dao;

	@Override
	public List<ArticleDTO> listArticles() {
		// TODO Auto-generated method stub
		return dao.listArticles();
	}

	@Override
	public int addArticle
			(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		int articleNo = dao.selectNewArticleNo();
		articleMap.put("articleNo", articleNo);
		
		int result = dao.addArticle(articleMap);
		
		List<ImageDTO> imageFileList = 
			(List<ImageDTO>) articleMap.get("imageFileList");
		int imageFileNo = dao.selectNewImageFileNo();
		
		for(ImageDTO image : imageFileList) {
			image.setImageFileNo(++imageFileNo);
			image.setArticleNo(articleNo);
		}
		
		dao.insertImage(imageFileList);
		return articleNo;
	}

	@Override
	public Map<String, Object> viewArticle(int articleNo) {
		// TODO Auto-generated method stub
		Map<String, Object> articleMap = 
				new HashMap<String,Object>();
		ArticleDTO articleDTO = dao.viewArticle(articleNo);
		List<ImageDTO> imageFileList = 
				dao.selectImageFileList(articleNo);
		
		System.out.println(articleDTO);
		articleMap.put("article", articleDTO);
		articleMap.put("imageFileList", imageFileList);

		return articleMap;
	}


}
