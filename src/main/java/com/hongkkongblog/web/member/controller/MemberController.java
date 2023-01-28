package com.hongkkongblog.web.member.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hongkkongblog.web.common.service.SqlService;
import com.hongkkongblog.web.mail.Impl.MailServiceImpl;
import com.hongkkongblog.web.mail.controller.MailController;
import com.hongkkongblog.web.member.service.MemberService;
import com.hongkkongblog.web.menu.controller.MenuController;
import com.hongkkongblog.web.util.BoardUtil;
import com.hongkkongblog.web.util.Encrypt;
import com.hongkkongblog.web.util.RandomStringBuilder;

@Controller
@RequestMapping("/member")
public class MemberController
{
	@Inject
	private MenuController menuController;

	@Inject
	private MemberService memberService;

	@Inject
	private MailController mailController;

	@Inject
	private MailServiceImpl mailServiceImpl;

	@Inject
	private BoardUtil boardUtil;

	@Inject
	private Encrypt encrypt;

	@Inject
	private RandomStringBuilder randomStringBuilder;

	@Inject
	private SqlService sqlService;

	@Inject
	private SqlSession sqlSession;

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
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		connetLog(request, response, model);
		
		mav.setViewName("contents/member/regist");
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/idPassword", method = RequestMethod.GET)
	public ModelAndView idPassword(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		menuController.MenuList(request, response, model);
		ModelAndView mav = new ModelAndView();
		model.addAttribute("BOARD_DOMAIN", gv_BOARD_DOMAIN);
		model.addAttribute("BOARD_URL", gv_BOARD_URL);
		model.addAttribute("BOARD_NAME", gv_BOARD_NAME);
		
		model.addAttribute("USER_ACT_CD", "ACT_SELECT_001");// 조회
		connetLog(request, response, model);
		
		mav.setViewName("contents/member/idPassword");
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/logonProccess.do", method = RequestMethod.POST)
	public ModelAndView logonProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);

		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		Map<String, Object> paramMap = new HashMap<String, Object>();

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			if("USER_ID".equals(ls_name))
			{
				paramMap.put(ls_name, request.getParameter(ls_name));
			}
			else 
			{
				paramMap.put(ls_name, boardUtil.encrypt(request.getParameter(ls_name)));
			}
		}
		
		Gson gson = new Gson();
		
		List<HashMap<String, Object>> searchList = memberService.searchUserData(paramMap);
		
		HttpSession session = request.getSession();
		
		if (searchList.size() > 0)
		{
			for (int k = 0; k < searchList.size(); k++)
			{
				Map<String, Object> sMap =searchList.get(k);
				session.setAttribute("SEQ", sMap.get("SEQ"));
				session.setAttribute("USER_NM", boardUtil.decrypt((String) sMap.get("USER_NM")));
				session.setAttribute("USER_ID", sMap.get("USER_ID"));
				session.setAttribute("USER_EMAIL", boardUtil.decrypt((String)sMap.get("USER_EMAIL")));
				session.setAttribute("USER_ADDR_IP", requestIp(request));
				session.setAttribute("USER_SESSION", session.getId());
				session.setAttribute("BLOCK_YN", sMap.get("BLOCK_YN"));
				session.setAttribute("CONNECT_TYPE_CD", connectDevice(request));
			}
			connetLog(request, response, model);
		}
		
		model.addAttribute("ajaxValue", gson.toJson(searchList));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");

		return mav;
	}
	
	@RequestMapping(value = "/logoutProcess.do")
	public ModelAndView logoutProcess(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		model.addAttribute("ajaxValue", "[]");
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");
		return mav;
	}
	
	@RequestMapping(value = "/registProccess.do", method = RequestMethod.POST)
	public ModelAndView registProccess(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			paramMap.put(ls_name, request.getParameter(ls_name));
		}

		HttpSession session = request.getSession();
		String sSessionId = session.getId();

		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);

		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");

		if (request.getParameter("SEQ") != null && !"".equals(request.getParameter("SEQ")))
		{
			model.addAttribute("USER_ACT_CD", "ACT_UPDATE_001"); // 수정
			model.addAttribute("ajaxValue", gson.toJson(paramMap.get("SEQ"))); // 저장
			model.addAttribute("USER_NM", boardUtil.encrypt((String) paramMap.get("USER_NM"))); // 사용자명
			model.addAttribute("USER_ADDR_IP", requestIp(request)); // IP
			model.addAttribute("USER_SESSION", sSessionId); // 세션
			model.addAttribute("USER_PASSWORD", boardUtil.encrypt((String) paramMap.get("USER_PASSWORD"))); // 비밀번호
			model.addAttribute("SEQ", paramMap.get("SEQ"));// seq
			model.addAttribute("CONNECT_TYPE_CD", connectDevice(request)); // 로그인
			request.setAttribute("SQL_ID", "member.updateUserInfo");
			sqlService.update(request, response, model);
		}
		else
		{
			String sSeq = boardUtil.randomStr(30);

			model.addAttribute("USER_ACT_CD", "ACT_INSERT_001"); // 저장
			model.addAttribute("ajaxValue", gson.toJson(sSeq)); // 저장
			model.addAttribute("USER_NM", boardUtil.encrypt((String) paramMap.get("USER_NM"))); // 사용자명
			model.addAttribute("USER_ID", paramMap.get("USER_ID")); // 사용자 아이디
			model.addAttribute("USER_EMAIL", boardUtil.encrypt((String) paramMap.get("USER_EMAIL"))); // 이메일
			model.addAttribute("USER_ADDR_IP", requestIp(request)); // IP
			model.addAttribute("USER_SESSION", sSessionId); // 세션
			model.addAttribute("USER_PASSWORD", boardUtil.encrypt((String) paramMap.get("USER_PASSWORD"))); // 비밀번호
			model.addAttribute("BLOCK_YN", "N"); // 잠김여부
			model.addAttribute("USER_LEVEL_CD", "NORMAL"); // 사용자 레벨
			model.addAttribute("CONNECT_TYPE_CD", connectDevice(request)); // 로그인
																			// 디바이스
			model.addAttribute("SEQ", sSeq);// 저장
			request.setAttribute("SQL_ID", "member.insertUserInfo");
			sqlService.insert(request, response, model);

			Map<String, Object> sParam = new HashMap<String, Object>();
			sParam.put("MEMBER_EMAIL", paramMap.get("USER_EMAIL"));
			sParam.put("TO_SUBJECT", "회원가입이 정상적으로 처리 되었습니다.");
			sParam.put("TO_BODY", mailServiceImpl.registProcessHtml(paramMap));

			mailController.sendMail(sParam);
		}
		model.addAttribute("ajaxPage", model);

		connetLog(request, response, model);
		session.invalidate();//세션삭제	
		return mav;
	}
	
	@RequestMapping(value = "/idEmailSend.do")
	public ModelAndView idEmailSend(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			paramMap.put(ls_name, request.getParameter(ls_name));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		
		Map<String, Object> sParamMap = new HashMap<String, Object>();
		
		sParamMap.put("USER_EMAIL",boardUtil.encrypt((String) paramMap.get("USER_EMAIL")));
		
		List<HashMap<String, Object>> searchList = memberService.searchUserData(sParamMap);
		
		if(searchList.size()>0)
		{
			Map<String, Object> sParam = new HashMap<String, Object>();
			sParam.put("MEMBER_EMAIL", paramMap.get("USER_EMAIL"));
			sParam.put("USER_NM", boardUtil.decrypt(searchList.get(0).get("USER_NM")+""));
			sParam.put("USER_ID", searchList.get(0).get("USER_ID"));
			sParam.put("TO_SUBJECT", "회원님의 정보입니다.");
			sParam.put("TO_BODY", mailServiceImpl.idProcessHtml(sParam));
			
			mailController.sendMail(sParam);
			
			model.addAttribute("ajaxValue", gson.toJson(searchList));
		}
		else 
		{
			model.addAttribute("ajaxValue", "[]");
		}
		
		model.addAttribute("ajaxPage", model);
		
		connetLog(request, response, model);
		return mav;
	}
	
	@RequestMapping(value = "/userPasswordEmailSend.do")
	public ModelAndView userPasswordEmailSend(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			paramMap.put(ls_name, request.getParameter(ls_name));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);
		
		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		Gson gson = new Gson();
		mav.setViewName("common/ajaxPage");
		
		paramMap.put("USER_EMAIL",boardUtil.encrypt((String) paramMap.get("USER_EMAIL")));
		
		List<HashMap<String, Object>> searchList = memberService.searchUserData(paramMap);
		
		if(searchList.size()>0)
		{
			Map<String, Object> sParam = new HashMap<String, Object>();
			sParam.put("MEMBER_EMAIL", boardUtil.decrypt(searchList.get(0).get("USER_EMAIL")+""));
			
			String sRandStr = new RandomStringBuilder().putLimitedChar(RandomStringBuilder.ALPHABET).setLength(10).build();
			System.out.println("sRandStr ::>" + sRandStr);
			
			sParam.put("USER_NM", boardUtil.decrypt(searchList.get(0).get("USER_NM")+""));
			sParam.put("NEW_USER_PASSSWORD", sRandStr);
			sParam.put("TO_SUBJECT", "회원님의 비밀번호가 변경되었습니다.");
			
			sParam.put("TO_BODY", mailServiceImpl.passwordChangeProcessHtml(sParam));
			
			mailController.sendMail(sParam);
			
			model.addAttribute("ajaxValue", gson.toJson(searchList));
			
			model.addAttribute("USER_PASSWORD", boardUtil.encrypt(sRandStr));// seq
			model.addAttribute("SEQ", searchList.get(0).get("SEQ"));// seq
			request.setAttribute("SQL_ID", "member.updateUserInfo");
			sqlService.update(request, response, model);
			
		}
		else 
		{
			model.addAttribute("ajaxValue", "[]");
		}
		
		model.addAttribute("ajaxPage", model);
		
		connetLog(request, response, model);
		return mav;
	}

	@RequestMapping(value = "/searchUserData.do", method = RequestMethod.GET)
	public ModelAndView searchUserData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("model", model);

		// ACT_UPDATE_001 수정
		// ACT_INSERT_001 저장
		Map<String, Object> paramMap = new HashMap<String, Object>();

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			if("USER_ID".equals(ls_name))
			{
				paramMap.put(ls_name, request.getParameter(ls_name));
			}
			else 
			{
				paramMap.put(ls_name, boardUtil.encrypt(request.getParameter(ls_name)));
			}
		}
		
		Gson gson = new Gson();
		
		List<HashMap<String, Object>> searchList = memberService.searchUserData(paramMap);
		model.addAttribute("ajaxValue", gson.toJson(searchList));
		model.addAttribute("ajaxPage", model);
		mav.setViewName("common/ajaxPage");

		connetLog(request, response, model);

		return mav;
	}
	
	private void connetLog(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		HttpSession session = request.getSession();
		String sSessionId = session.getId();

		System.out.println("session id::>" + sSessionId);
		System.out.println("ip::>" + requestIp(request));
		System.out.println("sWebMobile::>" + connectDevice(request));

		model.addAttribute("USER_ADDR_IP", requestIp(request));
		model.addAttribute("USER_SESSION", sSessionId);
		model.addAttribute("CONNECT_TYPE_CD", connectDevice(request));

		request.setAttribute("SQL_ID", "common.insertLog");
		sqlService.update(request, response, model);
	}
	
	private String connectDevice(HttpServletRequest request)
	{
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
		return sWebMobile;
	}
	
	private String requestIp(HttpServletRequest request)
	{
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
		
		return sIp;
	}
}
