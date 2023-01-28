<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<!DOCTYPE html>
	<html lang="KR">
	<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>hongkkong.com</title>

    <!-- Opens Sans Font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600' rel='stylesheet' type='text/css'>

    <!-- Bootstrap styles -->
    <link href="/resources/ui/bootstrap-3.3.6-dist/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" >

    <!-- Font Awesome -->
    <link href="/resources/ui/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css" >

    <!-- Lightbox styles -->
    <link href="/resources/ui/lightbox/css/lightbox.min.css" media="screen" rel="stylesheet" type="text/css" >

    <!-- Custom styles -->
    <link href="/resources/ui/css/styles.min.css" media="screen" rel="stylesheet" type="text/css" >

    <!-- Modernizr -->
    <script type="text/javascript" src="/resources/ui/js/modernizr.custom.min.js"></script>
    
	</head>
	
	<body data-spy="scroll" data-target="#main-menu">
	    <div class="preloader">
	        <div class="loader">
	            <div class="bar bar-1 bg-red"></div>
	            <div class="bar bar-2 bg-orange"></div>
	            <div class="bar bar-3 bg-yellow"></div>
	            <div class="bar bar-4 bg-green"></div>
	            <div class="bar bar-5 bg-green-2"></div>
	            <div class="text">Loading</div>
	        </div>
	    </div>
	    <div class="container">
	        <%@include file="/WEB-INF/views/contents/include/top.jsp"%>
	        <div class="main-header">
	            <div class="navbar navbar-inverse">
	                <div class="navbar-header">
	                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
	                        <span class="sr-only">Toggle navigation</span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                    </button>
	                    <div class="navbar-brand">
							<a href="${BOARD_URL}">${BOARD_NAME}</a>
						</div>
	                </div>
	                <div class="collapse navbar-collapse" id="main-menu">
	                    ${MENU_LIST}
	                </div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-md-8">
	                <div class="standard-box">
	                    <h1 class="standard-header">
	                        <span>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view"><%if("".equals(gv_SEQ)){ %>회원가입<%}else{%>회원정보 수정<%} %></a>
	                        </span>
	                    </h1>
	                  	<div class="row">
							<div id="naver_id_login">
							<form class="form-horizontal form-register" role="form" id="registForm" name="registForm">
							 <input type="hidden" id="SEQ" name="SEQ" value="<%=gv_SEQ%>">
			                        <div class="form-register-header">
			                            <h2 class="text-center no-margin"><%if("".equals(gv_SEQ)){ %>회원가입<%}else{%>회원정보 수정<%} %></h2>
			                            <div class="row">
			                                <div class="col-sm-9 col-sm-offset-2">
			                                    <hr>
			                                </div>
			                            </div>
			                        </div>
			                        <div class="form-group has-feedback">
			                            <label for="inputEmail3" class="col-sm-2 control-label">사용자명</label>
			                            <div class="col-sm-9">
			                                <input type="text" class="form-control" id="USER_NM" name="USER_NM" placeholder="사용자명" value="<%=gv_USER_NM%>">
			                            </div>
			                        </div>
			                        <%if("".equals(gv_SEQ)){ %>
				                        <div class="form-group has-feedback">
				                            <label for="inputEmail3" class="col-sm-2 control-label">이메일</label>
				                            <div class="col-sm-9">
				                                <input type="text" class="form-control" id="USER_EMAIL" name="USER_EMAIL" placeholder="이메일" value="<%=gv_USER_EMAIL%>"> 
				                            </div>
				                        </div>
			                        
				                        <div class="form-group has-feedback">
				                            <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
				                            <div class="col-sm-9">
				                                <input type="text" class="form-control" id="USER_ID" name="USER_ID" placeholder="아이디" value="<%=gv_USER_ID%>">
				                            </div>
				                        </div>
			                        <%} %>
			                        <div class="form-group has-feedback">
			                            <label for="inputEmail3" class="col-sm-2 control-label">password</label>
			                            <div class="col-sm-9">
			                                <input type="password" class="form-control" id="USER_PASSWORD" name="USER_PASSWORD" placeholder="비밀번호">
			                            </div>
			                        </div>
			                        <div class="form-group has-feedback">
			                            <label for="inputEmail3" class="col-sm-2 control-label">retry password</label>
			                            <div class="col-sm-9">
			                                <input type="password" class="form-control" id="RE_USER_PASSWORD" name="RE_USER_PASSWORD" placeholder="비밀번호 확인">
			                            </div>
			                        </div>
			                        
			                        <div class="form-group">
		                                <div class="col-sm-offset-4 col-sm-5">
		                                    <ul class="list-inline text-center">
		                                        <li>
		                                           <span class="btn-group">
							                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_save()"><%if("".equals(gv_SEQ)){ %>승인요청<%}else{%>수정요청<%} %></a>
							                        </span>
		                                        </li>
		                                    </ul>
		                                </div>
			                        </div>
		                    	</form>
							</div>
                        </div>
	                </div>
	            </div>
	            <div class="col-md-4">
	                <div class="row">
	                    <div class="col-md-12 col-sm-6 col-xs-12">
	                        <div class="standard-box">
	                            <h2 class="standard-header">
	                            <span>
		                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_change(this)" id="BTN_STOCK">${MENU_001}</a>
		                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_change(this)" id="BTN_COIN">${MENU_002}</a>
		                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_change(this)" id="BTN_LAND">${MENU_003}</a>
	                        	</span>
	                            </h2>
	                            <div class="standard-article-item event" id="CRAWLER_ZONE">
	                            </div>
	                            <div class="standard-article-item event" id="CRAWLER_ZONE_DTL_LIST">
	                            </div>

	                            <div class="row">
	                                <div class="col-md-6 col-md-offset-6">
	                                    <a class="btn standard-hover-effect bg-red btn-block" id="MORE_HREF" target="_blank">
	                                        <span class="text">More <i class="fa fa-arrow-right"></i></span>
	                                    </a>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
		<%@include file="/WEB-INF/views/contents/include/foot.jsp"%>
	
	    <!-- jQuery -->
  		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	    <!-- Bootstrap -->
	    <script type="text/javascript" src="/resources/ui/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
	
	    <!-- jQuery cookie -->
	    <script type="text/javascript" src="/resources/ui/js/jquery.cookie.min.js"></script>
	
	    <!-- jQuery scrollTo -->
	    <script type="text/javascript" src="/resources/ui/js/jquery-scrollto-min.js"></script>
	
	    <!-- Lightbox -->
	    <script type="text/javascript" src="/resources/ui/lightbox/js/lightbox.min.js"></script>
	
	    <!-- Google maps -->
	    <script src="https://maps.googleapis.com/maps/api/js"></script>
	
	    <!-- Masonry -->
	    <script type="text/javascript" src="/resources/ui/js/masonry.pkgd.min.js"></script>
	
	    <!-- Shuffle -->
	    <script type="text/javascript" src="/resources/ui/js/jquery.shuffle.modernizr.min.js"></script>
	
	    <!-- Custom javascript -->
	    <script type="text/javascript" src="/resources/ui/js/custom.min.js"></script>
	    
		<script src="/resources/ui/js/hongkkongUtil.js"></script>
		
	
	</body>
	</html>
	<script>
	//onload
	$(function()
	{	
		this.onload();
	});
	
	this.onload = function()
	{
		this.lfn_stockInfo();
	};
	
	this.lfn_stockInfo =function()
	{
		var params ={};
		var lo_coreJson = gfn_service(params,"/board/stockInfoData.do");
		var lv_headHtml = "";
		var lv_listHtml = "";
		$("#CRAWLER_ZONE").html("");
		$("#CRAWLER_ZONE_DTL_LIST").html("");
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			lv_headHtml +="<div class='row'>          ";
			lv_headHtml +="    <div class='col-xs-5'> ";
			lv_headHtml +="    주식명                   ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-4'> ";
			lv_headHtml +="    시세                    ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-3'> ";
			lv_headHtml +="    증감                    ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-12'>";
			lv_headHtml +="        <hr>               ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="</div>                     ";
			
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='row'>          ";
				lv_listHtml +="    <div class='col-xs-5'> ";
				lv_listHtml +="    <a href='"+data.STOCK_HREF+"' target='_blank'>"+data.STOCK_NM+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-4'> ";
				lv_listHtml +="    <a href='"+data.STOCK_HREF+"' target='_blank'>"+data.STOCK_PRICE+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-3'> ";
				lv_listHtml +="    <a href='"+data.STOCK_HREF+"' target='_blank'>"+data.STOCK_PLUS+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-12'>";
				lv_listHtml +="        <hr>               ";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="</div>                     ";
				
			});//JSON ARRAY
				
			$("#CRAWLER_ZONE").html(lv_headHtml);
			$("#CRAWLER_ZONE_DTL_LIST").html(lv_listHtml);
		}
	};	
	
	this.lfn_coinInfo = function()
	{
		var params ={};
		var lo_coreJson = gfn_service(params,"/board/coinInfoData.do");
		var lv_headHtml = "";
		var lv_listHtml = "";
		$("#CRAWLER_ZONE").html("");
		$("#CRAWLER_ZONE_DTL_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			lv_headHtml +="<div class='row'>          ";
			lv_headHtml +="    <div class='col-xs-5'> ";
			lv_headHtml +="    코인명                   ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-4'> ";
			lv_headHtml +="    시세                    ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-3'> ";
			lv_headHtml +="    증감                    ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="    <div class='col-xs-12'>";
			lv_headHtml +="        <hr>               ";
			lv_headHtml +="    </div>                 ";
			lv_headHtml +="</div>                     ";
			
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='row'>          ";
				lv_listHtml +="    <div class='col-xs-5'> ";
				lv_listHtml +="    <a href='"+data.COIN_HREF+"' target='_blank'>"+data.COIN_NM+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-4'> ";
				lv_listHtml +="    <a href='"+data.COIN_HREF+"' target='_blank'>"+data.COIN_PRICE+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-3'> ";
				lv_listHtml +="    <a href='"+data.COIN_HREF+"' target='_blank'>"+data.COIN_PLUS+"</a>";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="    <div class='col-xs-12'>";
				lv_listHtml +="        <hr>               ";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="</div>                     ";
			});//JSON ARRAY
				
			$("#CRAWLER_ZONE").html(lv_headHtml);
			$("#CRAWLER_ZONE_DTL_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_landInfo = function()
	{
		var params ={};
		var lo_coreJson = gfn_service(params,"/board/landInfoData.do");
		var lv_headHtml = "";
		var lv_listHtml = "";
		$("#CRAWLER_ZONE").html("");
		$("#CRAWLER_ZONE_DTL_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			
			lv_headHtml +="<div class='row'>           ";
			lv_headHtml +="    <div class='col-xs-12'> ";
			lv_headHtml +="    신문기사                  ";
			lv_headHtml +="    </div>                  ";
			lv_headHtml +="    <div class='col-xs-12'> ";
			lv_headHtml +="        <hr>                ";
			lv_headHtml +="    </div>                  ";
			lv_headHtml +="</div>                      ";
			
			
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				
				lv_listHtml +="<div class='row'>           ";
				lv_listHtml +="    <div class='col-xs-12'> ";
				lv_listHtml +="    <a href='"+data.NEWS_HREF+"' target='_blank'>"+data.NEWS_HAED+" <br/>["+data.NEWS_PAPER+"] ["+data.NEWS_CREATE_DATE+"]</a>";
				lv_listHtml +="    </div>                  ";
				lv_listHtml +="    <div class='col-xs-12'>";
				lv_listHtml +="        <hr>               ";
				lv_listHtml +="    </div>                 ";
				lv_listHtml +="</div>                     ";
			});//JSON ARRAY
				
			$("#CRAWLER_ZONE").html(lv_headHtml);
			$("#CRAWLER_ZONE_DTL_LIST").html(lv_listHtml);
		}
	};
	
	
	this.lfn_change =function(btn)
	{
		if(btn.id=="BTN_COIN")
		{
			this.lfn_coinInfo();
			
			$("#BTN_STOCK").addClass("btn btn-lg btn-danger list-view");
			$("#BTN_COIN").addClass("btn btn-lg btn-danger list-view active");
			$("#BTN_LAND").addClass("btn btn-lg btn-danger list-view");
			$("#MORE_HREF").attr("href", "https://www.bithumb.com/trade/order/BTC_KRW");
		}
		else if(btn.id=="BTN_LAND")
		{
			this.lfn_landInfo();
			
			$("#BTN_STOCK").addClass("btn btn-lg btn-danger list-view");
			$("#BTN_COIN").addClass("btn btn-lg btn-danger list-view");
			$("#BTN_LAND").addClass("btn btn-lg btn-danger list-view active");
			$("#MORE_HREF").attr("href", "https://land.naver.com/news/headline.nhn");
		}
		else
		{
			this.lfn_stockInfo();
		
			$("#BTN_STOCK").addClass("btn btn-lg btn-danger list-view active");
			$("#BTN_COIN").addClass("btn btn-lg btn-danger list-view");
			$("#BTN_LAND").addClass("btn btn-lg btn-danger list-view");
			$("#MORE_HREF").attr("href", "https://finance.naver.com/");
		}
	};
	
	this.lfn_save = function()
	{
		if(!lfn_preSave()) return;
		
		if(confirm("저장하시겠습니까?"))
		{
			var param = $("form[name=registForm]").serialize();
			var ls_result =gfn_postService(param,"/member/registProccess.do");
			
			if(ls_result !=undefined && ls_result !="")
			{
				alert("저장되었습니다.");
				var lv_url ="/board/login.do";
				$(location).attr("href", lv_url);
			}
			else
			{
				alert("저장되지 않았습니다\n관리자에게 연락바랍니다.");
			}
		}
	};
	
	/**validate**/
	function lfn_preSave()
	{
		if (!$("#USER_NM").val())
		{
			alert("사용자명이 비어있습니다.");
			$("#USER_NM").focus();
			return false;
		}
	
		<%if("".equals(gv_SEQ)){ %>
			if (!$("#USER_EMAIL").val())
			{
				alert("이메일이 비어 있습니다.");
				$("#USER_EMAIL").focus();
				return false;
			}
		
			var ls_param ={USER_EMAIL:$("#USER_EMAIL").val()};
			
			if(!this.lfn_searchUserData(ls_param))
			{
				alert("이미사용하는 이메일입니다.");
				$("#USER_EMAIL").focus();
				return false;
			}	
			
			if (!$("#USER_ID").val())
			{
				alert("아이디가 비어 있습니다.");
				$("#USER_ID").focus();
				return false;
			}
			
			
			var ls_param ={USER_ID:$("#USER_ID").val()};
			
			if(!this.lfn_searchUserData(ls_param))
			{
				alert("이미 사용하는 아이디입니다.");
				$("#USER_ID").focus();
				return false;
			}
		
		<%} %>
		<%if(!"".equals(gv_SEQ)){ %>
			
			if($("#USER_PASSWORD").val().length>0)
			{
				if (!$("#USER_PASSWORD").val())
				{
					alert("비밀번호가 비어 있습니다.");
					$("#USER_PASSWORD").focus();
					return false;
				}
		
				if (!$("#RE_USER_PASSWORD").val())
				{
					alert("비밀번호 확인이 비어 있습니다.");
					$("#RE_USER_PASSWORD").focus();
					return false;
				}
		
				if ($("#USER_PASSWORD").val() !=$("#RE_USER_PASSWORD").val())
				{
					alert("비밀번호/비밀번호 확인과 서로 다릅니다.\n다시한번 확인해주세요");
					$("#RE_USER_PASSWORD").val("");
					return false;
				}
			}
		<%}else{ %>
			if (!$("#USER_PASSWORD").val())
			{
				alert("비밀번호가 비어 있습니다.");
				$("#USER_PASSWORD").focus();
				return false;
			}
	
			if (!$("#RE_USER_PASSWORD").val())
			{
				alert("비밀번호 확인이 비어 있습니다.");
				$("#RE_USER_PASSWORD").focus();
				return false;
			}
	
			if ($("#USER_PASSWORD").val() !=$("#RE_USER_PASSWORD").val())
			{
				alert("비밀번호/비밀번호 확인과 서로 다릅니다.\n다시한번 확인해주세요");
				$("#RE_USER_PASSWORD").val("");
				return false;
			}
		<%}%>

		return true;
	}
	
	this.lfn_searchUserData = function(params)
	{		
		var lo_coreJson = gfn_service(params,"/member/searchUserData.do");
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			return false;
		}
		
		return true;
	};
	
	</script>

