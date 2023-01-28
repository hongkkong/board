package com.hongkkongblog.web.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class FileDao
{
	@Autowired
	private SqlSession sqlSession;

	public int insert(String string, Model model)
	{
		return sqlSession.update("file.insert", model);
	}
}
