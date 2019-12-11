<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script>
	$(function(){
		$("#resetInput").click(function(){
			$("#code").attr("value","");
			$("#password").attr("value","");
			$("#checkCode").attr("value",""); 
		})
	})
</script>
<style type="text/css">
  
    .code
    {
    font-family: Arial;
    font-style: italic;
    color: blue;
    
    cursor: pointer;
    width: 80px;
    border: 1px #fff solid;
    display: inline-block;
    
    text-align: center;
    vertical-align: middle;
    }
</style>
<title>客户关系管理系统</title>
<meta name="keywords" content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>

<!-- 记住登陆信息并且将登陆信息填充到登陆页面 -->
<%
    /* 	String user_code = "";
    	String user_password = "";
    	Cookie[] cookies = request.getCookies();
    	for(Cookie cookie:cookies){
    		if(cookie.getName().equals("userInfo")){
    			user_code = cookie.getValue().split("#")[0];
    			user_password = cookie.getValue().split("#")[1];
    			System.out.println(user_password);
    			//调用工具类将密码解密
    			user_password = com.lc.crm.utils.MD5Utils.convertMD5(user_password);
    			System.out.println(user_password);
    		}
    	} */
    %>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header">客户关系管理系统</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <span style="margin-left:150px;">
    	没有账号？
    	<a href="${pageContext.request.contextPath }/user_registUI.action" style="color:red;">前往注册
    	</a>
    	<h4 style="margin-left:150px;color:red;"><s:actionerror/></h4>
    </span>
    <form class="form form-horizontal" action="${ pageContext.request.contextPath }/user_login.action" method="post" target="_parent">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="code" name="user_code" type="text" placeholder="账户" class="input-text size-L" value="${cookie.userCode.value }"/>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="password" name="user_password" type="password" placeholder="密码" class="input-text size-L" value="${cookie.userPassword.value }"/>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <div class="code" id="checkCode" onclick="createCode()" ></div> <a id="kanbuq" onclick="createCode()">看不清，换一张</a> </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="checked" checked="checked">
            保存登录信息</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input type="reset" id="resetInput" class="btn btn-default radius size-L" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">客户关系管理系统</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="new-js/H-ui.js"></script> 
<script>
/* var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
          var code;
        function createCode() {
            code = "";
            var codeLength = 6; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 52);
                code += codeChars[charNum];
            }
            if (checkCode) 
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
            }
        }
        createCode();
        function validateCode() 
        {
            var inputCode = document.getElementById("inputCode").value;
            if (inputCode.length <= 0) 
            {
               // alert("请输入验证码！");
            }
            else if (inputCode.toUpperCase() != code.toUpperCase()) 
            {
                alert("验证码输入有误！");
                createCode();
            }
            else 
            {
                alert("验证码正确！");
            }        
        }    
     */

</script>
</body>
</html>