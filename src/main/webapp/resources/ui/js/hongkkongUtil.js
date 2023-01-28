function gfn_service(param, url)
{
	var result = null;
	$.ajax(
	{
		url : url,
		type : 'GET',
		data : param,
		dataType : 'json',
		timeout : 3800,
		cache : false,
		async : false,
		success : function(data)
		{
			result = data;
		},
		error : function(errorResult)
		{
			alert(errorResult.status + "\n" + errorResult.responseText);
		}
	});
	return result;
}

function gfn_postService(param,url)
{
	var result = null;
	$.ajax(
	{
		url : url,
		type : 'POST',
		data : param,
		dataType : 'json',
		timeout : 3800,
		cache : false,
		async : false,
		success : function(data)
		{
			result = data; 
		},
		error : function(errorResult)
		{
			alert(errorResult.status + "\n" + errorResult.responseText);
		}
	});
	return result;
}

function gfn_numberFormat(n)
{
	var reg = /(^[+-]?\d+)(\d{3})/; // 정규식
	n += ''; // 숫자를 문자열로 변환
	while (reg.test(n))
		n = n.replace(reg, '$1' + ',' + '$2');
	return n;
}

function gfn_setNumber(ls_num) 
{
    return ls_num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function gfn_toNumber(ls_num)
{
	return Number(ls_num.replace(/\,/gi, ""));
}

function lfn_service(lv_pageNum, lv_pageView, lv_visiblePages, lv_totalCnt, lv_pageArea, lv_paramStr, lv_url ,lv_fnName)
{
	var lv_limitPage =Math.ceil(lv_totalCnt/lv_pageView);
	var lo_obj={};
	
	$("#"+lv_pageArea).twbsPagination({
        totalPages: lv_limitPage,
        visiblePages: lv_visiblePages,
        startPage: lv_pageNum,
        onPageClick: function (event, page) 
        {
        	var param ={
        				 PAGE_NUM : page
        				,MENU_CD  : lv_paramStr
        				,BOARD_SEQ  : lv_paramStr
        				};
        	
        	lo_obj=gfn_service(param, lv_url);
        	
        	eval(lv_fnName+"(lo_obj)");
        }
    });
}

function lfn_searchService(lv_pageNum, lv_pageView, lv_visiblePages, lv_totalCnt, lv_pageArea, lv_params, lv_url ,lv_fnName)
{
//	alert("1::>"+lv_pageNum +" 2::>"+ lv_pageView +" 3::>"+ lv_visiblePages+" 4::>"+ lv_totalCnt+" 5::>"+ lv_pageArea+" 6::>"+ lv_params+" 7::>"+ lv_url +" 8::>"+lv_fnName)
//	alert("1::>"+lv_params.TITLE +" 2::>"+ lv_params.CONTENT_DESC +" 3::>"+ lv_params.COMMENT_YN+" 4::>"+ lv_params.USER_NM)
	
	var lv_limitPage =Math.ceil(lv_totalCnt/lv_pageView);
	var lo_obj={};
	
	$("#"+lv_pageArea).twbsPagination({
        totalPages: lv_limitPage,
        visiblePages: lv_visiblePages,
        startPage: lv_pageNum,
        onPageClick: function (event, page) 
        {
        	var param ={
				TITLE        :lv_params.TITLE
				,CONTENT_DESC:lv_params.CONTENT_DESC
				,COMMENT_YN  :lv_params.COMMENT_YN
				,USER_NM     :lv_params.USER_NM
				,PAGE_NUM    :page
			   };
			   
        	lo_obj=gfn_service(param, lv_url);
        	
        	eval(lv_fnName+"(lo_obj)");
        }
    });
}

