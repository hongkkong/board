package com.hongkkongblog.crontab.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongkkongblog.crontab.dao.RunCrawlerDao;
import com.hongkkongblog.web.util.BoardUtil;

@Component
public class RunPaxnetCrawler
{
	@Autowired
	private RunCrawlerDao runCrawlerDao;

	@Autowired
	private BoardUtil boardUtil;
	
	@Scheduled(cron = "0 10 0/1 * * *")
	public void main() throws IOException
	{
		this.paxnetStockNewsMain();
		this.paxnetStockJjalMain();
		this.paxnetStockJjalBestMain();
	}
	
	private void paxnetStockNewsMain() throws IOException
	{
		System.out.println("팍스넷 주요뉴스 크롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String mainUrl = "http://www.paxnet.co.kr/news/main";
		
		// 1.document를 가져온다.
		Document docMain = Jsoup.connect(mainUrl).get();

		// 2.목록을 가져온다.아래 테스트용
		Elements ePaxStock = docMain.select(".text");
		
//		System.out.println("ePaxStock::>"+ePaxStock);

		for (int k = 0; k < ePaxStock.size(); k++)
		{
			Element ePaxDesc = ePaxStock.get(k);
			
			Elements eTitleDesc = ePaxDesc.select("dt");
			String eTitleLinkDesc = (ePaxDesc.getElementsByAttribute("href").attr("href"));
			
//			System.out.println(k + " : " + eTitleDesc.text());
//			System.out.println(k + " : " + eTitleLinkDesc);
			
			String detailUrl = "http://www.paxnet.co.kr"+eTitleLinkDesc;

			// 1.document를 가져온다.
			Document docDetail =Jsoup.connect(detailUrl).get();
			
			Elements ePaxDetail = docDetail.select(".report-view-cont");
			
			String sDetail ="";
			String sImgSrc ="";
			for(int i=0;i<ePaxDetail.size();i++)
			{
				Element eKoreaStockDetailDesc =ePaxDetail.get(i);
				
				sDetail=new HtmlToPlainText().getPlainText(eKoreaStockDetailDesc);
				sImgSrc=eKoreaStockDetailDesc.getElementsByTag("img").attr("src");
				
//					System.out.println(k + " : " + eKoreaStockDetailDesc.text());
//					System.out.println(k + " : " + eKoreaStockDetailDesc.getElementsByTag("img"));
			}
			
			String sTitle =eTitleDesc.text();
			String sFromSiteSeq =eTitleLinkDesc;
			
			Map<String,Object> param=new HashMap<String, Object>();
			
//			System.out.println("sTitle::>"+sTitle);
//			System.out.println("sSeq::>"+sFromSiteSeq);
//			System.out.println("sDetail::>"+sDetail);
//			System.out.println("sImgSrc::>"+sImgSrc);
			
			String regex = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
			String sSeq =boardUtil.randomStr(30);
			param.put("SEQ", sSeq);
			param.put("TITLE_DESC", boardUtil.replaceText(sTitle.replaceAll(regex, "")));
			param.put("CONTENT_DESC", boardUtil.replaceText(sDetail.replaceAll(regex, "")));
			param.put("CONTENT_USER_NM", "멋진홍꽁");
			param.put("FROM_SITE_DESC", boardUtil.replaceText(mainUrl));
			param.put("GUBUN_CD", "CRAWLING");
			param.put("MENU_CD", "STOCK");
			param.put("CREATE_ID", "SYSTEM");
			param.put("FROM_SITE_SEQ", sFromSiteSeq);
			param.put("CONTENT_IMG_DESC", sImgSrc);
			
			List<Map<String,Object>> mainList =runCrawlerDao.selectInfo(param);
			
			if(mainList.size()==0 || mainList==null)
			{
				runCrawlerDao.insertInfo(param);
			}
		}
		

		System.out.println("팍스넷 주요뉴스 크롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	private void paxnetStockJjalMain() throws IOException
	{
		System.out.println("팍스넷 깔깔유머 크롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String mainUrl = "http://www.paxnet.co.kr/tbbs/list?tbbsType=L&id=N00811";
		
		// 1.document를 가져온다.
		Document docMain = Jsoup.connect(mainUrl).get();

		// 2.목록을 가져온다.아래 테스트용
		Elements ePaxStock = docMain.select(".title");
		
//		System.out.println("ePaxStock::>"+ePaxStock);
		

		for (int k = 0; k < ePaxStock.size(); k++)
		{
			if(k>1)
			{
				Element ePaxDesc = ePaxStock.get(k);
				
				String eTitleDesc = ePaxDesc.select(".best-title").text();
				Elements eUrlPaxDesc = ePaxDesc.select("p");
					     eUrlPaxDesc.attr("id");
			     String eUrlPaxDetail =eUrlPaxDesc.attr("id").replace("tit_", "");
				
	//			System.out.println(k + " : " + eTitleDesc);
	//			System.out.println(k + " : " + eUrlPaxDetail);
				
				String detailUrl = "http://www.paxnet.co.kr/tbbs/view?id=N00811&seq="+eUrlPaxDetail;
	
				// 1.document를 가져온다.
				Document docDetail =Jsoup.connect(detailUrl).get();
				
				Elements ePaxDetail = docDetail.select(".board-view-cont");
				
				String sDetail =eTitleDesc;
				String sImgSrc ="";
				for(int i=0;i<ePaxDetail.size();i++)
				{
					Element eJjalDetailDesc =ePaxDetail.get(i);
					
					sImgSrc=eJjalDetailDesc.getElementsByTag("img").attr("src");
					
//					System.out.println(k + " : " + sImgSrc);
				}
				
				String sTitle =eTitleDesc;
				String sFromSiteSeq =detailUrl;
				
				Map<String,Object> param=new HashMap<String, Object>();
				
//				System.out.println("sTitle::>"+sTitle);
//				System.out.println("sSeq::>"+sFromSiteSeq);
//				System.out.println("sDetail::>"+sDetail);
//				System.out.println("sImgSrc::>"+sImgSrc);
				
				String regex = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
				String sSeq =boardUtil.randomStr(30);
				param.put("SEQ", sSeq);
				param.put("TITLE_DESC", boardUtil.replaceText(sTitle.replaceAll(regex, "")));
				param.put("CONTENT_DESC", "");
				param.put("CONTENT_USER_NM", "멋진홍꽁");
				param.put("FROM_SITE_DESC", boardUtil.replaceText(mainUrl));
				param.put("GUBUN_CD", "CRAWLING");
				param.put("MENU_CD", "HUMOR");
				param.put("CREATE_ID", "SYSTEM");
				param.put("FROM_SITE_SEQ", sFromSiteSeq);
				param.put("CONTENT_IMG_DESC", sImgSrc);
				
				List<Map<String,Object>> mainList =runCrawlerDao.selectInfo(param);
				
				if(mainList.size()==0 || mainList==null)
				{
					runCrawlerDao.insertInfo(param);
				}
			}
		}	
		System.out.println("팍스넷 깔깔유머 크롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	private void paxnetStockJjalBestMain() throws IOException
	{
		System.out.println("팍스넷 깔깔유머 베스트 크롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String mainUrl = "http://www.paxnet.co.kr/tbbs/list?id=N00811&page=1&sort=popularOrdr";
		
		// 1.document를 가져온다.
		Document docMain = Jsoup.connect(mainUrl).get();

		// 2.목록을 가져온다.아래 테스트용
		Elements ePaxStock = docMain.select(".title");
		
//		System.out.println("ePaxStock::>"+ePaxStock);
		

		for (int k = 0; k < ePaxStock.size(); k++)
		{
			if(k>1)
			{
				Element ePaxDesc = ePaxStock.get(k);
				
				String eTitleDesc = ePaxDesc.select(".best-title").text();
				Elements eUrlPaxDesc = ePaxDesc.select("p");
					     eUrlPaxDesc.attr("id");
			     String eUrlPaxDetail =eUrlPaxDesc.attr("id").replace("tit_", "");
				
	//			System.out.println(k + " : " + eTitleDesc);
	//			System.out.println(k + " : " + eUrlPaxDetail);
				
				String detailUrl = "http://www.paxnet.co.kr/tbbs/view?id=N00811&sort=popularOrdr&seq="+eUrlPaxDetail;
	
				// 1.document를 가져온다.
				Document docDetail =Jsoup.connect(detailUrl).get();
				
				Elements ePaxDetail = docDetail.select(".board-view-cont");
				
				String sDetail =eTitleDesc;
				String sImgSrc ="";
				for(int i=0;i<ePaxDetail.size();i++)
				{
					Element eJjalDetailDesc =ePaxDetail.get(i);
					
					sImgSrc=eJjalDetailDesc.getElementsByTag("img").attr("src");
					
//					System.out.println(k + " : " + sImgSrc);
				}
				
				String sTitle =eTitleDesc;
				String sFromSiteSeq =detailUrl;
				
				Map<String,Object> param=new HashMap<String, Object>();
				
//				System.out.println("sTitle::>"+sTitle);
//				System.out.println("sSeq::>"+sFromSiteSeq);
//				System.out.println("sDetail::>"+sDetail);
//				System.out.println("sImgSrc::>"+sImgSrc);
				
				String regex = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
				String sSeq =boardUtil.randomStr(30);
				param.put("SEQ", sSeq);
				param.put("TITLE_DESC", boardUtil.replaceText(sTitle.replaceAll(regex, "")));
				param.put("CONTENT_DESC", "");
				param.put("CONTENT_USER_NM", "멋진홍꽁");
				param.put("FROM_SITE_DESC", boardUtil.replaceText(mainUrl));
				param.put("GUBUN_CD", "CRAWLING");
				param.put("MENU_CD", "BEST");
				param.put("CREATE_ID", "SYSTEM");
				param.put("FROM_SITE_SEQ", sFromSiteSeq);
				param.put("CONTENT_IMG_DESC", sImgSrc);
				
				List<Map<String,Object>> mainList =runCrawlerDao.selectInfo(param);
				
				if(mainList.size()==0 || mainList==null)
				{
					runCrawlerDao.insertInfo(param);
				}
			}
		}	
		System.out.println("팍스넷 깔깔유머 크롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
