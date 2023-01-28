package com.hongkkongblog.web.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongkkongblog.web.board.service.BoardService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/crawler")
public class BoardCrawler
{
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/bithumbMain", method = RequestMethod.GET)
	public JSONArray bithumbMain(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		System.out.println("빗썸 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("MENU_CD", "COIN");
		List<Map<String, Object>> contentsList =boardService.crawlerList(paramMap);
		
		for (int k = 0; k < contentsList.size(); k++)
		{
			Map<String, Object> obj =contentsList.get(k);
			
			jsonObject.put("COIN_NM", obj.get("CONTENT_NM"));
			jsonObject.put("COIN_PRICE", obj.get("CONTENT_PRICE_DESC"));
			jsonObject.put("COIN_PLUS", obj.get("CONTENT_PLUS_MINUS_DESC"));
			jsonObject.put("COIN_HREF", obj.get("CONTENT_URL_DESC"));
			jsonArray.add(jsonObject);
		}
			
		System.out.println("빗썸 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return jsonArray;
	}

	@RequestMapping(value = "/naverStockMain", method = RequestMethod.GET)
	public JSONArray naverStockMain(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		System.out.println("네이버증권 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("MENU_CD", "STOCK");
		List<Map<String, Object>> contentsList =boardService.crawlerList(paramMap);

		for (int k = 0; k < contentsList.size(); k++)
		{
			Map<String, Object> obj =contentsList.get(k);
			
			jsonObject.put("STOCK_NM", obj.get("CONTENT_NM"));
			jsonObject.put("STOCK_PRICE", obj.get("CONTENT_PRICE_DESC"));
			jsonObject.put("STOCK_PLUS", obj.get("CONTENT_PLUS_MINUS_DESC"));
			jsonObject.put("STOCK_HREF", obj.get("CONTENT_URL_DESC"));
			jsonArray.add(jsonObject);
		}

		//System.out.println(jsonArray);

		System.out.println("네이버증권 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return jsonArray;
	}

	@RequestMapping(value = "/naverLandMain", method = RequestMethod.GET)
	public JSONArray naverLandMain(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{
		System.out.println("네이버증권 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("MENU_CD", "LAND");
		List<Map<String, Object>> contentsList =boardService.crawlerList(paramMap);

		for (int k = 0; k < contentsList.size(); k++)
		{
			Map<String, Object> obj =contentsList.get(k);
			
			jsonObject.put("NEWS_HAED", obj.get("CONTENT_NM"));
			jsonObject.put("NEWS_PAPER", obj.get("CONTENT_PRICE_DESC"));
			jsonObject.put("NEWS_CREATE_DATE", obj.get("CONTENT_PLUS_MINUS_DESC"));
			jsonObject.put("NEWS_HREF", obj.get("CONTENT_URL_DESC"));
			jsonArray.add(jsonObject);
		}

//		System.out.println(jsonArray);

		System.out.println("네이버증권 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return jsonArray;
	}

}
