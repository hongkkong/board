package com.hongkkongblog.crontab.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunBoardInsertDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> selectCrawlerInfo(Map<String, Object> paramMap)
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList("runBoard.selectCrawlerInfo", paramMap);
	}

	public void insertBoardInfo(Map<String, Object> paramMap)
	{
		sqlSession.update("runBoard.insertBoardInfo",paramMap);
	}

}
