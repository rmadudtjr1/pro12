package com.test.pro12.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.pro12.board.dto.ArticleDTO;
import com.test.pro12.board.dto.ImageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	SqlSession session;

	@Override
	public List<ArticleDTO> listArticles() {
		// TODO Auto-generated method stub
		return session.selectList
				("mapper.board.selectAllArticleList");
	}

	@Override
	public int addArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		return session.insert
			("mapper.board.insertArticle",articleMap);
	}

	@Override
	public int selectNewArticleNo() {
		// TODO Auto-generated method stub
		return session.selectOne
				("mapper.board.selectNewArticleNo");
	}

	@Override
	public int selectNewImageFileNo() {
		// TODO Auto-generated method stub
		return session.selectOne
				("mapper.board.selectNewImageFileNo");
	}

	@Override
	public void insertImage(List<ImageDTO> imageFileList) {
		// TODO Auto-generated method stub
		session.insert
		("mapper.board.insertNewImage", imageFileList);
	}

	@Override
	public ArticleDTO viewArticle(int articleNo) {
		// TODO Auto-generated method stub
		return session.selectOne
			("mapper.board.selectArticle", articleNo);
	}

	@Override
	public List<ImageDTO> selectImageFileList(int articleNo) {
		// TODO Auto-generated method stub
		return session.selectList
			("mapper.board.selectImageFileList", articleNo);
	}
}



