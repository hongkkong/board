package com.hongkkongblog.web.common.Impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.common.dao.FileDao;
import com.hongkkongblog.web.common.dao.SqlDao;
import com.hongkkongblog.web.common.service.FileService;
import com.hongkkongblog.web.common.service.SqlService;

@Service
public class FileServiceImpl implements FileService
{
	@Autowired
	private FileDao fileDao;

	@Override
	public int insert(HashMap<String, String> rs_PARAM, Model model)
	{
		return fileDao.insert("file.insert", model);
	}
}
