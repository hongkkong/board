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

                    	<form class="form-horizontal form-register" role="form" id="inputForm" name="inputForm">
                    	<input type="hidden" name="MENU_CD" id="MENU_CD" value="${MENU_CD}">
                    	<input type="hidden" name="SEQ" id="SEQ" value="${LIST_VIEW.SEQ}">
                    	<input type="hidden" name="BOARD_TYPE_CD" id="BOARD_TYPE_CD" value="USER">
	                        <div class="form-register-header">
	                            <h2 class="text-center no-margin">글쓰기</h2>
	                            <div class="row">
	                                <div class="col-sm-9 col-sm-offset-2">
	                                    <hr>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-group has-feedback">
	                            <label for="inputEmail3" class="col-sm-2 control-label">제목</label>
	                            <div class="col-sm-9">
	                                <input type="text" class="form-control" id="TITLE" name="TITLE" placeholder="제목">
	                            </div>
	                        </div>
	                        
							 <div class="form-group">
	                            <label for="description" class="col-sm-2 control-label">내용</label>
	                            <div class="col-sm-9">
	                                <textarea class="form-control" id="CONTENT_DESC" name="CONTENT_DESC" placeholder="바르고 고은말..." rows="13"></textarea>
	                            </div>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label for="sex" class="col-sm-2 control-label">댓글 여부</label>
	                            <div class="col-sm-9">
	                                <select class="form-control" id="COMMENT_YN" name="COMMENT_YN">
	                                    <option value="Y" selected>받기</option>
	                                    <option value="N">안받기</option>
	                                </select>
	                            </div>
	                        </div>
	                        
					        <div class="form-group has-feedback">
	                            <label for="inputEmail3" class="col-sm-2 control-label">작성자</label>
	                            <div class="col-sm-9">
	                                <input type="text" class="form-control" id="USER_NM" name="USER_NM" value="<%=gv_USER_NM%>">
	                                <input type="hidden" id="CREATE_ID" name="CREATE_ID" value="<%=gv_USER_ID%>">
	                            </div>
	                        </div>
	                        
	                        <div class="form-group">
	                                <div class="col-sm-offset-4 col-sm-5">
	                                    <ul class="list-inline text-center">
	                                        <li>
	                                           <span class="btn-group">
						                            <a href="#onclick" class="btn btn-lg btn-danger list-view" onclick="lfn_save()">저장</a>
						                            <a href="/board/article.do?MENU_CD=${MENU_CD}" class="btn btn-lg btn-danger tiles-view active">목록</a>
						                        </span>
	                                        </li>
	                                    </ul>
	                                </div>
	                        </div>
                    	</form>

                    <div class="comments-list" id="COMMENTS_LIST">

                    </div>
                    
                    <div class="pagination">
						<ul id="commentsPagingArea"></ul>
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
		lfn_articleTopInfo();
	});
	
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
				lv_listHtml +="        <a class='standard-article-image' href='/board/listView.do?MENU_CD="+data.MENU_CD+"&SEQ="+data.SEQ+"'>          ";
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
	
	this.lfn_save = function()
	{
		if (!lfn_preSave()) return;

		var param = $("form[name=inputForm]").serialize();
		var ls_result =gfn_postService(param,"/board/writeProccess.do");
		
		if(ls_result !=undefined && ls_result !="")
		{
			$("#SEQ").val(ls_result);
		}
		alert("저장되었습니다.");
		var lv_url ="/board/article.do?MENU_CD=${MENU_CD}";
		$(location).attr("href", lv_url);
	}
	
	/**validate**/
	this.lfn_preSave = function()
	{
		if (!$("#TITLE").val())
		{
			alert("제목이 비어있습니다.");
			$("#TITLE").focus();
			return false;
		}
		if (!$("#CONTENT_DESC").val())
		{
			alert("내용이 비어있습니다.");
			$("#CONTENT_DESC").focus();
			return false;
		}
		
		if($("#TITLE").val().length>50)
		{
			alert("제목은 50자이내여야합니다.");
			$("#TITLE").focus();
			return false;	
		}
		
		if($("#USER_NM").val().length>30)
		{
			alert("작성자명은 30자이내여야합니다.");
			$("#USER_NM").focus();
			return false;	
		}
		
		return true;
	}
	
	
	</script>

