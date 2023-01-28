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
    <link href="/resources/ui/css/styles.css" media="screen" rel="stylesheet" type="text/css" >

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
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view">Login</a>
	                        </span>
	                    </h1>
	                  	<div class="row" id="MAIN_LIST">

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
		lfn_stockInfo();
	});
	
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
	
	</script>

