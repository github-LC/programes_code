<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 分页操作 -->
	<div style="width:500px;margin:0 auto;margin-top:50px;margin-left:500px;margin-bottom: 50px;">
			<span aria-hidden="true">第${page.currentPage }页/共${page.totalPage }页</span>
			<c:if test="${page.currentPage!=1 }">
			<a href="${pageContext.request.contextPath}/${page.url }&currentPage=1&cid=${page.productType }">
				<span aria-hidden="true">首页</span>
			</a>
			<a href="${pageContext.request.contextPath}/${page.url }&currentPage=${page.currentPage-1 }&cid=${page.productType }" aria-label="Previous">
				<span aria-hidden="true">上一页</span>
			</a>
			</c:if>
			<c:forEach begin="1" end="${page.totalPage }" var="p">
				<c:if test="${page.currentPage==p }">${p }</c:if>
				<c:if test="${page.currentPage!=p }"><a href="${pageContext.request.contextPath}/${page.url }&currentPage=${p }&cid=${page.productType }">${p }</a></c:if>
			</c:forEach>
			<a href="#">${totalPage }</a>
			<c:if test="${page.currentPage!=page.totalPage }">
			<a href="${pageContext.request.contextPath}/${page.url }&currentPage=${page.currentPage+1 }&cid=${page.productType }">
				<span aria-hidden="true">下一页</span>
			</a>
			<a href="${pageContext.request.contextPath}/${page.url }&currentPage=${page.totalPage }&cid=${page.productType }" aria-label="Previous">
				<span aria-hidden="true">尾页</span>
			</a>
			</c:if>
		</div>
</body>
</html>