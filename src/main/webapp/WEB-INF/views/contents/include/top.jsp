<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	String gv_SEQ = (String) session.getAttribute("SEQ") == null ? "": (String) session.getAttribute("SEQ");
	String gv_USER_NM = (String) session.getAttribute("USER_NM") == null ? "": (String) session.getAttribute("USER_NM");
	String gv_USER_ID = (String) session.getAttribute("USER_ID") == null ? "": (String) session.getAttribute("USER_ID");
	String gv_USER_EMAIL = (String) session.getAttribute("USER_EMAIL") == null ? "": (String) session.getAttribute("USER_EMAIL");
	String gv_USER_ADDR_IP = (String) session.getAttribute("USER_ADDR_IP") == null ? "": (String) session.getAttribute("USER_ADDR_IP");
	String gv_USER_SESSION = (String) session.getAttribute("USER_SESSION") == null ? "": (String) session.getAttribute("USER_SESSION");
	String gv_BLOCK_YN = (String) session.getAttribute("BLOCK_YN") == null ? "": (String) session.getAttribute("BLOCK_YN");
	String gv_CONNECT_TYPE_CD = (String) session.getAttribute("CONNECT_TYPE_CD") == null ? "": (String) session.getAttribute("CONNECT_TYPE_CD");
%>
<div class="header-top">

	 <div class="row">
	    <div class="col-xs-12">
	        <div class="header-top-dropdown">
	            <div class="btn-group">
	                <button type="button" class="btn dropdown-toggle">
	                    <span class="text" onclick="lfn_searchPage()"><i class="fa fa-search"></i> Search</span>
	                    <span class="equalizer-bar bg-red"></span>
	                </button>
	            </div>
	            <%if(!"".equals(gv_SEQ)){ %>
	            <div class="btn-group dropdown">
	                <button type="button" class="btn dropdown-toggle">
	                    <span class="text" onclick="lfn_memberUpdate()"><i class="fa fa-user"></i> <%=gv_USER_NM %> </span>
	                    <span class="equalizer-bar bg-red"></span>
	                </button>
	            </div>
	            <%}%>
	            <div class="btn-group dropdown">
	                <button type="button" class="btn dropdown-toggle">
	                    <span class="text" onclick=<%if("".equals(gv_SEQ)){ %>lfn_login()<%}else{%>lfn_logout()<%} %>><%if("".equals(gv_SEQ)){ %><i class="fa fa-user"></i> Login<%}else{%><i class="fa fa-sign-out"></i> Logout<%} %></span>
	                    <span class="equalizer-bar bg-red"></span>
	                </button>
	            </div>
	        </div>
	    </div>
	</div>
</div>

<script>
function lfn_searchPage()
{
	document.location.href="/board/searchList.do";
}

this.lfn_login = function()
{
	document.location.href="/board/login.do";
}

this.lfn_logout = function()
{
	if(confirm("로그아웃 하시겠습니까?"))
	{
		var param ={};
		var lo_coreJson =gfn_postService(param,"/member/logoutProcess.do");
		alert("로그아웃 되었습니다.");
		var lv_url ="/board/main.do";
		$(location).attr("href", lv_url);
	}
}

this.lfn_memberUpdate = function () 
{
	document.location.href="/member/main.do";
};

</script>
