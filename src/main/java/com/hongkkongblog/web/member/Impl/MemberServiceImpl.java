package com.hongkkongblog.web.member.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.member.dao.MemberDao;
import com.hongkkongblog.web.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService
{
	@Inject
	private MemberDao memberDao;
	
	@Override
	public List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		// TODO Auto-generated method stub
		return memberDao.selectTest(request, response, model);
	}

	@Override
	public List<HashMap<String, Object>> searchUserData(Map<String, Object> paramMap)
	{
		return memberDao.searchUserData(paramMap);
	}

}
