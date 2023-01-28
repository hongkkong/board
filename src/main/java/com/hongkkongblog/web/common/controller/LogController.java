package com.hongkkongblog.web.common.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongkkongblog.web.common.service.SqlService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/view")
public class LogController
{
	@Inject
	private SqlService sqlService;
	
	public void connetLog(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		HttpSession session = request.getSession();
		String sSessionId = session.getId();

		String sIp = request.getHeader("X-Forwarded-For");

		if (sIp == null)
		{
			sIp = request.getHeader("Proxy-Client-IP");
		}

		if (sIp == null)
		{
			sIp = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}

		if (sIp == null)
		{
			sIp = request.getHeader("HTTP_CLIENT_IP");
		}

		if (sIp == null)
		{
			sIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (sIp == null)
		{
			sIp = request.getRemoteAddr();
		}

		String sUserAgent = request.getHeader("User-Agent").toUpperCase();
		String sWebMobile = "";

		if (sUserAgent.indexOf("MOBILE") > -1)
		{
			if (sUserAgent.indexOf("PHONE") == -1)
			{
				sWebMobile = "MOBILE";
			}
			else
			{
				sWebMobile = "TABLET";
			}
		}
		else
		{
			sWebMobile = "WEB";
		}
		System.out.println("session id::>" + sSessionId);
		System.out.println("ip::>" + sIp);
		System.out.println("sWebMobile::>" + sWebMobile);

		model.addAttribute("USER_ADDR_IP", sIp);
		model.addAttribute("USER_SESSION", sSessionId);
		model.addAttribute("CONNECT_TYPE_CD", sWebMobile);

		request.setAttribute("SQL_ID", "common.insertLog");
		sqlService.update(request, response, model);
	}
}
