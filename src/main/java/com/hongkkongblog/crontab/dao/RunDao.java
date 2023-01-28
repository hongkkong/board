package com.hongkkongblog.crontab.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<HashMap<String, String>> connect(HashMap<String, String> paramMap)
	{
		return sqlSession.selectList("rDbConnect.run", paramMap);
	}
}
