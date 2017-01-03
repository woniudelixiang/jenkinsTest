<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
	function paginationHandle(pageNum, pageSize){
		if(doNumCheck(pageNum)){
			$("#pageNum").val(pageNum);
		}
		
		if(doNumCheck(pageSize)){
			$("#pageSize").val(pageSize);
		}
		
		var $validateForm = $("#validateForm");
		if($validateForm){
			$validateForm.submit();
		}
	}
	
	function jumpPage(){
		var jumpPageValue = $("#jumpPageValue").val();
		if(!doNumCheck(jumpPageValue)){
			alert('请输入一个整数');
			return;
		}
		
		var totals = "${page.totalPages}";
		if(parseInt(jumpPageValue) > parseInt(totals)){
			alert('你所要跳转的页数不存在');
			return;
		}
		
		$("#pageNum").val(jumpPageValue);
		var $validateForm = $("#validateForm");
		if($validateForm){
			$validateForm.submit();
		}
	}
	
	function doNumCheck(num) {
		var regs = /^[0-9]+$/;
		return regs.test(num);
	};
	
</script>

<%-- <c:if test="${page.totalPages > 0}"> --%>
	<div class="search" style="/*margin: 10px 1px;*/ padding: 5px 0px;">
		
		<span style="margin-left: 5px;">
			总记录${page.totalCount}条
			第${page.pageNum}页/共${page.totalPages}页
		</span>
		
		<span>
			每页显示
			<select name="pageSize" style="height:25px; line-height: 20px;width: 55px;margin:3px;" onchange='paginationHandle("${page.pageNum}", this.value)'>
				<option ${page.pageSize == '10'? 'selected': ''}>10</option>
				<option ${page.pageSize == '20'? 'selected': ''}>20</option>
				<option ${page.pageSize == '50'? 'selected': ''}>50</option>
				<option ${page.pageSize == '100'? 'selected': ''}>100</option>
			</select>
			条记录
		</span>
		
		<span style="float:right;margin:5px;">
			<c:if test="${page.totalPages > 1}">
				<c:if test="${page.pageNum == 1}">
					[<span>首页</span> |
					<span>上一页</span> |
					<a onclick='return paginationHandle("${page.pageNum+1}");' style="cursor: pointer;">下一页</a> |
					<a onclick='return paginationHandle("${page.totalPages}");' style="cursor: pointer;">末页</a>]
				</c:if>
				<c:if test="${page.pageNum > 1 and page.pageNum < page.totalPages}">
					[<a style="cursor: pointer;" onclick='return paginationHandle("${a}1");'>首页</a> |
					<a style="cursor: pointer;" onclick='return paginationHandle("${page.pageNum-1}");'>上一页</a> |
					<a style="cursor: pointer;" onclick='return paginationHandle("${page.pageNum+1}");'>下一页</a> |
					<a style="cursor: pointer;" onclick='return paginationHandle("${page.totalPages}");'>末页</a>]
				</c:if>
				<c:if test="${page.pageNum == page.totalPages}">
					[<a style="cursor: pointer;" onclick='return paginationHandle("${a}1");'>首页</a> |
					<a style="cursor: pointer;" onclick='return paginationHandle("${page.pageNum-1}");'>上一页</a> |
					<span>下一页</span> |
					<span>末页</span>]
				</c:if>	
			</c:if>
			
			<c:if test="${page.totalPages == 1}">
				[<span>首页</span> |
				<span>上一页</span> |
				<span>下一页</span> |
				<span>末页</span>]
			</c:if>
		
			<span>
				<input id="jumpPageValue" value="${page.pageNum}" type="text" style="width:20px;margin-left: 5px;"/>
				<span style="cursor: pointer;" onclick="jumpPage()">Go</span>
			</span>
			
		</span>
		
	</div>
	
	<input id="pageSize" name="pageSize" value="${page.pageSize}" type="hidden" />
	<input id="pageNum" name="pageNum" value="${page.pageNum}" type="text" />
	<input id="totalCount" name="totalCount" value="${page.totalCount}" type="text" />
	
<%-- </c:if> --%>


<input id="order" name="order" value="${page.order}" type="hidden" />
<input id="orderBy" name="orderBy" value="${page.orderBy}" type="hidden" />
<input id="className" name="className" value="${page.className}" type="hidden" />