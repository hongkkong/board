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
	                            <a href="#onclick" class="btn btn-lg btn-danger list-view" id="MAIN_STOCK" onclick="lfn_mainChange(this)">${MENU_NM}</a>
	                        </span>
	                    </h1>
	                    <div class="row">
                            <ul class="list-inline text-right">
                                <li>
                                    <span class="btn-group">
			                          	<a class="btn standard-hover-effect bg-red btn-block" href="/board/write.do?MENU_CD=${MENU_CD}&PAGE_NUM=${PAGE_NUM}">
		                                    <span class="text">글쓰기 <i class="fa fa-arrow-right"></i></span>
		                                </a>
                     				 </span>
                                  </li>
                              </ul>
                        </div>
                        <hr>
	                  	<div class="row" id="MAIN_LIST">

                        </div>
                        
                       	<div class="pagination">
							<ul id="pagingArea"></ul>
						</div>
			
	                </div>
	            </div>
	            
	            <div class="col-md-4">
	                <div class="row">
	                    <div class="col-md-12 col-sm-6 col-xs-12">
	                        <div class="standard-box">
	                            <h2 class="standard-header">
	                                <span class="text">TOP BEST</span>
	                            </h2>
	                            <div class="standard-article-item event" id="TOP_BEST_LIST">
	
	                            </div>
	                            
	                            <div class="row">
	                                <div class="col-md-6 col-md-offset-6">
	                                    <a class="btn standard-hover-effect bg-red btn-block" id="TOP_BEST_MORE"  href="#">
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
		lfn_articleInfo(${PAGE_NUM});
		lfn_articleTopInfo();
	});
	
	this.lfn_articleInfo =function(lv_pageNum)
	{
		lfn_service(lv_pageNum, ${LIST_LIMIT_NUM} ,${VISIBLE_PAGES}, ${TOTAL_CNT},"pagingArea","${MENU_CD}","/board/articleData.do","lfn_dataView");
	};	
	
	this.lfn_dataView = function(lo_coreJson)
	{
		$("#MAIN_LIST").html("");
		var lv_listHtml = "";
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"'>"+data.TITLE+"</a></h3>	";
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				
				if(data.COMMENT_YN=="Y")
				{
					lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
				}
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"'><p>"+data.CONTENT_DESC+"</p></a>";
				lv_listHtml +="</div>                                                                     			";
				lv_listHtml +="<div class='col-sm-12'><hr></div>                                          			";
			});//JSON ARRAY
			
			$("#MAIN_LIST").html(lv_listHtml);
		}
	};
	
	
	this.lfn_articleTopInfo = function()
	{
		var params ={
					  MENU_CD : "${MENU_CD}"
					  , START_NUM 	: 0  
					  , LIMIT_NUM 	: 5
					};
		var lo_coreJson = gfn_service(params,"/select/board/aticleTopData.do");
		var lv_listHtml = "";
		$("#TOP_BEST_LIST").html("");
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='row'>                                                                               ";
				lv_listHtml +="    <div class='col-xs-12'>                                                                     ";
				lv_listHtml +="        <a class='standard-article-image' href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>";
				lv_listHtml +="            <span class='event-status bg-red'></span>                                          ";
				lv_listHtml +="        </a>                                                                                   ";
				lv_listHtml +="    </div>                                                                                     ";
				lv_listHtml +="    <div class='col-xs-12'>                                                                     ";
				lv_listHtml +="        <h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>  ";
				lv_listHtml +="        <p>"+data.UPDATE_DATETIME+"</p>       ";
				lv_listHtml +="        <p class='no-margin'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.CONTENT_DESC+"</a></p> ";
				lv_listHtml +="    </div>                                                                                     ";
				lv_listHtml +="    <div class='col-xs-12'>                                                                    ";
				lv_listHtml +="        <hr>                                                                                   ";
				lv_listHtml +="    </div>                                                                                     ";
				lv_listHtml +="</div>                                                                                         ";
				
			});//JSON ARRAY
				
			$("#TOP_BEST_LIST").html(lv_listHtml);
			$("#TOP_BEST_MORE").attr("href", "/board/article.do?MENU_CD=${MENU_CD}");
		}
	};
	
	</script>

