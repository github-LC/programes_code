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
	
	<!-- 异步加载客户和负责人 -->
	<script>
		$(function(){
			$.post("${pageContext.request.contextPath}/saleVisit_findAllCustomer.action",{"":""},function(data){
				
				$(data).each(function(i,n){
					$(".cust_name").append("<option value="+n.cust_id+">"+n.cust_name+"<option/>");
				})
			},"json")
			
			$.post("${pageContext.request.contextPath}/saleVisit_findAllUser.action",{"":""},function(data){
				$(data).each(function(i,n){
					$(".user_name").append("<option value="+n.user_id+">"+n.user_name+"<option/>");
				})
			},"json")
		})
	</script>
</head>
    <body>
    <form action="${pageContext.request.contextPath }/saleVisit_add.action" method="post">
    	<input type="hidden" name="visit_id"></input>
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
                                                <table width="100%"class="cont">
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">客户名称：</td>
                                                        <td width="65%">
                                                        	<select class="cust_name" style="margin-left: 55px;width:300px; height:40px;margin-top: 30px;border-radius: 7px;background-color: cadetblue; border-style: none;font-size:18px;"name="customer.cust_id">
                                                            	<option id="font">--请选择--</option>
                                                            </select>
                                                        </td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">负责人：</td>
                                                        <td width="65%">
                                                        	<select class="user_name" style="margin-left: 55px;width:300px; height:40px;margin-top: 30px;border-radius: 7px;background-color: cadetblue; border-style: none;font-size:18px;" name="user.user_id">
                                                            	<option id="font">--请选择--</option>
                                                            </select>
                                                          </td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">拜访时间：</td>
                                                        <td width="65%"><input type="date" class="input" name="visit_time"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">拜访地点：</td>
                                                        <td width="65%"><input  type="text" class="input" name="visit_addr"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">拜访详情：</td>
                                                        <td width="65%"><input type="text" class="input" name="visit_detail"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
													<tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td id="font" width="20%">下次拜访时间：</td>
                                                        <td width="65%"><input type="date" class="input" name="visit_nexttime"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                        <td>&nbsp;</td>
                                                        <td colspan="2" align="center">
															<input class="btn btn-success radius size-L" style="width:60px;height:40px;" type="submit" value="提交" />&nbsp;&nbsp;&nbsp;
															<input class="btn btn-default radius size-L" style="width:60px;height:40px;margin-left:200px;" onclick="location='list.html'" type="button" value="重置" />
														</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                </table>
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