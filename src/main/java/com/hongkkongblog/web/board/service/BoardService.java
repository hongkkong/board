package com.hongkkongblog.web.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BoardService
{

	List<HashMap<String, Object>> articleList(Model model);

	List<HashMap<String, Object>> commentsList(Model model);

	void updateHitMain(HttpServletRequest request, HttpServletResponse response, Model model);

	List<HashMap<String, Object>> searchData(Model model);

	List<Map<String, Object>> crawlerList(Map<String, Object> paramMap);

}
