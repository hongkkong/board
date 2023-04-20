package com.hongkkongblog.crontab.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongkkongblog.crontab.dao.RunStockCoinLandCrawlerDao;
import com.hongkkongblog.web.util.BoardUtil;

@Component
public class RunStockCoinLandCrawler
{
	@Inject
	private RunStockCoinLandCrawlerDao runStockCoinLandCrawlerDao;
	
	@Inject
	private BoardUtil boardUtil;

	@Scheduled(cron = "0 10 0/1 * * *")
	public void main() throws IOException
	{
//		this.coinMain();
		this.stockMain();
		this.landMain();
	}
	
	public void coinMain() throws IOException
	{
		System.out.println("빗썸 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Map<String, Object> searchMap =new HashMap<String, Object>();
		searchMap.put("MENU_CD", "COIN");
		runStockCoinLandCrawlerDao.deleteContents(searchMap);

		String baseUrl = "https://www.bithumb.com/react/";

		// 1.document를 가져온다.
		Document doc = Jsoup.connect(baseUrl).get();

		// 2.목록을 가져온다.아래 테스트용
//		 Elements elements = doc.select(".sort_real_box>strong");
//		 for (int k = 0; k < elements.size(); k++)
//		 {
//		 Element elCoinName = elements.get(k);
//		
//		 System.out.println(k + " : " + elCoinName.text());
//		 System.out.println(k + " : " +
//		 elCoinName.getElementsByAttribute("href").attr("href"));
//		 }
		
		Elements eCoinName = doc.select(".sort_coin_box");
		Elements ePrice = doc.select(".sort_real_box>strong");
		Elements ePlus = doc.select(".sort_change");

		for (int k = 0; k < eCoinName.size(); k++)
		{
			Element elCoinName = eCoinName.get(k);
			Element elPrice = ePrice.get(k);
			Element elPlus = ePlus.get(k);
			String[] sCoinName = elCoinName.text().split("/");
			
			if(!"0 %".equals((elPlus.text())))
			{
				Map<String, Object> paramMap =new HashMap<String, Object>();

				paramMap.put("CONTENT_NM", sCoinName[0]);
				paramMap.put("CONTENT_PRICE_DESC", elPrice.text());
				paramMap.put("CONTENT_PLUS_MINUS_DESC", elPlus.text());
				paramMap.put("CONTENT_URL_DESC", baseUrl + "" + elCoinName.getElementsByAttribute("href").attr("href"));
				String sSeq =boardUtil.randomStr(30);
				paramMap.put("SEQ", sSeq);
				paramMap.put("MENU_CD", "COIN");
				paramMap.put("GUBUN_CD", "CRAWLING");
				paramMap.put("CREATE_ID", "SYSTEM");
				
				paramMap.put("CONTENT_ORDER_DESC", k+1);
				runStockCoinLandCrawlerDao.insertContents(paramMap);
			}
		}

		System.out.println("빗썸 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void stockMain() throws IOException
	{
		System.out.println("네이버증권 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Map<String, Object> searchMap =new HashMap<String, Object>();
		searchMap.put("MENU_CD", "STOCK");
		runStockCoinLandCrawlerDao.deleteContents(searchMap);

		String baseUrl = "https://finance.naver.com/sise";
		String retUrl = "https://finance.naver.com";

		// 1.document를 가져온다.
		Document doc = Jsoup.connect(baseUrl).get();

		// 2.목록을 가져온다.
		Elements eStockName = doc.select(".lst_pop>li>a");// 주식명
		Elements eStockPrice = doc.select(".lst_pop>li>span");// 가격
		Elements eStockPlus = doc.select(".lst_pop>li .blind");// 증감
		Elements eStockUrl = doc.select(".lst_pop>li");// url

		for (int k = 0; k < eStockName.size(); k++)
		{
			Element elStockName = eStockName.get(k);
			Element elStockPrice = eStockPrice.get(k);
			Element elStockPlus = eStockPlus.get(k);
			Element elStockUrl = eStockUrl.get(k);

			Map<String, Object> paramMap =new HashMap<String, Object>();

			paramMap.put("CONTENT_NM", elStockName.text());
			paramMap.put("CONTENT_PRICE_DESC", elStockPrice.text());
			paramMap.put("CONTENT_PLUS_MINUS_DESC", elStockPlus.text());
			paramMap.put("CONTENT_URL_DESC", retUrl + "" + elStockUrl.getElementsByAttribute("href").attr("href"));
			String sSeq =boardUtil.randomStr(30);
			paramMap.put("SEQ", sSeq);
			paramMap.put("MENU_CD", "STOCK");
			paramMap.put("GUBUN_CD", "CRAWLING");
			paramMap.put("CREATE_ID", "SYSTEM");
			paramMap.put("CONTENT_ORDER_DESC", k+1);
			
			runStockCoinLandCrawlerDao.insertContents(paramMap);
		}

		System.out.println("네이버증권 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	}

	public void landMain() throws IOException
	{
		String baseUrl = "https://land.naver.com/news/breaking.nhn";
		String retUrl = "https://land.naver.com";
		System.out.println("네이버증권 클롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Map<String, Object> searchMap =new HashMap<String, Object>();
		searchMap.put("MENU_CD", "LAND");
		runStockCoinLandCrawlerDao.deleteContents(searchMap);

		// 1.document를 가져온다.
		Document doc = Jsoup.connect(baseUrl).get();

		// 2.목록을 가져온다.
		Elements eNewsHead = doc.select(".headline_list>li>dl>dd");// 뉴스 헤드라인
		Elements eNewsUrl = doc.select(".headline_list>li>dl .photo");// 뉴스
		Elements eNewsPaper = doc.select(".headline_list>li>dl>dd .writing");// 신문사
		Elements eNewCreateDate = doc.select(".headline_list>li>dl>dd .date");// 작성일

		for (int k = 0; k < eNewsUrl.size(); k++)
		{
			Element elNewsHead = eNewsHead.get(k);
			Element elNewsPaper = eNewsPaper.get(k);
			Element elNewCreateDate = eNewCreateDate.get(k);
			Element elNewUrl = eNewsUrl.get(k);

			Map<String, Object> paramMap =new HashMap<String, Object>();

			paramMap.put("CONTENT_NM", elNewsHead.ownText());
			paramMap.put("CONTENT_PRICE_DESC", elNewsPaper.text());
			paramMap.put("CONTENT_PLUS_MINUS_DESC", elNewCreateDate.text());
			paramMap.put("CONTENT_URL_DESC", retUrl + "" + elNewUrl.getElementsByAttribute("href").attr("href"));
			String sSeq =boardUtil.randomStr(30);
			paramMap.put("SEQ", sSeq);
			paramMap.put("MENU_CD", "LAND");
			paramMap.put("GUBUN_CD", "CRAWLING");
			paramMap.put("CREATE_ID", "SYSTEM");
			paramMap.put("CONTENT_ORDER_DESC", k+1);
			
			runStockCoinLandCrawlerDao.insertContents(paramMap);
		}

		System.out.println("네이버증권 클롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
