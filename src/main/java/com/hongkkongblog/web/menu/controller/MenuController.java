package com.hongkkongblog.web.menu.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hongkkongblog.web.common.service.SqlService;
import com.hongkkongblog.web.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController
{
	@Inject
	private MenuService menuService;

	@Inject
	private SqlService sqlService;

	@Value("#{board['LIST_LIMIT_NUM']}")
	private String gv_LIST_LIMIT_NUM;

	@Value("#{board['VISIBLE_PAGES']}")
	private String gv_VISIBLE_PAGES;

	@Value("#{board['BOARD_DOMAIN']}")
	private String gv_BOARD_DOMAIN;

	@Value("#{board['BOARD_URL']}")
	private String gv_BOARD_URL;

	@Value("#{board['BOARD_NAME']}")
	private String gv_BOARD_NAME;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView Main(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		List<Map<String, Object>> objList = menuService.selectTest(request, response, model);

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contents/board/write");
		mav.addObject("model", model);

		request.setAttribute("SQL_ID", "common.menuSelectList");
		List<HashMap<String, Object>> menuSelectList = sqlService.list(request, response, model);
		model.addAttribute("MENU_NM", menuSelectList.get(0).get("MENU_NM_KR"));
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_002");// 조회
		// logUtil.ConnetLog(request, response, model);

		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	public Model MenuList(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		request.setAttribute("SQL_ID", "common.menuList");
		List<HashMap<String, Object>> menuList = sqlService.list(request, response, model);

		String sHtml = "";

		String[] sArray =
		{ "bg-blue", "bg-violet", "bg-violet-2", "bg-pink", "bg-red-2", "bg-red" };

		int iNum = 0;
		int iSub = 0;

		sHtml += "<ul class='nav navbar-nav'>";

		for (HashMap<String, Object> sItem : menuList)
		{
			if ("MAIN".equals(sItem.get("PART")) || "MAIN_SUB".equals(sItem.get("PART")))
			{
				model.addAttribute(sItem.get("SEQ") + "", sItem.get("MENU_NM_KR"));

				String sToggleIcon = "";
				String sIcon = "";

				int iRn = Integer.parseInt(sItem.get("RN") + "");

				if (iRn > 0)
				{
					sToggleIcon = "class='dropdown-toggle' data-toggle='dropdown'";
					sIcon = "<b class='caret'></b>";
				}

				sHtml += "\n    <li>";
				sHtml += "\n        <a href='/board/article.do?MENU_CD=" + sItem.get("MENU_CD") + "' " + sToggleIcon + ">";
				sHtml += "\n            <span class='text'>" + sItem.get("MENU_NM_KR") + "" + sIcon + "</span> ";
				sHtml += "\n            <span class='equalizer-bar " + sArray[iNum] + "'></span>";
				sHtml += "\n        </a>";
			}
			else if ("SUB".equals(sItem.get("PART")))
			{
				iSub++;
				int iCnt = Integer.parseInt(sItem.get("RN") + "");
				if (iSub == 1)
				{
					sHtml += "\n        <ul class='dropdown-menu'> ";
				}

				sHtml += "\n        	<li>  ";
				sHtml += "\n                <a href='/board/article.do?MENU_CD=" + sItem.get("MENU_CD") + "'>";
				sHtml += "\n                    <span class='text'>" + sItem.get("MENU_NM_KR") + "</span>";
				sHtml += "\n                </a> ";
				sHtml += "\n            </li>";

				if (iSub == iCnt)
				{
					sHtml += "\n</ul>";
					sHtml += "\n</li> ";
				}
			}

			if ("MAIN".equals(sItem.get("PART")) && !"MAIN_SUB".equals(sItem.get("PART")))
			{
				iNum++;
				sHtml += "\n</li> ";
			}
		}

		sHtml += "\n</ul>";

		// System.out.println("sHtml::>"+sHtml);

		return model.addAttribute("MENU_LIST", sHtml);
	}
}
