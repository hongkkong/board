package com.hongkkongblog.web.mail.Impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongkkongblog.web.mail.dao.MailDao;
import com.hongkkongblog.web.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService
{
	@Inject
	private MailDao mailDao;

	@Override
	public List<Map<String, Object>> selectTest(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		// TODO Auto-generated method stub
		return mailDao.selectTest(request, response, model);
	}
	
	public String registProcessHtml(Map<String, Object> paramMap)
	{
		String ls_html = "";

		ls_html += "<html lang='ko'>";
		ls_html += "<head>";
		ls_html += "<m-ta name='format-detection' content='date=no'> ";
		ls_html += "<m-ta name='format-detection' content='email=no'>";
		ls_html += "</head>";
		ls_html += "<body style='margin: 0; padding: 0;' bgcolor='#FFFFFF'>";
		ls_html += "	<table width='100%' height='100%' style='min-width: 348px;' border='0' cellspacing='0' cellpadding='0'>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "		<tr align='center'>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "			<td><table border='0' cellspacing='0' cellpadding='0' style='max-width: 600px;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#D94235' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #E0E0E0; border-bottom: 0; border-top-left-radius: 3px; border-top-right-radius: 3px;'>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px' height='60px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 24px; color: #FFFFFF; line-height: 1.25;'>회원정보가 등록되었습니다.</td>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FFFFFF' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-top: 0;'>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>";
		ls_html += "									  본인의 계정이 아니라면 이메일 수신을 중단하세요.";
		ls_html += "									</td>";
		ls_html += "									<td width='10px'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0'";
		ls_html += "								style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-bottom: 1px solid #C0C0C0; border-top: 0; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px;'>";
		ls_html += "								<tr height='16px'>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "									<td></td>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><table style='min-width: 300px;' border='0' cellspacing='0' cellpadding='0'> ";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>"
				+ paramMap.get("USER_NM") + " 님, 안녕하세요.</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='8px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>계정 <a>"
				+ paramMap.get("USER_ID") + "</a> 의 회원정보가 등록되었습니다.<br>";
		ls_html += "												</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='32px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>hongkkong.com 드림</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='16px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 12px; color: #B9B9B9; line-height: 1.5;'>";
		ls_html += "														<tr>";
		ls_html += "															<td>본 이메일은 발신 전용입니다. 자세히 알아보려면 <a href='http://hongkkong.com' data-meta-key='help' style='text-decoration: none; color: #4285F4;' target='_blank'>hongkkong.com 사이트</a>를 방문하세요. ";
		ls_html += "															</td>";
		ls_html += "														</tr>";
		ls_html += "													</table></td>";
		ls_html += "											</tr>";
		ls_html += "										</table></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='32px'></tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr height='16'></tr> ";
		ls_html += "					<tr>";
		ls_html += "						<td style='max-width: 600px; font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #BCBCBC; line-height: 1.5;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #666666; line-height: 18px; padding-bottom: 10px'>";
		ls_html += "								<tr>";
		ls_html += "									<td>본 이메일은 계정과 관련된 중요한 변경사항을 알려드리기 위한 필수 이메일 서비스 공지입니다.</td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='6px'></tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><div style='direction: ltr; text-align: left'>ⓒ hongkkong.com </div></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					</td>";
		ls_html += "					</tr>";
		ls_html += "				</table></td>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "		</tr>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "	</table>";
		ls_html += "</body>";
		ls_html += "</html>";

		return ls_html;
	}
	
	public String idProcessHtml(Map<String, Object> paramMap)
	{
		String ls_html = "";
		
		ls_html += "<html lang='ko'>";
		ls_html += "<head>";
		ls_html += "<m-ta name='format-detection' content='date=no'> ";
		ls_html += "<m-ta name='format-detection' content='email=no'>";
		ls_html += "</head>";
		ls_html += "<body style='margin: 0; padding: 0;' bgcolor='#FFFFFF'>";
		ls_html += "	<table width='100%' height='100%' style='min-width: 348px;' border='0' cellspacing='0' cellpadding='0'>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "		<tr align='center'>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "			<td><table border='0' cellspacing='0' cellpadding='0' style='max-width: 600px;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#D94235' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #E0E0E0; border-bottom: 0; border-top-left-radius: 3px; border-top-right-radius: 3px;'>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px' height='60px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 24px; color: #FFFFFF; line-height: 1.25;'>회원님의 정보입니다.</td>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FFFFFF' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-top: 0;'>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>";
		ls_html += "									  본인의 계정이 아니라면 이메일 수신을 중단하세요.";
		ls_html += "									</td>";
		ls_html += "									<td width='10px'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0'";
		ls_html += "								style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-bottom: 1px solid #C0C0C0; border-top: 0; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px;'>";
		ls_html += "								<tr height='16px'>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "									<td></td>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><table style='min-width: 300px;' border='0' cellspacing='0' cellpadding='0'> ";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>"
				+ paramMap.get("USER_NM") + " 님, 안녕하세요.</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='8px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>계정 아이디:<br> <a>"
				+ paramMap.get("USER_ID") + "</a><br> 입니다.<br>";
		ls_html += "												</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='32px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>hongkkong.com 드림</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='16px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 12px; color: #B9B9B9; line-height: 1.5;'>";
		ls_html += "														<tr>";
		ls_html += "															<td>본 이메일은 발신 전용입니다. 자세히 알아보려면 <a href='http://hongkkong.com' data-meta-key='help' style='text-decoration: none; color: #4285F4;' target='_blank'>hongkkong.com 사이트</a>를 방문하세요. ";
		ls_html += "															</td>";
		ls_html += "														</tr>";
		ls_html += "													</table></td>";
		ls_html += "											</tr>";
		ls_html += "										</table></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='32px'></tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr height='16'></tr> ";
		ls_html += "					<tr>";
		ls_html += "						<td style='max-width: 600px; font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #BCBCBC; line-height: 1.5;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #666666; line-height: 18px; padding-bottom: 10px'>";
		ls_html += "								<tr>";
		ls_html += "									<td>본 이메일은 계정과 관련된 중요한 변경사항을 알려드리기 위한 필수 이메일 서비스 공지입니다.</td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='6px'></tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><div style='direction: ltr; text-align: left'>ⓒ hongkkong.com </div></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					</td>";
		ls_html += "					</tr>";
		ls_html += "				</table></td>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "		</tr>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "	</table>";
		ls_html += "</body>";
		ls_html += "</html>";
		
		return ls_html;
	}
	
	public String passwordChangeProcessHtml(Map<String, Object> paramMap)
	{
		String ls_html = "";
		
		ls_html += "<html lang='ko'>";
		ls_html += "<head>";
		ls_html += "<m-ta name='format-detection' content='date=no'> ";
		ls_html += "<m-ta name='format-detection' content='email=no'>";
		ls_html += "</head>";
		ls_html += "<body style='margin: 0; padding: 0;' bgcolor='#FFFFFF'>";
		ls_html += "	<table width='100%' height='100%' style='min-width: 348px;' border='0' cellspacing='0' cellpadding='0'>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "		<tr align='center'>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "			<td><table border='0' cellspacing='0' cellpadding='0' style='max-width: 600px;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#D94235' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #E0E0E0; border-bottom: 0; border-top-left-radius: 3px; border-top-right-radius: 3px;'>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px' height='60px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 24px; color: #FFFFFF; line-height: 1.25;'>회원님의 비밀번호 정보입니다.</td>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FFFFFF' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-top: 0;'>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td width='32px'></td>";
		ls_html += "									<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>";
		ls_html += "									  본인의 계정이 아니라면 이메일 수신을 중단하세요.";
		ls_html += "									</td>";
		ls_html += "									<td width='10px'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td height='18px' colspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr>";
		ls_html += "						<td><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0'";
		ls_html += "								style='min-width: 332px; max-width: 600px; border: 1px solid #F0F0F0; border-bottom: 1px solid #C0C0C0; border-top: 0; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px;'>";
		ls_html += "								<tr height='16px'>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "									<td></td>";
		ls_html += "									<td width='32px' rowspan='3'></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><table style='min-width: 300px;' border='0' cellspacing='0' cellpadding='0'> ";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>"
				+ paramMap.get("USER_NM") + " 님, 안녕하세요.</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='8px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>새비밀번호:<br> <a>"
				+ paramMap.get("NEW_USER_PASSSWORD") + "</a><br>입니다.<br>";
		ls_html += "												</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='32px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 13px; color: #202020; line-height: 1.5;'>hongkkong.com 드림</td>";
		ls_html += "											</tr>";
		ls_html += "											<tr height='16px'></tr>";
		ls_html += "											<tr>";
		ls_html += "												<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 12px; color: #B9B9B9; line-height: 1.5;'>";
		ls_html += "														<tr>";
		ls_html += "															<td>본 이메일은 발신 전용입니다. 자세히 알아보려면 <a href='http://hongkkong.com' data-meta-key='help' style='text-decoration: none; color: #4285F4;' target='_blank'>hongkkong.com 사이트</a>를 방문하세요. ";
		ls_html += "															</td>";
		ls_html += "														</tr>";
		ls_html += "													</table></td>";
		ls_html += "											</tr>";
		ls_html += "										</table></td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='32px'></tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					<tr height='16'></tr> ";
		ls_html += "					<tr>";
		ls_html += "						<td style='max-width: 600px; font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #BCBCBC; line-height: 1.5;'>";
		ls_html += "					<tr>";
		ls_html += "						<td><table style='font-family: Roboto-Regular, Helvetica, Arial, sans-serif; font-size: 10px; color: #666666; line-height: 18px; padding-bottom: 10px'>";
		ls_html += "								<tr>";
		ls_html += "									<td>본 이메일은 계정과 관련된 중요한 변경사항을 알려드리기 위한 필수 이메일 서비스 공지입니다.</td>";
		ls_html += "								</tr>";
		ls_html += "								<tr height='6px'></tr>";
		ls_html += "								<tr>";
		ls_html += "									<td><div style='direction: ltr; text-align: left'>ⓒ hongkkong.com </div></td>";
		ls_html += "								</tr>";
		ls_html += "							</table></td>";
		ls_html += "					</tr>";
		ls_html += "					</td>";
		ls_html += "					</tr>";
		ls_html += "				</table></td>";
		ls_html += "			<td width='32px'></td>";
		ls_html += "		</tr>";
		ls_html += "		<tr height='32px'></tr>";
		ls_html += "	</table>";
		ls_html += "</body>";
		ls_html += "</html>";
		
		return ls_html;
	}

}
