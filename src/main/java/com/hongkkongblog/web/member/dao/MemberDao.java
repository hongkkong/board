package com.hongkkongblog.web.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class MemberDao
{
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlSession.selectList("", model);
	}

	public List<HashMap<String, Object>> searchUserData(Map<String, Object> paramMap)
	{
		return sqlSession.selectList("member.searchUserData", paramMap);
	}
}
