<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 菜单栏部分 -->
<div class="container-fluid">
	<div class="col-md-4">
		<h1>校园淘淘旧</h1>
	</div>
	<div class="col-md-5">
		<img src="${pageContext.request.contextPath}/img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
				<!-- 判断session中有没有值,有值就显示欢迎xx登陆 -->
				<c:if test="${empty userInfo }">
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
							
				</c:if>
				<c:if test="${not empty userInfo }">
					<li>欢迎  &nbsp;&nbsp;&nbsp;${userInfo.username }</li>
					&nbsp;&nbsp;
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=logout" style="color:red;">退出</a></li>
					<li><a href="${pageContext.request.contextPath }/CartServlet?method=jumpToCart">购物车</a></li>
					<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findOrderById&currentPage=1">我的订单</a></li>
				</c:if>
						
		</ol>		
	</div>
</div>
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="IndexServlet">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="category">
								<%-- <!-- 遍历取出request中的信息 -->
								<c:forEach items="${list }" var="c">
									<li class="active"><a href="#">${c.cname }</a></li>
								</c:forEach> --%>
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>	
</body>
		<script>
			$(function(){
				var path = "CategoryServlet";
				var obj = {"method":"getAllCats"};
				$.post(path,obj,function(data){
					
					//遍历取出list中的数据
					$.each(data,function(i,obj){
						var li = "<li><a href='${pageContext.request.contextPath}/ProductServlet?method=product_list&cid="+obj.cid+"&currentPage=1'>"+obj.cname+"</a></li>"
						$("#category").append(li);
					});
				},"json");
			});
		</script>		
</html>