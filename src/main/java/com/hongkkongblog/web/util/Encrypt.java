package com.hongkkongblog.web.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt
{
	// 암호화
	public String encrypt(String word) throws Exception
	{
		if(word ==null || word=="") return "";
		
		byte[] message =word.getBytes("UTF-8");
		
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String rtn_word = base64Encoder.encode(message);
		return rtn_word;
	}

	// 복호화
	public String decrypt(String word) throws Exception
	{
		if(word ==null || word=="") return "";
		
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] rtn_word_byte = base64Decoder.decodeBuffer(word);
		return new String(rtn_word_byte, "UTF-8");
	}
}