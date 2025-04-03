package com.test.pro12.board.service;

import java.util.List;
import java.util.Map;

import com.test.pro12.board.dto.ArticleDTO;

public interface BoardService {

	List<ArticleDTO> listArticles();

	int addArticle(Map<String, Object> articleMap);

	Map viewArticle(int articleNo);

}
