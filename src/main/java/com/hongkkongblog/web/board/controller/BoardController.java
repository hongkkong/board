package com.hongkkongblog.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hongkkongblog.web.board.service.BoardService;
import com.hongkkongblog.web.common.controller.LogController;
import com.hongkkongblog.web.common.service.SqlService;
import com.hongkkongblog.web.menu.controller.MenuController;
import com.hongkkongblog.web.util.BoardUtil;

@Controller
@RequestMapping("/board")
public class BoardController
{
	@Autowired
	private BoardUtil boardUtil;

	@Autowired
	private BoardService boardService;

	@Inject
	private MenuController menuController;

	@Inject
	private LogController log;

	@Autowired
	private SqlService sqlService;

	@Inject
	private BoardCrawler boardCrawler;

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

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView Main(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);

		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);

		mav.setViewName("contents/board/main");
		mav.addObject("model", model);
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchList", method = RequestMethod.GET)
	public ModelAndView SearchList(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		
		int iPageNum = 1;
		request.setAttribute("SQL_ID", "board.searchDataTotal");
		List<HashMap<String, Object>> searchDataTotal = sqlService.list(request, response, model);
		int iTotalCnt = Integer.parseInt(searchDataTotal.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		int iVisibleCnt = Integer.parseInt(gv_VISIBLE_PAGES);

		int iStartNum = 0;

		int iNowPageNum = (iPageNum - 1) * iLimitCnt;

		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}
		model.addAttribute("PAGE_NUM", iPageNum);
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		/* 페이징처리 */
		model.addAttribute("LIST_LIMIT_NUM", iLimitCnt);
		model.addAttribute("TOTAL_CNT", iTotalCnt);
		model.addAttribute("VISIBLE_PAGES", iVisibleCnt);

		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_003");// 조회
		log.connetLog(request, response, model);
		
		mav.setViewName("contents/board/search");
		mav.addObject("model", model);
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ModelAndView Article(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();

		request.setAttribute("SQL_ID", "board.articleTotal");
		List<HashMap<String, Object>> articleTotalList = sqlService.list(request, response, model);
		
		String sPageNum = request.getParameter("PAGE_NUM")==null ? "1" :(request.getParameter("PAGE_NUM")=="" ? "1":request.getParameter("PAGE_NUM"));
		
		int iPageNum = Integer.parseInt(sPageNum);
		int iTotalCnt = Integer.parseInt(articleTotalList.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		int iVisibleCnt = Integer.parseInt(gv_VISIBLE_PAGES);

		int iStartNum = 0;

		int iNowPageNum = (iPageNum - 1) * iLimitCnt;

		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}
		model.addAttribute("PAGE_NUM", iPageNum);
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		/* 페이징처리 */
		model.addAttribute("LIST_LIMIT_NUM", iLimitCnt);
		model.addAttribute("TOTAL_CNT", iTotalCnt);
		model.addAttribute("VISIBLE_PAGES", iVisibleCnt);

		request.setAttribute("SQL_ID", "common.menuSelectList");
		List<HashMap<String, Object>> menuSelectList = sqlService.list(request, response, model);

		model.addAttribute("MENU_NM", menuSelectList.get(0).get("MENU_NM_KR"));
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);
		mav.addObject("model", model);
		mav.setViewName("contents/board/article");
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/listView", method = RequestMethod.GET)
	public ModelAndView ListView(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();

		request.setAttribute("SQL_ID", "board.articleTotal");
		List<HashMap<String, Object>> articleTotalList = sqlService.list(request, response, model);

		String sPageNum = request.getParameter("PAGE_NUM")==null ? "1" :(request.getParameter("PAGE_NUM")=="" ? "1":request.getParameter("PAGE_NUM"));
		
		int iPageNum = Integer.parseInt(sPageNum);
		int iTotalCnt = Integer.parseInt(articleTotalList.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		int iVisibleCnt = Integer.parseInt(gv_VISIBLE_PAGES);

		int iStartNum = 0;

		int iNowPageNum = (iPageNum - 1) * iLimitCnt;

		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}

		model.addAttribute("PAGE_NUM", iPageNum);
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		/* 페이징처리 */
		model.addAttribute("LIST_LIMIT_NUM", iLimitCnt);
		model.addAttribute("TOTAL_CNT", iTotalCnt);
		model.addAttribute("VISIBLE_PAGES", iVisibleCnt);

		request.setAttribute("SQL_ID", "common.menuSelectList");
		List<HashMap<String, Object>> menuSelectList = sqlService.list(request, response, model);

		model.addAttribute("MENU_NM", menuSelectList.get(0).get("MENU_NM_KR"));
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);
		
		boardService.updateHitMain(request, response, model);

		request.setAttribute("SQL_ID", "board.listViewData");
		HashMap<String, Object> listView = sqlService.select(request, response, model);
		mav.addObject("LIST_VIEW", listView);
		HttpSession session = request.getSession();
		
		model.addAttribute("USER_ID", session.getAttribute("USER_ID"));
		model.addAttribute("SESSION_ID", session.getId());
		
		request.setAttribute("SQL_ID", "board.writeViewUserData");
		HashMap<String, Object> btnView = sqlService.select(request, response, model);
		mav.addObject("BTN_VIEW", btnView);
		
		mav.addObject("model", model);
		mav.setViewName("contents/board/listView");

		return mav;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView Write(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		menuController.MenuList(request, response, model);
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
		log.connetLog(request, response, model);

		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView Update(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contents/board/update");
		mav.addObject("model", model);
		HttpSession session = request.getSession();
		
		model.addAttribute("USER_ID", session.getAttribute("USER_ID"));
		model.addAttribute("SESSION_ID", session.getId());
		request.setAttribute("SQL_ID", "board.writeViewData");
		HashMap<String, Object> listView = sqlService.select(request, response, model);
		mav.addObject("LIST_VIEW", listView);
		
		request.setAttribute("SQL_ID", "board.writeViewUserData");
		HashMap<String, Object> btnView = sqlService.select(request, response, model);
		mav.addObject("BTN_VIEW", btnView);
		
		request.setAttribute("SQL_ID", "common.menuSelectList");
		List<HashMap<String, Object>> menuSelectList = sqlService.list(request, response, model);
		model.addAttribute("MENU_NM", menuSelectList.get(0).get("MENU_NM_KR"));
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_002");// 조회
		log.connetLog(request, response, model);

		return mav;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView Login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);

		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);

		mav.setViewName("contents/member/login");
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView Regist(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);
		
		mav.setViewName("contents/member/regist");
		mav.addObject("model", model);
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loginCallback", method = RequestMethod.GET)
	public ModelAndView LoginCallback(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);

		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		log.connetLog(request, response, model);

		mav.setViewName("contents/board/loginCallback");
		mav.addObject("model", model);
		return mav;
	}
	

	@RequestMapping(value = "/writeProccess", method = RequestMethod.POST)
	public ModelAndView writeProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			if ("TITLE".equals(ls_name) || "CONTENT_DESC".equals(ls_name) || "USER_NM".equals(ls_name))
			{
				model.addAttribute(ls_name, boardUtil.replaceText(request.getParameter(ls_name)));

			}
			else
			{
				model.addAttribute(ls_name, request.getParameter(ls_name));
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);

		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		
		HttpSession session = request.getSession();
		String sSessionId = session.getId();
		
		String sCreateId = request.getParameter("CREATE_ID")==null ? "":request.getParameter("CREATE_ID");
		String sUpdateId = request.getParameter("UPDATE_ID")==null ? "":request.getParameter("UPDATE_ID");
		
		if (request.getParameter("SEQ") != null && !"".equals(request.getParameter("SEQ")))
		{
			model.addAttribute("USER_ACT_CD", "ACT_UPDATE_001");// 수정
			model.addAttribute("ajaxValue", gson.toJson(""));// 저장
			
			model.addAttribute("UPDATE_ID", sUpdateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			
			request.setAttribute("SQL_ID", "board.updateBoard");
			sqlService.update(request, response, model);
		}
		else
		{
			String sSeq =boardUtil.randomStr(30);
			
			model.addAttribute("USER_ACT_CD", "ACT_INSERT_001");// 저장
			model.addAttribute("CREATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			model.addAttribute("UPDATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			
			model.addAttribute("SEQ", sSeq);// 저장
			model.addAttribute("ajaxValue", gson.toJson(sSeq));// 저장
			request.setAttribute("SQL_ID", "board.insertBoard");
			sqlService.insert(request, response, model);
		}
		model.addAttribute("ajaxPage", model);

		log.connetLog(request, response, model);

		return mav;
	}
	
	@RequestMapping(value = "/deleteProccess", method = RequestMethod.POST)
	public ModelAndView deleteProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		model.addAttribute("USER_ACT_CD", "ACT_DELETE_001");// 삭제
		model.addAttribute("ajaxValue", gson.toJson(""));// 저장
		request.setAttribute("SQL_ID", "board.deleteBoard");
		sqlService.update(request, response, model);
		request.setAttribute("SQL_ID", "board.deleteComment");
		sqlService.update(request, response, model);
		
		model.addAttribute("ajaxPage", model);
		
		log.connetLog(request, response, model);
		
		return mav;
	}
	
	@RequestMapping(value = "/writeCommentProccess", method = RequestMethod.POST)
	public ModelAndView writeCommentProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			if ("CONTENT_DESC".equals(ls_name) || "COMMENT_USER_NM".equals(ls_name))
			{
				model.addAttribute(ls_name, boardUtil.replaceText(request.getParameter(ls_name)));
			}
			else
			{
				model.addAttribute(ls_name, request.getParameter(ls_name));
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		
		HttpSession session = request.getSession();
		String sSessionId = session.getId();
		
		String sCreateId = request.getParameter("CREATE_ID")==null ? "":request.getParameter("CREATE_ID");
		
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		
		if (request.getParameter("SEQ") != null && !"".equals(request.getParameter("SEQ")))
		{
			model.addAttribute("USER_ACT_CD", "ACT_UPDATE_001");// 수정
			model.addAttribute("CREATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			model.addAttribute("UPDATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			model.addAttribute("ajaxValue", gson.toJson(""));// 저장
			request.setAttribute("SQL_ID", "board.updateComment");
			sqlService.update(request, response, model);
		}
		else
		{
			String sRandStr =boardUtil.randomStr(30);
			
			model.addAttribute("USER_ACT_CD", "ACT_INSERT_001");// 저장
			model.addAttribute("CREATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			model.addAttribute("UPDATE_ID", sCreateId =="" ? sSessionId:session.getAttribute("USER_ID"));// 저장
			model.addAttribute("SEQ", sRandStr);// 저장
			model.addAttribute("ajaxValue", gson.toJson(sRandStr));// 저장
			request.setAttribute("SQL_ID", "board.insertComment");
			sqlService.insert(request, response, model);
		}
		model.addAttribute("ajaxPage", model);
		
		log.connetLog(request, response, model);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteCommentProccess")
	public ModelAndView deleteCommnetProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		model.addAttribute("USER_ACT_CD", "ACT_DELETE_002");// 삭제
		model.addAttribute("ajaxValue", gson.toJson(""));// 저장

		request.setAttribute("SQL_ID", "board.deleteCommentSeq");
		sqlService.update(request, response, model);
		
		model.addAttribute("ajaxPage", model);
		
		log.connetLog(request, response, model);
		
		return mav;
	}

	/**
	 * 크롤링 - 주식
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/stockInfoData.do", method = RequestMethod.GET)
	public ModelAndView StockInfoData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("ajaxValue", boardCrawler.naverStockMain(request, response, model));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		return mav;
	}

	/**
	 * 크롤링 - 비트코인
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/coinInfoData.do", method = RequestMethod.GET)
	public ModelAndView CoinInfoData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("ajaxValue", boardCrawler.bithumbMain(request, response, model));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		return mav;
	}

	/**
	 * 크롤링 - 부동산
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/landInfoData.do", method = RequestMethod.GET)
	public ModelAndView LandInfoData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("ajaxValue", boardCrawler.naverLandMain(request, response, model));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		return mav;
	}

	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchData.do", method = RequestMethod.GET)
	public ModelAndView SearchData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		int iPageNum = Integer.parseInt(request.getParameter("PAGE_NUM") == null ? "1" : (String) request.getParameter("PAGE_NUM"));
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);

		int iStartNum = 0;

		int iNowPageNum = (iPageNum - 1) * iLimitCnt;

		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		
		Gson gson = new Gson();
		ModelAndView mav = new ModelAndView();
		
		List<HashMap<String, Object>> searchList = boardService.searchData(model);
		model.addAttribute("ajaxValue", gson.toJson(searchList));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/searchNextPageData.do", method = RequestMethod.GET)
	public ModelAndView SearchNextPageData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		request.setAttribute("SQL_ID", "board.searchDataTotal");
		List<HashMap<String, Object>> searchDataTotal = sqlService.list(request, response, model);
		
		int iPageNum = Integer.parseInt(request.getParameter("PAGE_NOW_NUM") == null ? "1" : (String) request.getParameter("PAGE_NOW_NUM"));
		int iTotalCnt = Integer.parseInt(searchDataTotal.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		
		int iNowPageTotal = iPageNum * iLimitCnt;
		
		if (iNowPageTotal<iTotalCnt)
		{
			iPageNum++;
		}
		
		HashMap<String, Object> paraMap =new HashMap<String, Object>();
		paraMap.put("PAGE_NEXT_NUM", iPageNum);
		
		Gson gson = new Gson();
		ModelAndView mav = new ModelAndView();
		
		List<HashMap<String, Object>> searchTotalList = new ArrayList<HashMap<String,Object>>();
		
		searchTotalList.add(paraMap);
		
		model.addAttribute("ajaxValue", gson.toJson(searchTotalList));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/articleData.do", method = RequestMethod.GET)
	public ModelAndView articleData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		request.setAttribute("SQL_ID", "board.articleTotal");
		List<HashMap<String, Object>> articleTotalList = sqlService.list(request, response, model);
		
		int iPageNum = Integer.parseInt(request.getParameter("PAGE_NUM") == null ? "1" : (String) request.getParameter("PAGE_NUM"));
		int iTotalCnt = Integer.parseInt(articleTotalList.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		int iVisibleCnt = Integer.parseInt(gv_VISIBLE_PAGES);
		
		int iStartNum = 0;
		
		int iNowPageNum = (iPageNum - 1) * iLimitCnt;
		
		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}
		model.addAttribute("PAGE_NUM", iPageNum);
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		/* 페이징처리 */
		model.addAttribute("LIST_LIMIT_NUM", iLimitCnt);
		model.addAttribute("TOTAL_CNT", iTotalCnt);
		model.addAttribute("VISIBLE_PAGES", iVisibleCnt);
		
		Gson gson = new Gson();
		ModelAndView mav = new ModelAndView();
		
		List<HashMap<String, Object>> article = boardService.articleList(model);
		model.addAttribute("ajaxValue", gson.toJson(article));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		
		return mav;
	}
	
	@RequestMapping(value = "/commentsData.do", method = RequestMethod.GET)
	public ModelAndView commentsData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}
		
		request.setAttribute("SQL_ID", "board.commentsTotal");
		List<HashMap<String, Object>> commentsTotalList = sqlService.list(request, response, model);
		
		int iPageNum = Integer.parseInt(request.getParameter("PAGE_NUM") == null ? "1" : (String) request.getParameter("PAGE_NUM"));
		int iTotalCnt = Integer.parseInt(commentsTotalList.get(0).get("TOTAL_CNT") + "");
		int iLimitCnt = Integer.parseInt(gv_LIST_LIMIT_NUM);
		int iVisibleCnt = Integer.parseInt(gv_VISIBLE_PAGES);
		
		int iStartNum = 0;
		
		int iNowPageNum = (iPageNum - 1) * iLimitCnt;
		
		if (iPageNum > 1)
		{
			iStartNum = iNowPageNum;
		}
		model.addAttribute("PAGE_NUM", iPageNum);
		model.addAttribute("START_NUM", iStartNum);
		model.addAttribute("LIMIT_NUM", iLimitCnt);
		/* 페이징처리 */
		model.addAttribute("LIST_LIMIT_NUM", iLimitCnt);
		model.addAttribute("TOTAL_CNT", iTotalCnt);
		model.addAttribute("VISIBLE_PAGES", iVisibleCnt);
		
		Gson gson = new Gson();
		ModelAndView mav = new ModelAndView();
		List<HashMap<String, Object>> comments = boardService.commentsList(model);
		model.addAttribute("ajaxValue", gson.toJson(comments));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		
		return mav;
	}
}
