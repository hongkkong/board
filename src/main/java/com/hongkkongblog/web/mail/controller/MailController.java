package com.hongkkongblog.web.mail.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hongkkongblog.web.common.service.SqlService;

@Controller
@RequestMapping("/mail")
public class MailController
{
	@Inject
	private SqlService sqlService;

	@Value("#{board['SHOP_EMAIL']}")
	private String gv_SHOP_EMAIL;

	@Value("#{board['FROM_EMAIL_USER_NAME']}")
	private String gv_FROM_EMAIL_USER_NAME;

	@Value("#{board['FROM_PASSWORD']}")
	private String gv_FROM_PASSWORD;

	@Value("#{board['HOST']}")
	private String gv_HOST;

	@Value("#{board['FROM_EMAIL']}")
	private String gv_FROM_EMAIL;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView Main(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException
	{

		Enumeration<?> ls_params = request.getParameterNames();
		while (ls_params.hasMoreElements())
		{
			String ls_name = (String) ls_params.nextElement();
			model.addAttribute(ls_name, request.getParameter(ls_name));
		}

		ModelAndView mav = new ModelAndView();

		return mav;
	}

	public String sendMail(Map<String, Object> ls_param)
	{
		System.out.println("send mail start");

		String ls_resultCode = "";

		String ls_TO_EMAIL = (String) ls_param.get("MEMBER_EMAIL");
		String ls_TO_SUBJECT = (String) ls_param.get("TO_SUBJECT");
		String ls_TO_BODY = (String) ls_param.get("TO_BODY");
		String ls_FROM_EMAIL = gv_FROM_EMAIL;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", gv_HOST);

		Authenticator auth = new MyAuthentication();
		Session ls_mailSession = Session.getInstance(props, auth);

		try
		{
			Message ls_message = new MimeMessage(ls_mailSession);
			ls_message.setFrom(new InternetAddress(ls_FROM_EMAIL));
			InternetAddress[] address =
			{ new InternetAddress(ls_TO_EMAIL) };
			ls_message.setRecipients(Message.RecipientType.TO, address);
			ls_message.setSentDate(new Date());
			ls_message.setSubject(ls_TO_SUBJECT);
			ls_message.setContent(ls_TO_BODY, "text/html;charset=UTF-8");

			Transport.send(ls_message);
			System.out.println("mail send Done");

			ls_resultCode = "SUCCESS";

		}
		catch (MessagingException mex)
		{
			System.out.println(mex.getMessage());
			ls_resultCode = "NONE_MAIL_SEND";
		}

		return ls_resultCode;
	}
	
	class MyAuthentication extends Authenticator
	{
		PasswordAuthentication passAuth;

		public MyAuthentication()
		{
			passAuth = new PasswordAuthentication(gv_FROM_EMAIL, gv_FROM_PASSWORD);
		}

		public PasswordAuthentication getPasswordAuthentication()
		{
			return passAuth;
		}
	}
}
