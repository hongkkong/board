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
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_STOCK" onclick="lfn_mainChange(this)">${MENU_001}</a>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_COIN" onclick="lfn_mainChange(this)">${MENU_002}</a>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_LAND" onclick="lfn_mainChange(this)">${MENU_003}</a>
	                        </span>
	                    </h1>
	                  	<div class="row" id="MAIN_LIST">

                        </div>
	                    
	                    <div class="text-right">
	                    	<a class="btn standard-hover-effect bg-red" id="MAIN_MORE_HREF">
                            	<span class="text">More <i class="fa fa-arrow-right"></i></span>
                            </a>
	                    </div>
	                </div>
	                
	                <div class="standard-box">
	                    <h1 class="standard-header">
	                        <span>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_ETC_HUMOR" onclick="lfn_mainEtcChange(this)">${MENU_004}</a>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_ETC_BEST" onclick="lfn_mainEtcChange(this)">${MENU_005}</a>
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_ETC_TRIP" onclick="lfn_mainEtcChange(this)">${MENU_006}</a>
	                        </span>
	                    </h1>
	                  	<div class="row" id="MAIN_ETC_LIST">
                        </div>
	                    <div class="text-right">
	                    	<a class="btn standard-hover-effect bg-red" id="MAIN_ETC_MORE_HREF">
                            	<span class="text">More <i class="fa fa-arrow-right"></i></span>
                            </a>
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
		//초기 증권에 액티브 적용
		$("#MAIN_STOCK").addClass("btn btn-lg btn-danger list-view active");
		$("#MAIN_ETC_HUMOR").addClass("btn btn-lg btn-danger list-view active");
		$("#BTN_STOCK").addClass("btn btn-lg btn-danger list-view active");

		//초기값 주식으로 수정
		$("#MAIN_MORE_HREF").attr("href", "/board/article.do?MENU_CD=STOCK");
		$("#MAIN_ETC_MORE_HREF").attr("href", "/board/article.do?MENU_CD=HUMOR");
		$("#MORE_HREF").attr("href", "https://finance.naver.com/");
		
		lfn_stockData();
		lfn_humorData();
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
	
	this.lfn_stockData = function()
	{
		var params ={
					  MENU_CD : "STOCK"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 5
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>";
				lv_listHtml +=""+data.UPDATE_DATETIME+"		                                               			";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>		";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               			";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          			";
			});//JSON ARRAY
				
			$("#MAIN_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_coinData = function()
	{
		var params ={
					  MENU_CD : "COIN"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 5
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>	";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               			";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                         		 	";
			});//JSON ARRAY
				
			$("#MAIN_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_landData = function()
	{
		var params ={
					  MENU_CD : "LAND"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 5
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>	";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>		";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               			";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          			";
			});//JSON ARRAY
				
			$("#MAIN_LIST").html(lv_listHtml);
		}
	};
	
	
	this.lfn_mainChange =function(btn)
	{
		if(btn.id=="MAIN_COIN")
		{
			this.lfn_coinData();
			
			$("#MAIN_STOCK").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_COIN").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_LAND").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_MORE_HREF").attr("href", "/board/article.do?MENU_CD=COIN");
		}
		else if(btn.id=="MAIN_LAND")
		{
			this.lfn_landData();
			
			$("#MAIN_STOCK").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_COIN").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_LAND").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_MORE_HREF").attr("href", "/board/article.do?MENU_CD=LAND");
		}
		else
		{
			this.lfn_stockData();
			
			$("#MAIN_STOCK").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_COIN").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_LAND").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_MORE_HREF").attr("href", "/board/article.do?MENU_CD=STOCK");
		}
	};
	
	
	this.lfn_humorData = function()
	{
		var params ={
					  MENU_CD : "HUMOR"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 10
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_ETC_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    	";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>		";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 	";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				lv_listHtml +="</div>                                                                     	";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               	";
				lv_listHtml +="</div>                                                                     	";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          	";
			});//JSON ARRAY
				
			$("#MAIN_ETC_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_bestData = function()
	{
		var params ={
					  MENU_CD : "BEST"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 10
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_ETC_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    ";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 ";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				lv_listHtml +="</div>                                                                     ";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               ";
				lv_listHtml +="</div>                                                                     ";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          ";
			});//JSON ARRAY
				
			$("#MAIN_ETC_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_tripData = function()
	{
		var params ={
					  MENU_CD : "TRIP"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 10
					};
		var lo_coreJson = gfn_service(params,"/select/board/mainList.do");
		var lv_listHtml = "";
		$("#MAIN_ETC_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
						
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    ";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 ";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				lv_listHtml +="</div>                                                                     ";
				lv_listHtml +="<p>"+data.CONTENT_DESC+"</p>                                               ";
				lv_listHtml +="</div>                                                                     ";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          ";
			});//JSON ARRAY
				
			$("#MAIN_ETC_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_mainEtcChange =function(btn)
	{
		if(btn.id=="MAIN_ETC_BEST")
		{
			this.lfn_bestData();
			
			$("#MAIN_ETC_HUMOR").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_BEST").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_ETC_TRIP").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_MORE_HREF").attr("href", "/board/article.do?MENU_CD=BEST");
		}
		else if(btn.id=="MAIN_ETC_TRIP")
		{
			this.lfn_tripData();
			
			$("#MAIN_ETC_HUMOR").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_BEST").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_TRIP").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_ETC_MORE_HREF").attr("href", "/board/article.do?MENU_CD=TRIP");
		}
		else
		{
			this.lfn_humorData();
			
			$("#MAIN_ETC_HUMOR").addClass("btn btn-lg btn-danger list-view active");
			$("#MAIN_ETC_BEST").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_TRIP").addClass("btn btn-lg btn-danger list-view");
			$("#MAIN_ETC_MORE_HREF").attr("href", "/board/article.do?MENU_CD=HUMOR");
		}
	};
	
	</script>

