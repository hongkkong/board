package com.hongkkongblog.web.util;
 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
@Component("FileUtils")
public class FileUtils {
    private static final String filePath = "C:\\DEV\\file\\";
     
	public String uuid()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}