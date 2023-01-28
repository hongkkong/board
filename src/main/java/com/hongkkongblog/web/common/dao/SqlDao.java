package com.hongkkongblog.web.common.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class SqlDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<HashMap<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ls_SQL_ID = (String) request.getAttribute("SQL_ID");
		
		return sqlSession.selectList(ls_SQL_ID, model);
	}

	public int update(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ls_SQL_ID = (String) request.getAttribute("SQL_ID");
		return sqlSession.update(ls_SQL_ID, model);
	}

	public Object insert(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ls_SQL_ID = (String) request.getAttribute("SQL_ID");
		return sqlSession.insert(ls_SQL_ID, model);
	}

	public Object delete(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ls_SQL_ID = (String) request.getAttribute("SQL_ID");
		return sqlSession.delete(ls_SQL_ID, model);
	}
	
	public HashMap<String, Object> select(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		String ls_SQL_ID = (String) request.getAttribute("SQL_ID");
		
		return sqlSession.selectOne(ls_SQL_ID, model);
	}
}
