<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="Style/skin.css" />
    <link rel="stylesheet" typr="text/css" href="css/add.css" />
	<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
	
	<!-- 引入jquery -->
	<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	
	<script>
		$(function(){
			$.post("${pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"001"},function(data){
				//遍历获取元素
				$(data).each(function(i,n){
					$(".cust_industry").append("<option value="+n.dict_id+">"+n.dict_item_name+"</option>")
				});
			},"json");
			$.post("{pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"009"},function(data){
				//遍历获取元素
				$(data).each(function(i,m){
					$(".cust_source").append("<option value="+m.dict_id+">"+m.dict_item_name+"</option>")
				});
			},"json");
			$.post("{pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(data){
				//遍历获取元素
				$(data).each(function(i,k){
					$(".cust_level").append("<option value="+k.dict_id+">"+k.dict_item_name+"</option>")
				});
			},"json");
		});
	</script>
</head>
    <body>
    <form action="${pageContext.request.contextPath }/customer_add.action" method="post" enctype="multipart/form-data">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <!-- 头部开始 -->
            <tr>
                <td width="17" valign="top" background="images/mail_left_bg.gif">
                    <img src="images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="images/content_bg.gif">
                    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="images/content_bg.gif">
                        <tr><td height="31"><div class="title">客户添加</div></td></tr>
                    </table>
                </td>
                <td width="16" valign="top" background="images/mail_right_bg.gif"><img src="images/nav_right_bg.gif" width="16" height="29" /></td>
            </tr>
            <!-- 中间部分开始 -->
            <tr>
                <!--第一行左边框-->
                <td valign="middle" background="images/mail_left_bg.gif">&nbsp;</td>
                <!--第一行中间内容-->
                <td valign="top" bgcolor="#F7F8F9">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <!-- 空白行-->
                        <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                       <!-- 客户资源修改开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="${pageContext.request.contextPath }/customer_add.action" method="post">
                                                <table width="100%"class="cont">
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="20%" id="font">客户名称：</td>
                                                        <td width="65%"><input type="text" placeholder="限长，非空必填" class="input" name="cust_name"/></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">客户信息来源：</td>
                                                        <td width="65%">
                                                        	<select class="cust_source" style="margin-left: 55px;width:300px; height:40px;margin-top: 30px;border-radius: 7px;background-color: cadetblue; border-style: none;font-size:18px;"name="sourceBaseDict.dict_id">
                                                            	<option id="font">--请选择--</option>
                                                            </select>
                                                        </td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">客户所属行业：</td>
                                                        <td width="65%">
                                                        	<select class="cust_industry" style="margin-left: 55px;width:300px; height:40px;margin-top: 30px;border-radius: 7px;background-color: cadetblue; border-style: none;font-size:18px;" name="industryBaseDict.dict_id">
                                                            	<option id="font">--请选择--</option>
                                                            </select>
                                                          </td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">客户级别：</td>
                                                        <td width="65%">
                                                        	<select class="cust_level" style="margin-left: 55px;width:300px; height:40px;margin-top: 30px;border-radius: 7px;background-color: cadetblue; border-style: none;font-size:18px;" name="levelBaseDict.dict_id">
                                                            	<option id="font">--请选择--</option>
                                                            </select>
                                                        </td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">移动电话：</td>
                                                        <td width="65%"><input type="text" class="input" name="cust_mobile"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">固定电话：</td>
                                                        <td width="65%"><input  type="text" class="input" name="cust_phone"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">录入人:</td>
                                                        <td width="65%"><input type="text" class="input" name="cust_create_id"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">跟进人：</td>
                                                        <td width="65%"><input type="text" class="input" name="cust_user_id"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td>&nbsp;</td>
                                                        <td id="font" width="20%">联系人：</td>
                                                        <td width="65%"><input type="text" class="input" name="cust_linkman"></input></td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td id="font" width="20%">用户资质：</td>
                                                        <td width="65%"><input type="file" style="margin-left: 55px;font-size:18px;" name="upload"></input></td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td colspan="2" align="center">
															<input class="btn btn-success radius size-L" style="width:60px;height:40px;" type="submit" value="提交" />&nbsp;&nbsp;&nbsp;
															<input class="btn btn-default radius size-L" style="width:60px;height:40px;margin-left:200px;" onclick="location='list.html'" type="button" value="重置" />
														</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <!-- 客户资源修改结束 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="51%" class="left_txt">
                                
                            </td>
                            <td>&nbsp;</td><td>&nbsp;</td>
                        </tr>
                    </table>
                </td>


                <td background="images/mail_right_bg.gif">&nbsp;</td>
            </tr>
            <!-- 底部部分 -->
            <tr>
                <td valign="bottom" background="images/mail_left_bg.gif">
                    <img src="images/buttom_left.gif" width="17" height="17" />
                </td>
                <td background="images/buttom_bgs.gif">
                    <img src="images/buttom_bgs.gif" width="17" height="17"/>
                </td>
                <td valign="bottom" background="images/mail_right_bg.gif">
                    <img src="images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr>
        </table>
        </form>
    </body>
</html>