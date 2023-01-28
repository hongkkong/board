package com.hongkkongblog.crontab.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hongkkongblog.crontab.dao.RunDao;

 
@Component
public class Run
{
	@Autowired
	private RunDao runDao;

	@Scheduled(cron = "0 0/30 * * * *")
	public void dbConnect()
	{
		try
		{
			System.out.println("<::::::::::::::: connect jdbc :::::::::::::::::>");

			HashMap<String, String> paramMap = new HashMap<String, String>();
			runDao.connect(paramMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
