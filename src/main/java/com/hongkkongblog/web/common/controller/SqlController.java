package com.hongkkongblog.web.common.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hongkkongblog.web.common.service.SqlService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SqlController
{
	@Autowired
	private SqlService sqlService;

	@RequestMapping(value = "/select/{mapperNamespace}/{sqlMapperId}")
	public ModelAndView select(@PathVariable String mapperNamespace, @PathVariable String sqlMapperId, HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		String sSqlMapperId[] =sqlMapperId.split(".json");

		request.setAttribute("SQL_ID", mapperNamespace + "." + sSqlMapperId[0]);
		
		List<HashMap<String, Object>> mainResultSet = sqlService.list(request, response, model);

		for (int k = 0; k < mainResultSet.size(); k++)
		{
			(mainResultSet.get(k)).put("CHK", "0");
		}

		Gson gson = new Gson();

		model.addAttribute("ajaxValue", gson.toJson(mainResultSet));

		ModelAndView mav = new ModelAndView("common/ajaxPage", "ajaxPage", model);
		return mav;
	}

	@RequestMapping(value = "/update/{mapperNamespace}/{sqlMapperId}")
	public ModelAndView update(@PathVariable String mapperNamespace, @PathVariable String sqlMapperId, HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		request.setAttribute("SQL_ID", mapperNamespace + "." + sqlMapperId);

		sqlService.update(request, response, model);

		model.addAttribute("ajaxValue", "[1]");

		ModelAndView mav = new ModelAndView("common/ajaxPage", "ajaxPage", model);
		return mav;
	}

	@RequestMapping(value = "/insert/{mapperNamespace}/{sqlMapperId}")
	public ModelAndView insert(@PathVariable String mapperNamespace, @PathVariable String sqlMapperId, HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		request.setAttribute("SQL_ID", mapperNamespace + "." + sqlMapperId);

		Object ls_tranNum = sqlService.insert(request, response, model);

		model.addAttribute("ajaxValue", "[" + ls_tranNum + "]");

		ModelAndView mav = new ModelAndView("common/ajaxPage", "ajaxPage", model);
		return mav;
	}

	@RequestMapping(value = "/delete/{mapperNamespace}/{sqlMapperId}")
	public ModelAndView delete(@PathVariable String mapperNamespace, @PathVariable String sqlMapperId, HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		request.setAttribute("SQL_ID", mapperNamespace + "." + sqlMapperId);

		Object ls_tranNum = sqlService.delete(request, response, model);

		model.addAttribute("ajaxValue", "[" + ls_tranNum + "]");

		ModelAndView mav = new ModelAndView("common/ajaxPage", "ajaxPage", model);
		return mav;
	}

}
