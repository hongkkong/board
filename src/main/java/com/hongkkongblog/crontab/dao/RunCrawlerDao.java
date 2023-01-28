package com.hongkkongblog.crontab.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunCrawlerDao
{
	@Autowired
	private SqlSession sqlSession;

	public void insertInfo(Map<String, Object> param)
	{
		sqlSession.update("runCrawler.insertInfo",param);
	}

	public List<Map<String, Object>> selectInfo(Map<String, Object> paramMap)
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList("runCrawler.selectInfo", paramMap);
	}
}
