
var referrer = "";//登录后返回页面
referrer = document.referrer;
if (!referrer) {
	try {
		if (window.opener) {                
			// IE下如果跨域则抛出权限异常，Safari和Chrome下window.opener.location没有任何属性              
			referrer = window.opener.location.href;
		}  
	} catch (e) {
	}
}

//按键盘Enter键即可登录
$(document).keyup(function(event){
	if(event.keyCode == 13){
		login();
	}
});
//输入域失去焦点，将红色提示信息清除
/*$(function(){
	//获取平台基本信息
	loadStat();
});

//获取平台基本信息
function loadStat() {
	$.ajax({
		url:"loan/loadStat",
		type:"get",
		success:function(jsonObject) {
			$("#historyAverageRate").html(jsonObject.historyAverageRate);
			$("#allUserCount").html(jsonObject.allUserCount);
			$("#allBidMoney").html(jsonObject.allBidMoney);
		}
	});
}*/

/*
//验证手机号
function checkPhone() {
	//获取用户输入的手机号
	var phone = $.trim($("#phone").val());
	if("" == phone) {
		$("#showId").html("");
		$("#showId").html("请输入手机号");
		return false;
	} else if(!/^1[1-9]\d{9}$/.test(phone)) {
		$("#showId").html("");
		$("#showId").html("请输入正确的手机号");
		return false;
	} else {
		$("#showId").html("");
	}
	return true;
}

//验证登录密码
function checkLoginPassword() {
	//获取用户输入的登录密码
	var loginPassword = $.trim($("#loginPassword").val());
	if("" == loginPassword) {
		$("#showId").html("");
		$("#showId").html("请输入登录密码");
		return false;
	} else {
		$("#showId").html("");
	}
	return true;
}

//验证图形验证码
function checkCaptcha() {
	var flag = true;
	//获取用户输入图形验证码
	var captcha = $.trim($("#captcha").val());
	if("" == captcha) {
		$("#showId").html("");
		$("#showId").html("请输入图形验证码");
		return false;
	} else {
		$.ajax({
			url:"loan/checkCaptcha",
			type:"post",
			async:false,
			data:"captcha="+captcha,
			success:function(jsonObject) {
				if(jsonObject.errorMessage == "ok") {
					$("#showId").html("");
					flag = true;
				} else {
					$("#showId").html("");
					$("#showId").html(jsonObject.errorMessage);
					flag = false;
				}
			},
			error:function() {
				$("#showId").html("系统繁忙，请稍后重试...");
				flag = false;
			}
		});
	}
	
	if(!flag) {
		return false;
	}
	return true;
}

//用户登录
function login() {
	//获取用户输入表单内容
	var phone = $.trim($("#phone").val());
	var loginPassword = $.trim($("#loginPassword").val());
	var captcha = $.trim($("#captcha").val());
	
	if(checkPhone() && checkLoginPassword() && checkCaptcha()) {
		$("#loginPassword").val($.md5(loginPassword));
		
		$.ajax({
			url:"loan/login",
			type:"post",
			data:{
				"phone":phone,
				"loginPassword":$.trim($("#loginPassword").val()),
				"captcha":captcha
			},
			success:function(jsonObject) {
				if(jsonObject.errorMessage == "ok") {
					//登录成功
					if("" == referrer) {
						window.location.href = "index";
					} else {
						window.location.href = referrer;
					}
				} else {
					//登录失败
					$("#showId").html("");
					$("#showId").html(jsonObject.errorMessage);
				}
			},
			error:function() {
				$("#showId").html("");
				$("#showId").html("系统繁忙，请稍后重试...");
			}
		});
	}
}
*!/
*/


/*验证手机号*/
function checkPhone(){
	/*用户输入手机号必须进行验证
	a)  手机号不能为空
	b)  手机号格式
	c)  手机号是否已被注册*/
	//获取输入值
	var phone = $.trim($("#phone").val());
	if (phone == "") {
		showError("phone", "请输入手机号");
		return false;
	} else if (!/^1[1-9]\d{9}$/.test(phone)) {
		showError("phone", "请输入正确的手机号码");
		return false;
	} else if (phone.length != 11) {
		showError("phone", "请输入正确的手机号码");
		return false;
	}else{
		showSuccess("phone");
		return true;
	}
}


//验证登录密码
function checkLoginPassword() {
	/*密码验证格式：
	a)  密码不能为空
	b)  密码字符只可使用数字和大小写英文字母
	c)  密码应同时包含英文或数字
	d)  密码应为6-16位
	e)  两次输入密码必须一致
	*/

//获取用户输入的登录密码
	var loginPassword = $.trim($("#loginPassword").val());

	if ("" == loginPassword) {
		showError("loginPassword", "请输入登录密码");
		return false;
	} else if (!/^[0-9a-zA-Z]+$/.test(loginPassword)) {
		showError("loginPassword", "密码字符只可使用数字和大小写英文字母");
		return false;
	} else if (!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(loginPassword)) {
		showError("loginPassword", "密码应同时包含英文或数字 ");
		return false;
	} else if (loginPassword.length < 6 || loginPassword.length > 16) {
		showError("loginPassword", "密码长度应为6-16位");
		return false;
	} else {
		showSuccess("loginPassword");
		return true;
	}

}

/*验证验证码*/
function checkCaptcha(){
	var captcha = $.trim($("#captcha").val());

	if("" == captcha){
		showError("captcha","请输入验证码");
		return false;
	}else{
		$.ajax({
			url:"loan/checkCaptcha",
			data:"captcha="+captcha,
			type:"post",
			async:false,
			success:function(jsonObject){
				if(jsonObject.errorMessage == "OK"){
					showSuccess("captcha");
					return true;
				}else{
					showError("captcha",jsonObject.errorMessage);
					//清空验证码
					$("#captcha").val(null);
					return false;
				}
			},
			error:function(){
				showError("captcha","系统繁忙，请稍后重试...");
				return false;
			}
		});
	}
}
/*用户登陆*/
function login() {

	//获取用户输入表单内容
	var phone = $.trim($("#phone").val());
	var loginPassword = $.trim($("#loginPassword").val());
	var captcha = $.trim($("#captcha").val());

	//对密码进行加密
	$("#loginPassword").val($.md5(loginPassword));
	$.ajax({
		url: "loan/login",
		type: "post",
		data: {
			"phone": phone,
			"loginPassword": $.trim($("#loginPassword").val()),
			"captcha": captcha
		},
		success: function (jsonObject) {
			if (jsonObject.errorMessage == "OK") {
				if ("" == referrer) {
					window.location.href = "index";
				} else {
					window.location.href = referrer;
				}
			} else {
				showError("phone", "登陆失败,手机号或者密码输入错误...")
			}
			},
		error: function () {
			showError("phone", "系统错误，请稍后重试")
		}
	})
}

/*页面加载的时候去查询历史年化收益率和平台用户数和累计成交额*/
$(function(){
    $.ajax({
        url:"loan/loadStat",
        type:"post",
        success:function(jsonObject){

             $("#historyAverageRate").html(jsonObject.historyAverageRate);
             $("#allUserCount").html(jsonObject.allUserCount);
             $("#allBidMoney").html(jsonObject.allBidMoney);
        },
        error:function(){
            alert("请求失败")
        }
    })
});

//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}