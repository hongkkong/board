package com.hongkkongblog.web.board.Impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.board.dao.BoardDao;
import com.hongkkongblog.web.board.service.BoardService;
import com.hongkkongblog.web.util.BoardUtil;

@Service
public class BoardServiceImpl implements BoardService
{
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private BoardUtil boardUtil;

	@Override
	public List<HashMap<String, Object>> articleList(Model model)
	{
		return boardDao.articleList(model);
	}

	@Override
	public List<HashMap<String, Object>> commentsList(Model model)
	{
		return boardDao.commentsList(model);
	}

	@Override
	public void updateHitMain(HttpServletRequest request, HttpServletResponse response, Model model)
	{

		String sSeq =boardUtil.randomStr(30);
		HttpSession session = request.getSession();
		String sSessionId = session.getId();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("USER_ID", session.getAttribute("USER_ID"));
		paramMap.put("RANDOM_SEQ", sSeq);
		paramMap.put("USER_SESSION_ID", sSessionId);
		
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			paramMap.put(ls_name, request.getParameter(ls_name));
		}
		
		List<Map<String, Object>> oList = boardDao.selectContentsList(paramMap);
		
		if(oList.size()==0)
		{
			boardDao.updateHitMain(paramMap);
		}
	}

	@Override
	public List<HashMap<String, Object>> searchData(Model model)
	{
		return boardDao.searchData(model);
	}

	@Override
	public List<Map<String, Object>> crawlerList(Map<String, Object> paramMap)
	{
		return boardDao.crawlerList(paramMap);
	}
}
