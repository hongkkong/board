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
public class RunDdengleCrawler
{
	@Autowired
	private RunCrawlerDao runCrawlerDao;

	@Autowired
	private BoardUtil boardUtil;
	
	@Scheduled(cron = "0 20 0/1 * * *")
	public void ddengleMain() throws IOException
	{
		System.out.println("땡글닷컴 블록체인뉴스 크롤러 start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String mainUrl = "https://www.ddengle.com/blockchainnews";
		
		// 1.document를 가져온다.
		Document docMain = Jsoup.connect(mainUrl).get();

		// 2.목록을 가져온다.아래 테스트용
		Elements eDdBit = docMain.select("tr");

		for (int k = 0; k < eDdBit.size(); k++)
		{
			if(k>1)
			{	
				Element eDdBitDesc = eDdBit.get(k);
				
				Elements eTitleDesc = eDdBitDesc.select(".speech");
				String eTitleLinkDesc = (eDdBitDesc.getElementsByAttribute("href").attr("href"));
				
//				System.out.println(k + " : " + eTitleDesc.text());
//				System.out.println(k + " : " + eTitleLinkDesc);
				
				String detailUrl = eTitleLinkDesc;
	
				// 1.document를 가져온다.
				Document docDetail =Jsoup.connect(detailUrl).get();
				
				Elements eDdBitDetail = docDetail.select("#zema9_body");
				
				String sDetail ="";
				String sImgSrc ="";
				for(int i=0;i<eDdBitDetail.size();i++)
				{
					Element eDdBiteDetailDesc =eDdBitDetail.get(i);
					
					sDetail=new HtmlToPlainText().getPlainText(eDdBiteDetailDesc);
					sImgSrc=eDdBiteDetailDesc.getElementsByTag("img").attr("src");
					
	//					System.out.println(k + " : " + eKoreaStockDetailDesc.text());
	//					System.out.println(k + " : " + eKoreaStockDetailDesc.getElementsByTag("img"));
				}
				
				String sTitle =eTitleDesc.text();
				String sFromSiteSeq =eTitleLinkDesc;
				
				Map<String,Object> param=new HashMap<String, Object>();
				
//				System.out.println("sTitle::>"+sTitle);
//				System.out.println("sSeq::>"+sFromSiteSeq);
//				System.out.println("sDetail::>"+sDetail);
//				System.out.println("sImgSrc::>"+sImgSrc);
				
				String regex = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
				String sSeq =boardUtil.randomStr(30);
				param.put("SEQ", sSeq);
				param.put("TITLE_DESC", boardUtil.replaceText(sTitle.replaceAll(regex, "")));
				param.put("CONTENT_DESC", boardUtil.replaceText(sDetail.replaceAll(regex, "")));
				param.put("CONTENT_USER_NM", "멋진홍꽁");
				param.put("FROM_SITE_DESC", boardUtil.replaceText(mainUrl));
				param.put("GUBUN_CD", "CRAWLING");
				param.put("MENU_CD", "COIN");
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

		System.out.println("땡글닷컴 블록체인뉴스 크롤러 end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
