package com.hongkkongblog.web.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService
{
	List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model);

	List<HashMap<String, Object>> searchUserData(Map<String, Object> paramMap);
}
