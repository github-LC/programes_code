<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user LC
  Date: 2019/11/29
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>疯狂加载中....</title>
</head>
<body>
<form action="${p2p_pay_alipay_return_url}" method="post">

    <input type="hidden" name="signVerified" value="${signVerified}"/>
    <c:choose>
        <c:when test="${not empty params}">
            <c:forEach items="${params}" var="paramMap">
                <input type="hidden" name="${paramMap.key}" value="${paramMap.value}"/>
            </c:forEach>
        </c:when>
    </c:choose>
</form>
</body>
<script>
    document.forms[0].submit();
</script>
</html>
