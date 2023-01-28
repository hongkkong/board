package com.hongkkongblog.web.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongkkongblog.web.common.service.FileService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController
{
	@Inject
	private FileService fileService;
	
	@Value("#{board['BASE_FILE_PATH']}")
	private String gv_BASE_FILE_PATH;
	
	@Value("#{board['BASE_FILE_TEMP_PATH']}")
	private String gv_BASE_FILE_TEMP_PATH;

	@Value("#{board['SERVER_ID']}")
	private String gv_SERVER_ID;
	
	@Value("#{board['SERVER_URL']}")
	private String gv_SERVER_URL;
	
	public String upload(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{

		System.out.println("FileUpload start");
		System.out.println("gv_BASE_FILE_PATH ::> " + gv_BASE_FILE_PATH);
		System.out.println("gv_BASE_FILE_TEMP_PATH ::> " + gv_BASE_FILE_TEMP_PATH);

		HashMap<String, String> ls_param = new HashMap<String, String>();

		/**
		 * 운영 반영시 반드시 바꿀껏
		 */
		String ls_fileName = "";

		int gv_fileSize = 10485760;// 10메가

		MultipartRequest multiPart = new MultipartRequest(request, gv_BASE_FILE_TEMP_PATH, gv_fileSize, "UTF-8", (FileRenamePolicy) new DefaultFileRenamePolicy());

		String ls_orgFileName = "";
		Enumeration files = multiPart.getFileNames();
		String ls_saveFilePath = "";
		while (files.hasMoreElements())
		{
			ls_fileName = multiPart.getFilesystemName((String) files.nextElement());
		}

		if (!("".equals(ls_fileName)) && ls_fileName != null)
		{
			ls_orgFileName = ls_fileName;
			int ls_position = ls_fileName.lastIndexOf(".");
			String ls_fileExt = ls_fileName.substring(ls_position);
			String ls_fileExtNotComma = ls_fileName.substring(ls_fileName.lastIndexOf(46) + 1);
			String ls_reNameFileNm = "BOARD_" + System.currentTimeMillis() + ls_fileExt;

			File file = new File(String.valueOf(gv_BASE_FILE_TEMP_PATH) + "/" + ls_fileName);
			if (file.exists() && file.renameTo(new File(String.valueOf(gv_BASE_FILE_TEMP_PATH) + "/" + ls_reNameFileNm)))
			{
				ls_fileName = ls_reNameFileNm;
				file = new File(String.valueOf(gv_BASE_FILE_TEMP_PATH) + "/" + ls_fileName);
			}

			if (!(ls_fileExtNotComma.equalsIgnoreCase("JPG") || ls_fileExtNotComma.equalsIgnoreCase("GIF") || ls_fileExtNotComma.equalsIgnoreCase("PNG") || ls_fileExtNotComma.equalsIgnoreCase("JPEG")))
			{
				file.delete();
				ls_param.put("FILE_UPLOAD_MSG", "NOT_UPLOAD");
			}

			ls_saveFilePath = modifyFilePath(gv_BASE_FILE_PATH, gv_BASE_FILE_TEMP_PATH, "BOARD", ls_fileName);
			
			ls_param.put("FILE_NM", ls_fileName);
			ls_param.put("ORG_FILE_NM", ls_orgFileName);
			ls_param.put("FILE_TYPE_CD", ls_fileExtNotComma);
			ls_param.put("FILE_PATH", ls_saveFilePath);
			
			HttpSession session = request.getSession();
			
			ls_param.put("USER_ID", (String) (session.getAttribute("USER_ID")==null ? session.getId():session.getAttribute("USER_ID")));

			int ls_fileSqlNum = fileService.insert(ls_param, model);
			if (ls_fileSqlNum > 0)
			{
				model.addAttribute("SAVE_FILE_SUCCESS_YN", "Y");
			}
			else
			{
				model.addAttribute("SAVE_FILE_SUCCESS_YN", "N");
			}
		}

		System.out.println("fileUpload end");
		
		return ls_fileName;
	}
	
	public String modifyFilePath(String gv_BASE_FILE_PATH, String gv_BASE_FILE_TEMP_PATH, String ls_menuCd, String ls_fileName)
	{
		String rtn_fileDir = "";
		Session jsSession = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		Date ls_todate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String ls_now = format.format(ls_todate);
		String ls_yyyy = ls_now.substring(0, 4);
		String ls_mm = ls_now.substring(4, 6);
		String ls_dd = ls_now.substring(6, 8);

		try
		{
			JSch jsch = new JSch();
			jsSession = jsch.getSession(gv_SERVER_ID, gv_SERVER_URL, Integer.parseInt("22"));
			jsSession.setPassword("h73034396");
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			jsSession.setConfig(config);
			jsSession.connect();
			channel = jsSession.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			System.out.println("directory create start");
			try
			{
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd);
			}
			catch (Exception e)
			{
				channelSftp.mkdir(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd);
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd);
			}
			try
			{
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy);
			}
			catch (Exception e)
			{
				channelSftp.mkdir(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy);
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy);
			}
			try
			{
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm);
			}
			catch (Exception e)
			{
				channelSftp.mkdir(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm);
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm);
			}
			try
			{
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm + "/" + ls_dd);
			}
			catch (Exception e)
			{
				channelSftp.mkdir(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm + "/" + ls_dd);
				channelSftp.cd(String.valueOf(gv_BASE_FILE_PATH) + "/" + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm + "/" + ls_dd);
			}

			System.out.println("directory create end");

			File file = new File(String.valueOf(gv_BASE_FILE_TEMP_PATH) + "/" + ls_fileName);// 파일 카피
			String ls_FILE_NAME = file.getName();

			if (ls_fileName != null && ls_fileName.length() > 0)
			{
				ls_FILE_NAME = ls_fileName;
			}
			channelSftp.put((InputStream) new FileInputStream(file), ls_FILE_NAME);
			channel.disconnect();
			jsSession.disconnect();
			rtn_fileDir = gv_BASE_FILE_PATH + ls_menuCd + "/" + ls_yyyy + "/" + ls_mm + "/" + ls_dd + "/" + ls_FILE_NAME;

			System.out.println("rtn_fileDir ::> " + rtn_fileDir);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return rtn_fileDir;
	}
}
