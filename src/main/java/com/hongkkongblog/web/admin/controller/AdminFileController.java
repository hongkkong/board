package com.hongkkongblog.web.admin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongkkongblog.web.admin.service.AdminFileService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class AdminFileController {

	@Autowired
	private AdminFileService adminfileService;
	
	
	@RequestMapping(value = "/fileList", method = RequestMethod.GET)
	public String file(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("<::::::::::::::: connect jdbc :::::::::::::::::>");

		return "customer/fileUpLoad";
	}
	
	@RequestMapping(value = "/fileWarUpLoad", method = RequestMethod.POST)
	public String fileWarUpLoad(HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException
	{	
		
		System.out.println("<::111111111111111111111 :::::::::::::::::>");
		
		adminfileService.fileWarUpLoad(request,response,model);
	    System.out.println("<::2222222222222222 :::::::::::::::::>");
		return "main/customer/fileUpLoad";
	}
	
	
	@RequestMapping(value = "/fileUpLoad", method = RequestMethod.POST)
	public String fileUpLoad(HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException
	{
		adminfileService.fileUpLoad(request,response,model);
	    
		return "main/customer/fileUpLoad";
		  
	}
}
