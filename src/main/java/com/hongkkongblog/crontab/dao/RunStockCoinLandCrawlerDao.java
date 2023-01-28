package com.hongkkongblog.crontab.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunStockCoinLandCrawlerDao
{
	@Autowired
	private SqlSession sqlSession;

	public void insertContents(Map<String, Object> paramMap)
	{
		sqlSession.update("runStockCoinLandCrawler.insertContents",paramMap);
	}

	public void deleteContents(Map<String, Object> searchMap)
	{
		// TODO Auto-generated method stub
		sqlSession.update("runStockCoinLandCrawler.deleteContents",searchMap);
	}
}
