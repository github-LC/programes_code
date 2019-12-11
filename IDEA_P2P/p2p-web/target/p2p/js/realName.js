/*//验证真实姓名
function checkRealName() {
	//真实姓名不能为空
	//真实姓名只支持中文)：[^\x00-\xff][\u4e00-\u9fa5]
	var realName = $.trim($("#realName").val());
	if("" == realName) {
		showError("realName","请输入真实姓名");
		return false;
	} else if(!/[^\x00-\xff]/.test(realName)) {
		showError("realName","真实姓名只支持中文");
		return false;
	} else {
		showSuccess("realName");
	}
	return true;
}

//验证身份证号码
function checkIdCard() {
	//身份证号码不能为空
	//符合身份证号码格式要求
	//身份证号码与确认身份号码得一致
	
	var idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());
	
	if("" == idCard) {
		showError("idCard","请输入身份证号码");
		return false;
	} else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard)) {
		showError("idCard","请输入正确的身份证号码");
		return false;
	} else {
		showSuccess("idCard");
	}
	
	if("" != idCard && "" != replayIdCard && replayIdCard == idCard) {
		showSuccess("idCard");
	} else {
		showError("replayIdCard","请输入确认身份证号码");
		return false;
	}
	return true;
}

//验证确认身份证号码
function checkIdCardEqu() {
	var idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());
	
	if("" == idCard) {
		showError("idCard","请输入身份证号码");
		return false;
	} else if("" == replayIdCard) {
		showError("replayIdCard","请输入确认身份证号码");
		return false;
	} else if(replayIdCard != idCard) {
		showError("replayIdCard","两次输入身份证号码不一致");
		return false;
	} else {
		showSuccess("replayIdCard");
	}
	return true;
}

//验证图形验证码
function checkCaptcha() {
	var captcha = $.trim($("#captcha").val());
	var flag = true;
	if("" == captcha) {
		showError("captcha","请输入图形验证码");
		return false;
	} else {
		//发送ajax请求
		$.ajax({
			url:"loan/checkCaptcha",
			type:"post",
			async:false,
			data:"captcha="+captcha,
			success:function (jsonObject) {
				if(jsonObject.errorMessage == "ok") {
					showSuccess("captcha");
					flag = true;
				} else {
					showError("captcha",jsonObject.errorMessage);
					flag = false;
				}
			},
			error:function() {
				showError("captcha","系统繁忙，请稍后重试...");
				flag = false;
			}
		});
	}
	
	if(!flag) {
		return false;
	}
	
	return true;
}

//实名认证
function verifyRealName() {
	//获取用户输入的表单内容
	var realName = $.trim($("#realName").val());
	var idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());
	var captcha = $.trim($("#captcha").val());
	
	if(checkRealName() && checkIdCard() && checkIdCardEqu() && checkCaptcha()) {
		$.ajax({
			url:"loan/verifyRealName",
			type:"post",
			data:{
				"realName":realName,
				"idCard":idCard,
				"replayIdCard":replayIdCard,
				"captcha":captcha
			},
			success:function(jsonObject) {
				if(jsonObject.errorMessage == "ok") {
					window.location.href = "index";
				} else {
					showError("captcha",jsonObject.errorMessage);
				}
			},
			error:function() {
				showError("captcha","系统繁忙，请稍后重试...");
			}
		});
	}
}*/

/*
 * 验证真实姓名
 */
function checkRealName(){

	var realName = $.trim($("#realName").val());
	if("" == realName){
		showError("realName","请输入你的真实姓名");
		return false;
	}else if(!/^[\u4e00-\u9fa5]{0,}$/.test(realName)){

		showError("realName","姓名只支持汉字");
		return false;
	}else{
		showSuccess("realName");
		return true;
	}
}

/*验证身份证号*/
function checkIdCard(){

	var  idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());

	if("" == idCard){
		showError("idCard","请输入身份证号");
		return false;

	}else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard)){
		showError("idCard","请输入正确的身份证号");
		return false;
	}else{
		showSuccess("idCard");
		return true;
	}

	if("" != replayIdCard && "" != idCard && replayIdCard==idCard){
		showSuccess("idCard");
		return true;
	}else{
		showError("idCard","请输入正确的身份证号");
		return false;
	}
}

/*验证再次输入身份证号*/
function  checkIdCardEqu() {

	var  idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());

	if("" == replayIdCard){
		showError("replayIdCard","请输入身份证号");
		return false;
	}else if(idCard.length<15||idCard.length>20){
		showError("replayIdCard","身份证号码的长度为15-20位");
		return false;
	}else if(idCard != replayIdCard){
		showError("replayIdCard","两次输入不一致，请重新输入");
		return false;
	}else if("" == idCard){
		showError("idCard","请输入身份证号");
		return false;
	}else{
		showSuccess("replayIdCard");
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
			url:"checkCaptcha",
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
				showError("captcha","系统繁忙，请稍后重试...")
			}
		});
	}
}


/*验证用户实名认证信息*/
function verifyRealName(){

	//获取用户输入的表单内容
	var realName = $.trim($("#realName").val());
	var idCard = $.trim($("#idCard").val());
	var replayIdCard = $.trim($("#replayIdCard").val());
	var captcha = $.trim($("#captcha").val());

	$.ajax({
		url:"checkVerifyRealName",
		data:{
			"realName":realName,
			"idCard":idCard,
			"replayIdCard":replayIdCard,
			"captcha":captcha
		},
		type:"post",
		async: false,
		success:function(jsonObject){
			if(jsonObject.errorMessage == "OK"){
				window.location.href = "index";
				return true;
			}else{
				showError("realName","身份证号码与真实姓名不一致");
			}
		},
		error:function(){
			showError("realName","系统繁忙，请稍后重试...");
			return false;
		}
	});
}

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

//成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


//同意实名认证协议
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});
});
//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}