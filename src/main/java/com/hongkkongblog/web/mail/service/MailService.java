package com.hongkkongblog.web.mail.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MailService
{
	List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model);
}
