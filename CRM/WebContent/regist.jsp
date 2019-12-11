<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <!--引入外部的css和js文件-->
    <link rel="stylesheet" href="css/regist.css">
</head>
<body>
<div id="background">
    <div id="left">
        <div id="sims">
            C
            <br>
            R
            <br>
            M
        </div>
    </div>
    
    <form method="post" action="${ pageContext.request.contextPath }/user_regist.action">
        <div id="right">
            <div id="font">用户注册</div>
            <label>
                <input type="text" placeholder="用户账号" class="input" name="user_code"/>
                <br/>
                <input type="password" placeholder="用户密码" class="input" name="user_password"/>
                <br/>
                <input type="tel" placeholder="用户名" class="input" name="user_name"/>
                <br/>
                <input type="email" placeholder="用户邮箱" class="input" name="user_email"/>
                <br/>
                <input type="submit" value="注册" id="btn"/>
            </label>
        </div>
    </form>
</div>
</body>
</html>