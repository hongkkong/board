package com.hongkkongblog.web.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class BoardDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> articleList(Model model)
	{
		return sqlSession.selectList("board.articleList", model);
	}

	public List<HashMap<String, Object>> commentsList(Model model)
	{
		return sqlSession.selectList("board.commentsData", model);
	}

	public void updateHitMain(Map<String, Object> paramMap)
	{
		sqlSession.update("board.hitMainData", paramMap);
	}

	public List<Map<String, Object>> selectContentsList(Map<String, Object> paramMap)
	{
		return sqlSession.selectList("board.selectContentsList", paramMap);
	}

	public List<HashMap<String, Object>> searchData(Model model)
	{
		return sqlSession.selectList("board.searchData", model);
	}

	public List<Map<String, Object>> crawlerList(Map<String, Object> paramMap)
	{
		return sqlSession.selectList("board.crawlerList", paramMap);
	}
}
