<%--
  Created by IntelliJ IDEA.
  User: user LC
  Date: 2019/12/8
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>手机客户端</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ingot.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nivo.slider.pack.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
    <style type="text/css">body{background:#f2f2f2;}</style>
</head>
<body>
<div><jsp:include page="commons/header.jsp"/></div>

<div>
    <div style="margin-left:41%;font-size: 17px;margin-top: 100px">扫一扫下载手机客户端</div>
   <img src="images/QRCode.jpg" style="margin-top:10px;margin-bottom: 100px;margin-left:40%"/>
</div>
<div><jsp:include page="commons/footer.jsp"/></div>
</body>
</html>
