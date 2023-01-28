package com.hongkkongblog.web.admin.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hongkkongblog.web.admin.dao.AdminFileDao;
import com.hongkkongblog.web.util.FileUtils;

/*
 * menu service
 */
@Service
public class AdminFileService
{
	@Autowired
	private AdminFileDao fileDao;

	@Autowired
	private FileUtils fileUtils;

	private String filePath = "C://DEV//file//";

	public void fileUpLoad(HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException
	{
		
		String ls_PARENT_IDX = request.getParameter("PARENT_IDX");
		String ls_USER_ID = request.getParameter("USER_ID");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null;
	    
	    String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        String uuid = null;
        
        String curTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
        Calendar cal = Calendar.getInstance();  // ���� ��¥�ð��� ���� ��ü ���

        String yStr = ""+cal.get(Calendar.YEAR);  // ���س⵵ ���
        String mStr = ""+(cal.get(Calendar.MONTH) + 1);  // ���� �� ��� (���� + 1 �������)
        System.out.println("yStr -------------"+yStr);
        System.out.println("mStr -------------"+mStr);
        System.out.println("cal -------------"+curTime);
        
        if((cal.get(Calendar.MONTH)+1) < 10 ) {
           mStr = "0"+mStr;  // ������� 1�ڸ� �����ΰ�� �տ� 0���ٿ��ش�.
        }

        String dirPath = filePath+yStr+"//"+mStr+"//";
        int count = 0;
        
        ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
 
        File file = new File(dirPath);
        if(file.exists() == false){
            file.mkdirs();
        }

	    while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	        
	
	        if(multipartFile.isEmpty() == false){
	        	uuid = fileUtils.uuid();
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = uuid + originalFileExtension;
                 
                file = new File(dirPath + storedFileName);
                multipartFile.transferTo(file);
	        	
	        	System.out.println("------------- file start -------------");
	        	System.out.println("name : "+multipartFile.getName());
	        	System.out.println("filename : "+multipartFile.getOriginalFilename());
	        	System.out.println("size : "+multipartFile.getSize());
	        	System.out.print("-------------- file end --------------\n");
	        	
	            listMap = new HashMap<String,Object>();
	            
	            
	            listMap.put("IDX", curTime+""+count++);
	            listMap.put("PARENT_IDX", ls_PARENT_IDX);
	            listMap.put("ORIGINAL_FILE_NAME", originalFileName);
	            listMap.put("STORED_FILE_NAME", storedFileName);
	            listMap.put("FILE_SIZE", multipartFile.getSize());
	            listMap.put("FILE_PATH", dirPath.replace("//", "/"));
	            listMap.put("CREA_ID", ls_USER_ID);
	            listMap.put("TYPE", "img");
	            
	            list.add(listMap);

	        }
	    }
	    
	    System.out.println("list.size()-----> : "+list.size());
	    Map<String,Object> tempMap = null;
	    for(int i = 0 ; i < list.size() ; i++)
	    {
	    	tempMap = list.get(i);
	    	{
	    		System.out.print("2222222222222222222222222222222222222222222222\n");
	    		fileDao.fileInsert(tempMap);
	    	}
	    }
	}
	
	public void fileWarUpLoad(HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException
	{
		
		String ls_PARENT_IDX = request.getParameter("PARENT_IDX");
		String ls_USER_PASSWORD = request.getParameter("USER_PASSWORD");
		String ls_USER_ID = request.getParameter("USER_ID");
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null;
	    
	    String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        String uuid = null;
        
        
        String dirPath = "C://DEV//04.server//webapps//";
        int count = 0;
        
        ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
 
        File file = new File(dirPath);
        if(file.exists() == false){
            file.mkdirs();
        }

	    while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	        
	
	        if(multipartFile.isEmpty() == false){
	        	uuid = fileUtils.uuid();
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = "ROOT.war";
                 
                file = new File(dirPath + storedFileName);
                multipartFile.transferTo(file);
	        	
	        	System.out.println("------------- file start -------------");
	        	System.out.println("name : "+multipartFile.getName());
	        	System.out.println("filename : "+multipartFile.getOriginalFilename());
	        	System.out.println("size : "+multipartFile.getSize());
	        	System.out.print("-------------- file end --------------\n");
	        	
	            listMap = new HashMap<String,Object>();
	            
	            String curTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	            listMap.put("IDX", curTime+""+count++);
	            listMap.put("PARENT_IDX", ls_PARENT_IDX);
	            listMap.put("ORIGINAL_FILE_NAME", originalFileName);
	            listMap.put("STORED_FILE_NAME", storedFileName);
	            listMap.put("FILE_SIZE", multipartFile.getSize());
	            listMap.put("FILE_PATH", dirPath.replace("//", "/"));
	            listMap.put("CREA_ID", ls_USER_ID);
	            listMap.put("TYPE", "war");

	            list.add(listMap);
	        }
	    }
	    
	    System.out.println("list.size()-----> : "+list.size());
	    Map<String,Object> tempMap = null;
	    for(int i = 0 ; i < list.size() ; i++)
	    {
	    	tempMap = list.get(i);
	    	{
	    		System.out.print("2222222222222222222222222222222222222222222222\n");
	    		fileDao.fileInsert(tempMap);
	    	}
	    }
	}
	
}
