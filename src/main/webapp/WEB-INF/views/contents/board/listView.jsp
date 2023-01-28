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
	                            <a class="btn btn-lg btn-danger list-view">${MENU_NM}</a>
	                        </span>
	                    </h1>
						<div class="row">
                            <ul class="list-inline text-right">
                                <li>
                                    <span class="btn-group">
                                    	<c:choose>
                                    		<c:when test="${BTN_VIEW.SEQ ne null}">
	                                    		<a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_delete()">삭제</a>
				                            	<a href="/board/update.do?MENU_CD=${MENU_CD}&SEQ=${LIST_VIEW.SEQ}" class="btn btn-lg btn-danger list-view">수정</a>
                                    		</c:when>
                                    	</c:choose>
			                            
			                            <a href="/board/article.do?MENU_CD=${MENU_CD}&PAGE_NUM=${PAGE_NUM}" class="btn btn-lg btn-danger tiles-view">목록</a>
                     				 </span>
                                  </li>
                              </ul>
                        </div>
                        <hr>
						<div class="standard-text">
	                       	<div class="row">
		                        <div class="col-sm-12 col-md-12">
		                            <h1 class="item-heading">
		                                ${LIST_VIEW.TITLE}
		                            </h1>
		                        </div>
	                        	<div class="col-sm-12">${LIST_VIEW.CONTENT_DESC}</div>
	                        </div>
	                        <div class="thumbnail">
	                        	<img src="${LIST_VIEW.CRAWLER_IMG_DEC}" class="thumbnail" onerror="this.style.display='none';">
	                        </div>
	                        <div class="article-toolbar">
	                            <div class="row">
	                                <div class="col-sm-8">
	                                    <div class="article-toolbar-info">
	                                        <span class="info-item">
	                                            <i class="fa fa-user"></i> <strong> ${LIST_VIEW.USER_NM}</strong>
	                                        </span>
	                                        <span class="info-item">
	                                            	${LIST_VIEW.UPDATE_DATETIME}
	                                        </span>
	                                        <span class="info-item">
	                                            <i class="fa fa-eye"></i> <c:choose><c:when test="${LIST_VIEW.CLICK_CNT eq null}">0</c:when><c:otherwise>${LIST_VIEW.CLICK_CNT}</c:otherwise></c:choose> Views
	                                        </span>
	                                    </div>
	                                </div>
	                                <form id="MAIN_FORM" name="MAIN_FORM">
	                                	<input type="hidden" name="SEQ" id="SEQ" value="${LIST_VIEW.SEQ}">
	                                	<input type="hidden" name="MENU_CD" id="MENU_CD" value="${MENU_CD}">
	                                </form>
	                            </div>
	                        </div>
	                        <div class="clearfix"></div>
	                    </div>
	                    <div class="clearfix"></div>
	                    
	                    <h2 class="text-heading" id="COMMENTS_CNT_VIEW">Comments <strong>(<c:choose><c:when test="${LIST_VIEW.COMMENTS_CNT eq null}">0</c:when><c:otherwise>${LIST_VIEW.COMMENTS_CNT}</c:otherwise></c:choose>)</strong></h2>
	                    <div class="comments-list" id="COMMENTS_LIST">

	                    </div>
	                    
	                    <div class="pagination">
							<ul id="commentsPagingArea"></ul>
						</div>
	                    
	                    <div class="add-comment" data-scroll-to="target" id="ADD_COMMENT_VIEW">
	                        <h2 class="text-heading">Add comment:</h2>
	                        <div class="comment">
	                            <form class="form-horizontal" role="form" id="COMMENT_FORM" name="COMMENT_FORM">
	                            	<input type="hidden" name="SEQ" id="SEQ">
	                            	<input type="hidden" name="BOARD_SEQ" id="BOARD_SEQ" value="${LIST_VIEW.SEQ}">
	                            	<input type="hidden" name="MENU_CD" id="MENU_CD" value="${LIST_VIEW.MENU_CD}">
	                                <div class="form-group">
	                                    <div class="col-md-offset-3 col-md-9">
	                                        <textarea class="form-control" id="COMMENT_DESC" name="COMMENT_DESC" placeholder="바르고 고은말.."></textarea>
	                                    </div>
	                                </div>
	                                <div class="form-group has-feedback">
	                                    <div class="col-md-offset-3 col-md-9">
	                                        <span class="glyphicon glyphicon-user form-control-feedback form-control-feedback-lg"></span>
	                                        <input type="text" class="form-control input-lg" id="COMMENT_USER_NM" name="COMMENT_USER_NM" placeholder="성명" value="<%=gv_USER_NM%>">
	                                        <input type="hidden" id="CREATE_ID" name="CREATE_ID" placeholder="성명" value="<%=gv_USER_ID%>">
	                                    </div>
	                                </div>
		                           <div class="form-group">
		                                <div class="col-sm-offset-4 col-sm-5">
		                                    <ul class="list-inline text-center">
		                                        <li>
		                                           <span class="btn-group">
							                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_save()">저장</a>
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

	            <div class="col-md-8">
	                <div class="standard-box">
	                    <h1 class="standard-header">
	                        <span>
	                            <a class="btn btn-lg btn-danger list-view">${MENU_NM}</a>
	                        </span>
	                    </h1>
	                  	<div class="row" id="MAIN_LIST">

                        </div>
                        
                       	<div class="pagination">
							<ul id="pagingArea"></ul>
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
		
		var lv_commentYn ="${LIST_VIEW.COMMENT_YN}";
		
		if(lv_commentYn=="Y")
		{
			lfn_commentsInfo(1);
			$("#ADD_COMMENT_VIEW").show();
			$("#COMMENTS_CNT_VIEW").show();
		}
		else
		{
			$("#ADD_COMMENT_VIEW").hide();
			$("#COMMENTS_CNT_VIEW").hide();
		}
	});
	
	this.lfn_articleInfo =function(lv_pageNum)
	{
		lfn_service(lv_pageNum, ${LIST_LIMIT_NUM} ,${VISIBLE_PAGES}, ${TOTAL_CNT},"pagingArea","${MENU_CD}","/board/articleData.do","lfn_dataView");
	};	
	
	this.lfn_dataView = function(lo_coreJson)
	{
		$("#MAIN_LIST").html("");
		var lv_listHtml = "";
		var lv_seq =$("#BOARD_SEQ").val();
		
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='col-sm-12'>                                                    			";
				
				if(lv_seq ==data.SEQ)
				{
					lv_listHtml +="<h3 class='standard-article-header'><i class='fa fa-play' style='color:#FA5456'></i> <a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"' style='color:#FA5456'>"+data.TITLE+"</a></h3>	";
				}
				else
				{
					lv_listHtml +="<h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"'>"+data.TITLE+"</a></h3>	";
				}
				lv_listHtml +=""+data.UPDATE_DATETIME+"";
				lv_listHtml +="<div class='list-toolbar'>                                                 			";
				lv_listHtml +="<a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"&PAGE_NUM="+data.PAGE_NUM+"'><i class='fa fa-comments'></i> "+data.COMMENTS_CNT+" comments</a>";
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
		var lv_seq =$("#BOARD_SEQ").val();
		
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
				if(lv_seq ==data.SEQ)
				{
					lv_listHtml +="        <h3 class='standard-article-header'><i class='fa fa-play' style='color:#FA5456'></i> <a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"' style='color:#FA5456'>"+data.TITLE+"</a></h3>  ";
				}
				else
				{
					lv_listHtml +="        <h3 class='standard-article-header'><a href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>"+data.TITLE+"</a></h3>  ";
				}
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
	
	this.lfn_commentsInfo = function(lv_pageNum)
	{
		//lfn_service(gv_PAGE_NUM, gv_LIST_LIMIT_NUM ,gv_VISIBLE_PAGES, ls_TOTAL_COUNT);
		lfn_service(lv_pageNum, ${LIST_LIMIT_NUM} ,${VISIBLE_PAGES}, ${LIST_VIEW.COMMENTS_CNT},"commentsPagingArea","${LIST_VIEW.SEQ}","/board/commentsData.do","lfn_commnetsViewData");
	};
	
	this.lfn_commnetsViewData = function(lo_coreJson)
	{
		var lv_userId="<%=gv_USER_ID%>";
		var lv_sessionId="<%=gv_USER_SESSION%>";
		var lv_listHtml = "";
		$("#COMMENTS_LIST").html("");
	
		if (lo_coreJson != null && lo_coreJson.length > 0)
		{
			$.each(lo_coreJson, function(k, n)
			{
				var data = lo_coreJson[k];
				
				lv_listHtml +="<div class='row'>                                                           ";
				lv_listHtml +="    <div class='col-xs-12'>                                                 ";
				lv_listHtml +="        <span class='author'><i class='fa fa-user'></i> <a href='#'>"+data.USER_NM+"</a></span>     ";
				lv_listHtml +="        <p>"+data.UPDATE_DATETIME;
				
				if(lv_userId ==data.CREATE_ID || lv_sessionId == data.CREATE_ID)
				{
					lv_listHtml +="        	<a href='#' onclick=lfn_deleteComment('"+data.SEQ+"')>ⓧ</a>";
				}
				
				lv_listHtml +="        </p>";
				lv_listHtml +="        <p class='no-margin'>"+data.CONTENT_DESC+"</p> ";
			
				lv_listHtml +="    </div>                                                                   ";
				lv_listHtml +="    <div class='col-xs-12'>                                                  ";
				lv_listHtml +="        <hr>                                                                 ";
				lv_listHtml +="    </div>                                                                   ";
				lv_listHtml +="</div>                                                                       ";
			});//JSON ARRAY
				
			$("#COMMENTS_LIST").html(lv_listHtml);
		}
	}
	
	this.lfn_save = function()
	{
		if (!lfn_preSave()) return;

		var param = $("form[name=COMMENT_FORM]").serialize();
		var ls_result =gfn_postService(param,"/board/writeCommentProccess.do");
		
		if(ls_result !=undefined && ls_result !="")
		{
			$("#SEQ").val(ls_result);
		}
		alert("저장되었습니다.");
		var lv_url ="/board/listView.do?MENU_CD=${MENU_CD}&SEQ=${SEQ}";
		$(location).attr("href", lv_url);
	}
	
	/**validate**/
	this.lfn_preSave = function()
	{
		if (!$("#COMMENT_DESC").val())
		{
			alert("내용이 비어있습니다.");
			$("#COMMENT_DESC").focus();
			return false;
		}
		
		if (!$("#COMMENT_USER_NM").val())
		{
			alert("사용자가 비어있습니다.");
			$("#COMMENT_USER_NM").focus();
			return false;
		}
		return true;
	}
	
	this.lfn_delete = function()
	{
		if(confirm("삭제하시겠습니까?"))
		{
			var param = $("form[name=MAIN_FORM]").serialize();
			var ls_result =gfn_postService(param,"/board/deleteProccess.do");
			alert("저장되었습니다.");
			var lv_url ="/board/article.do?MENU_CD=${MENU_CD}";
			$(location).attr("href", lv_url);
		}
	}
	
	this.lfn_delete = function()
	{
		if(confirm("삭제하시겠습니까?"))
		{
			var param = $("form[name=MAIN_FORM]").serialize();
			var ls_result =gfn_postService(param,"/board/deleteProccess.do");
			alert("저장되었습니다.");
			var lv_url ="/board/article.do?MENU_CD=${MENU_CD}";
			$(location).attr("href", lv_url);
		}
	}
	
	this.lfn_deleteComment = function(lv_num)
	{
		if(confirm("삭제하시겠습니까?"))
		{
			var param ={SEQ:lv_num};
			var ls_result =gfn_service(param,"/board/deleteCommentProccess.do");
			alert("저장되었습니다.");
			var lv_url ="/board/listView.do?MENU_CD=${MENU_CD}&SEQ=${SEQ}";
			$(location).attr("href", lv_url);
		}
	}
	
	</script>

