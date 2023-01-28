package com.hongkkongblog.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hongkkongblog.web.admin.service.AdminService;
import com.hongkkongblog.web.util.BoardUtil;
import com.hongkkongblog.web.util.RandomStringBuilder;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminMainController
{
	@Autowired
	private AdminService adminService;

	@Autowired
	private BoardUtil boardUtil;

	@Autowired
	private RandomStringBuilder randomStringBuilder;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ModelAndView mav = new ModelAndView("admin/contents/main", "model", model);
		return mav;
	}
}
