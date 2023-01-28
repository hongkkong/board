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
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_STOCK" onclick="#onclick">검색</a>
	                        </span>
	                    </h1>
	                    <form class="form-horizontal form-register" role="form" id="inputForm" name="inputForm">
                    	<input type="hidden" name="BOARD_TYPE_CD" id="BOARD_TYPE_CD" value="USER">
	                        <div class="form-register-header">
	                            <h2 class="text-center no-margin">검색 조건</h2>
	                            <div class="row">
	                                <div class="col-sm-9 col-sm-offset-2">
	                                    <hr>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-group has-feedback">
	                            <label for="inputEmail3" class="col-sm-2 control-label">제목</label>
	                            <div class="col-sm-9">
	                                <input type="text" class="form-control" id="TITLE" name="TITLE">
	                            </div>
	                        </div>
	                        
							 <div class="form-group">
	                            <label for="description" class="col-sm-2 control-label">내용</label>
	                            <div class="col-sm-9">
	                                <input type="text" class="form-control" id="CONTENT_DESC" name="CONTENT_DESC">
	                            </div>
	                        </div>
	                        
					        <div class="form-group has-feedback">
	                            <label for="inputEmail3" class="col-sm-2 control-label">작성자</label>
	                            <div class="col-sm-9">
	                                <input type="text" class="form-control" id="USER_NM" name="USER_NM">
	                            </div>
	                        </div>
	                        
	                        <div class="form-group">
	                                <div class="col-sm-offset-4 col-sm-5">
	                                    <ul class="list-inline text-center">
	                                        <li>
	                                           <span class="btn-group">
						                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_searchInfo(1)">검색</a>
						                            <a href="/board/main.do" class="btn btn-lg btn-danger tiles-view active">메인으로</a>
						                        </span>
	                                        </li>
	                                    </ul>
	                                </div>
	                        </div>
                    	</form>
                    	
                    	<div class="form-register-header">
                            <h2 class="text-center no-margin">검색 결과</h2>
                            <div class="row">
                                <div class="col-sm-9 col-sm-offset-2">
                                    <hr>
                                </div>
                            </div>
                        </div>
	                  	<div class="row" id="MAIN_LIST">
                        </div>
                    	<div class="row" id="MORE_NEXT">
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
	
	    <!-- Masonry -->
	    <script type="text/javascript" src="/resources/ui/js/masonry.pkgd.min.js"></script>
	
	    <!-- Shuffle -->
	    <script type="text/javascript" src="/resources/ui/js/jquery.shuffle.modernizr.min.js"></script>
	
	    <!-- Custom javascript -->
	    <script type="text/javascript" src="/resources/ui/js/custom.min.js"></script>
	    
		<script src="/resources/ui/js/jquery.twbsPagination.js"></script>
		
		<script src="/resources/ui/js/hongkkongUtil.js"></script>
	
	</body>
	</html>
	<script>
	//onload
	$(function()
	{
		//초기 증권에 액티브 적용
		$("#BTN_STOCK").addClass("btn btn-lg btn-danger list-view active");

		//초기값 주식으로 수정
		$("#MORE_HREF").attr("href", "https://finance.naver.com/");
		
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
	
	this.lfn_searchInfo =function(lv_pageNum)
	{	
		var params ={
			TITLE        :$("#TITLE").val()
			,CONTENT_DESC:$("#CONTENT_DESC").val()
			,USER_NM     :$("#USER_NM").val()
			,PAGE_NUM    :lv_pageNum
		};
		
		var lo_coreJson = gfn_service(params,"/board/searchData.do");
		this.lfn_searchData(lo_coreJson);
		this.lfn_searchNextData(lv_pageNum);
        $("html, body").animate({ scrollTop:0 },500);
	};	
	
	this.lfn_searchData = function(lo_coreJson)
	{		
		$("#MAIN_LIST").html("");
		var lv_listHtml = "";
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM=1'>"+data.TITLE+"</a></h3>	";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM=1'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM=1'><p>"+data.CONTENT_DESC+"</p></a>";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          			";
			});//JSON ARRAY
			
			$("#MAIN_LIST").html(lv_listHtml);
		}
	};
	
	this.lfn_searchNextData = function(lv_pageNum)
	{
		$("#MORE_NEXT").html("");
		var lv_listHtml = "";
		var lv_pageNextNum=lv_pageNum;
		
		var params ={
				TITLE        	:$("#TITLE").val()
				,CONTENT_DESC	:$("#CONTENT_DESC").val()
				,USER_NM     	:$("#USER_NM").val()
				,PAGE_NOW_NUM   :lv_pageNum
			};
			
		var lo_coreJson = gfn_service(params,"/board/searchNextPageData.do");
		
		if(lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				lv_pageNextNum = data.PAGE_NEXT_NUM;
			});//JSON ARRAY
			
			lv_listHtml +="<div class='col-md-6 col-md-offset-3'>";
			lv_listHtml +="    <a class='btn standard-hover-effect bg-red btn-block' id='MORE_NEXT' onclick='lfn_searchInfo("+lv_pageNextNum+")'>";
			lv_listHtml +="        <span class='text'>More <i class='fa fa-arrow-right'></i></span>";
			lv_listHtml +="    </a>";
			lv_listHtml +="</div>";
		}
		
		$("#MORE_NEXT").html(lv_listHtml);
	};
	
	
	</script>

