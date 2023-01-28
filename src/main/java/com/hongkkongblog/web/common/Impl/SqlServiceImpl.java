package com.hongkkongblog.web.common.Impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.common.dao.SqlDao;
import com.hongkkongblog.web.common.service.SqlService;

@Service
public class SqlServiceImpl implements SqlService
{
	@Autowired
	private SqlDao sqlDao;

	public List<HashMap<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlDao.list(request, response, model);
	}

	public int update(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlDao.update(request, response, model);
	}

	public Object insert(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlDao.insert(request, response, model);
	}

	public Object delete(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlDao.delete(request, response, model);
	}

	public HashMap<String, Object> select(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		return sqlDao.select(request, response, model);
	}
}
