<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="/WEB-INF/views/design_template_admin/include_title.jsp"%>
<%@include file="/WEB-INF/views/design_template_admin/include_header.jsp"%>

<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <spring:eval expression="@admin['SHOP_ADMIN']" />
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
	<div class="nav" role="navigation">
	<h3 class="sub-header">complete page</h3>
	<div class="category-filter-wrapper">
		<h4>페이지 이동중 입니다.</h4>
		<div class="progress progress-striped active">
			<div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
				<span class="sr-only">100% Complete</span>
			</div>
		</div>
	</div>
</div>
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<%@include file="/WEB-INF/views/design_template_admin/include_footer.jsp"%>
	
	<%
		String ls_RTN_URL = (String)request.getParameter("RTN_URL") ==null ? "": (String)request.getParameter("RTN_URL").replace("^", "&");
	%>

	<!-- *****************************************************************************************************
              스크립트 
     ***************************************************************************************************** -->

	<script>
		/**gloval vars**/
		var gv_SUCCESS_YN = "${SUCCESS_YN}";
		var gv_MEMBER_ID_YN = "${MEMBER_ID_YN}";
		var gv_LOGOUT_MENT_YN = "${LOGOUT_MENT_YN}";
		var ls_RTN_URL = "<%=ls_RTN_URL%>";
		
		$(function()
		{
			lfn_alert();
		});
		/**사용자 function**/
		function lfn_alert()
		{
			$(location).attr("href", lfn_getUrl());
		}
		function lfn_getUrl()
		{
			var ls_url = "/";
			if (gv_MEMBER_ID_YN == "N")
			{
				alert("가입하지 않은 아이디입니다.\n아이디를 다시 입력해주세요.");
				ls_url = "/admin/regist.do";
				return ls_url;
			}
			
			if (gv_LOGOUT_MENT_YN == "Y")
			{
				alert("로그아웃되었습니다.");
				ls_url = "/admin/login.do";
				return ls_url;
			}
			
			if (gv_SUCCESS_YN == "Y")
			{
				alert("로그인되었습니다.");
				if(ls_RTN_URL =="")
				{
					ls_url = "/admin/main.do";
				}
				else
				{
					ls_url = ls_RTN_URL;
				}
				return ls_url;
			}
			else
			{
				alert("아이디/패스워드를 다시 확인하세요.");
				ls_url = "/admin/login.do";
				return ls_url;
			}
		}
	</script>
</body>
</html>
