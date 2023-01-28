package com.hongkkongblog.web.mail.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class MailDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlSession.selectList("", model);
	}
}
