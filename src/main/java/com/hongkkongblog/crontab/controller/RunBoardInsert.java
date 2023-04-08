package com.hongkkongblog.crontab.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongkkongblog.crontab.dao.RunBoardInsertDao;

@Component
public class RunBoardInsert
{
	@Autowired
	private RunBoardInsertDao runBoardInsertDao;

	@Scheduled(cron = "0 0/30 * * * *")
	public void antokKoreaStockMain() throws IOException
	{
		System.out.println("국내 증시 크롤러 to board start>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		Map<String, Object> param = new HashMap<String, Object>();

		List<Map<String, Object>> mainList = runBoardInsertDao.selectCrawlerInfo(param);

		for (int k = 0; k < mainList.size(); k++)
		{
			Map<String, Object> paramMap = mainList.get(k);
			/*
			 * 게시판 #{SEQ} ,#{BOARD_TYPE_CD} ,#{MENU_CD} ,#{TITLE}
			 * ,#{CONTENT_DESC} ,(CASE WHEN #{FIXED_YN}='' OR #{FIXED_YN} IS
			 * NULL THEN 'N' ELSE 'Y' END) ,#{LIKE_YN} ,#{USER_NM} ,#{CREATE_ID}
			 * ,DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%s') ,#{UPDATE_ID}
			 * ,DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%s') ,#{COMMENT_YN} ,(CASE
			 * WHEN #{HIDDEN_YN}='' OR #{HIDDEN_YN} IS NULL THEN 'N' ELSE 'Y'
			 * END) ,DATE_FORMAT(NOW(),'%Y%m%d'), CRAWLER_IMG_DEC
			 * 
			 * 크롤링 SEQ ,CREATE_DATE
			 * ,TITLE_DESC ,CONTENT_DESC ,CONTENT_USER_NM
			 * ,CONTENT_CREATE_DATETIME ,FROM_SITE_DESC ,GUBUN_CD ,MENU_CD
			 * ,CREATE_DATETIME ,CREATE_ID ,FROM_SITE_SEQ ,CONTENT_IMG_DESC
			 */

			paramMap.put("BOARD_TYPE_CD", "SITE_CRAWL");
			paramMap.put("TITLE", paramMap.get("TITLE_DESC"));
			paramMap.put("CONTENT_DESC", paramMap.get("CONTENT_DESC"));
			paramMap.put("USER_NM", paramMap.get("CONTENT_USER_NM"));
			paramMap.put("CREATE_ID", "hongkkong");
			paramMap.put("UPDATE_ID", "hongkkong");
			paramMap.put("COMMENT_YN", "Y");
			paramMap.put("CRAWLER_IMG_DEC", paramMap.get("CONTENT_IMG_DESC"));

			runBoardInsertDao.insertBoardInfo(paramMap);
		}

		System.out.println("국내 증시 크롤러 to board end>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	}
}
