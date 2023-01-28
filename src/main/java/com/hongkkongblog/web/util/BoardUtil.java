package com.hongkkongblog.web.util;

import java.io.IOException;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BoardUtil extends Encrypt
{
	public void close(SqlSession sqlSession)
	{
		sqlSession.commit();
		sqlSession.close();
	}

	public String replaceText(String word)
	{
		String ls_word = "";
		ls_word = word.toLowerCase().replace("<script", "<!--script");
		ls_word = word.toLowerCase().replace("</script", "<!--script");
		ls_word = word.toLowerCase().replace("<td", "<!--td");
		ls_word = word.toLowerCase().replace("</td", "<!--td");
		ls_word = word.toLowerCase().replace("<tr", "<!--tr");
		ls_word = word.toLowerCase().replace("</tr", "<!--tr");
		ls_word = word.toLowerCase().replace("<table", "<!--table");
		ls_word = word.toLowerCase().replace("</table", "<!--table");
		ls_word = word.toLowerCase().replace("'", "＇");
		ls_word = word.toLowerCase().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		ls_word = word.toLowerCase().replace("\n", "<br/>");

		return ls_word;
	}

	public String nvl(String word)
	{
		String ls_word = "";
		if (word == null)
		{
			ls_word = "";
		}
		else
		{
			ls_word = word;
		}
		return ls_word;
	}

	public String encoder(String word)
	{
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String rtn_word = base64Encoder.encode(word.getBytes());
		return rtn_word;
	}

	public String decoder(String word) throws IOException
	{
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] rtn_word_byte = base64Decoder.decodeBuffer(word);
		return new String(rtn_word_byte);
	}

	public String idEncoder(String sMemberId)
	{
		return encoder(sMemberId);
	}

	public Object idDecoder(String sMemberId) throws IOException
	{
		return decoder(sMemberId);
	}

	public String numberFormat(String num)
	{
		String original = num;
		String convert = "";
		int count = 1;
		for (int k = original.length() - 1; k > -1; k--)
		{
			if ((count % 3) == 0 && k < original.length() - 1 && k > 0)
			{
				convert = "," + original.charAt(k) + convert;
			}
			else
			{
				convert = original.charAt(k) + convert;
			}
			count++;
		}
		return convert;
	}

	public String randomStr(int length)
	{
		Random random = new Random();

		// System.out.println(randomWord("lower", length));
		// System.out.println(randomWord("upper", length));
		// System.out.println(randomWord("number", length));

		StringBuffer newWord = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			int mixed = random.nextInt(3);
			switch (mixed)
			{
			case 0:
				newWord.append(randomWord("lower", 1));
				break;
			case 1:
				newWord.append(randomWord("upper", 1));
				break;
			case 2:
				newWord.append(randomWord("number", 1));
				break;
			default:
				break;
			}
		}
		// System.out.println("newWord = " + newWord);

		return newWord.toString();
	}

	static String randomWord(String selectCase, int length)
	{
		if (selectCase == "lower")
		{
			String lowerRandom = "";
			for (int i = 0; i < length; i++)
			{
				char lowerCh = (char) ((int) (Math.random() * 25) + 97);
				lowerRandom += lowerCh;
			}
			return lowerRandom;
		}
		if (selectCase == "upper")
		{
			String upperRandom = "";
			for (int i = 0; i < length; i++)
			{
				char upperCh = (char) ((int) (Math.random() * 25) + 65);
				upperRandom += upperCh;
			}
			return upperRandom;
		}
		if (selectCase == "number")
		{
			String numRandom = "";
			for (int i = 0; i < length; i++)
			{
				char ch = (char) ((int) (Math.random() * 10) + 48);
				numRandom += ch;
			}
			return numRandom;
		}
		return null;
	}
}
