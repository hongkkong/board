package com.hongkkongblog.web.menu.Impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.member.dao.MemberDao;
import com.hongkkongblog.web.member.service.MemberService;
import com.hongkkongblog.web.menu.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService
{
	@Inject
	private MemberDao memberDao;
	
	@Override
	public List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		// TODO Auto-generated method stub
		return memberDao.selectTest(request, response, model);
	}

}
