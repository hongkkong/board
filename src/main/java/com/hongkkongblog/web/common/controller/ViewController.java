package com.hongkkongblog.web.common.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hongkkongblog.web.menu.controller.MenuController;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController
{
	@Inject
	private MenuController menuController;
	
	@Inject
	private LogController log;
	
	@Value("#{board['BOARD_DOMAIN']}")
	private String gv_BOARD_DOMAIN;

	@Value("#{board['BOARD_URL']}")
	private String gv_BOARD_URL;

	@Value("#{board['BOARD_NAME']}")
	private String gv_BOARD_NAME;
	
	@RequestMapping(value = "/{menuName}/{pageName}")
	public ModelAndView pageMove(@PathVariable String menuName, @PathVariable String pageName, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		model.addAttribute("MENU_NAME", menuName);
		model.addAttribute("PAGE_NAME", pageName);

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);

		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);
		
		//contents/board/search
		mav.setViewName("contents/" + menuName + "/" + pageName);
		mav.addObject("model", model);
		return mav;
	}
}
