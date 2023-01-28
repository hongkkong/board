package com.hongkkongblog.web.common.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public interface SqlService
{

	List<HashMap<String, Object>> list(HttpServletRequest request, HttpServletResponse response, Model model);

	int update(HttpServletRequest request, HttpServletResponse response, Model model);

	Object insert(HttpServletRequest request, HttpServletResponse response, Model model);

	Object delete(HttpServletRequest request, HttpServletResponse response, Model model);

	HashMap<String, Object> select(HttpServletRequest request, HttpServletResponse response, Model model);

}
