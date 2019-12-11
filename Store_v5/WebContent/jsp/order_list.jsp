<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

			<!--
            	描述：菜单栏 ，描述：轮播条
            -->
			<%@ include file="/jsp/header.jsp" %>

		

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<table class="table table-bordered">
					<strong>我的订单</strong>
					<c:forEach items="${page.list }" var="p">
						<tbody>
							<tr class="success">
								<th colspan="5">
								订单编号:${p.oid }
								&nbsp;&nbsp;&nbsp;总金额：￥${p.total }元
								&nbsp;&nbsp;&nbsp;<c:if test="${p.state==1 }"><a href="${pageContext.request.contextPath}/OrderServlet?method=findEveryOrderById&oid=${p.oid }">未付款</a></c:if>
								&nbsp;&nbsp;&nbsp;<c:if test="${p.state==2 }">已发货</c:if>
								&nbsp;&nbsp;&nbsp;<c:if test="${p.state==3 }">签收</c:if>
								&nbsp;&nbsp;&nbsp;<c:if test="${p.state==4 }">交易结束，请评价</c:if>
								</th>
								
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							
						<c:forEach items="${p.list }" var="orderItem">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${orderItem.product.pimage }" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank">${orderItem.product.pname } </a>
								</td>
								<td width="20%">
									￥${orderItem.product.shop_price }
								</td>
								<td width="10%">
									${orderItem.quantity }
								</td>
								<td width="15%">
									<span class="subtotal">￥${orderItem.total }</span>
								</td>
							</tr>
					</c:forEach>
						</tbody>
					</c:forEach>
				</table>
				<%@ include file="/jsp/pageFile.jsp" %>
				</div>
			</div>
		</div>

	<!--
                 描述：页脚部分
     -->
	<%@ include file="/jsp/footer.jsp" %>
	</body>

</html>