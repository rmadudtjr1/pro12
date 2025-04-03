package com.test.pro12.board.dao;

import java.util.List;
import java.util.Map;

import com.test.pro12.board.dto.ArticleDTO;
import com.test.pro12.board.dto.ImageDTO;

public interface BoardDAO {
	List<ArticleDTO> listArticles();
	int addArticle(Map<String, Object> articleMap);
	int selectNewArticleNo();
	int selectNewImageFileNo();
	void insertImage(List<ImageDTO> imageFileList);
	ArticleDTO viewArticle(int articleNo);
	List<ImageDTO> selectImageFileList(int articleNo);
}
