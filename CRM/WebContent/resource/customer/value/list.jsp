<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="Style/skin.css" />	    
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
	<script>
		$(function(){
			$.post("${pageContext.request.contextPath}/saleVisit_findAllUser.action",{"":""},function(data){
				alert(data);
				$(data).each(function(i,n){
					$("#user_name").append("<option value="+n.user_id+">"+n.user_name+"</option>")
				});
			},"json");
		});
	</script>    
    
</head>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <!-- 头部开始 -->
            <tr>
                <td width="17" valign="top" background="images/mail_left_bg.gif">
                    <img src="images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="images/content_bg.gif">
                    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="images/content_bg.gif">
                        <tr><td height="31"><div class="title">客户拜访</div></td></tr>
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
                    <table style="width:100%" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <!-- 空白行-->
                        <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
						<tr>
							<td colspan="4">
								<form class="clearfix" action="${pageContext.request.contextPath}/saleVisit_detachedCriteriaFind.action" mehtod="post">
									<div style="float:left">
										<p style="float:left; margin-left:30px;"><label>客户姓名：</label><input class="text" style="width:80px;"name="customer.cust_id"/></p>&nbsp;&nbsp;&nbsp;
										<p style="float:left; margin-left:30px;"><label>负责人：</label>
										<select id="user_name" name="user.user_id"></select>
										</p>&nbsp;&nbsp;&nbsp;
										<p style="float:left; margin-left:30px;"><input type="submit" class="btn" value="查询"/></p>
									</div>
									<div style="float:right">
									</div>
								</form>
								
								<a href="${pageContext.request.contextPath }/saleVisit_addUI.action"><input type="button" class="btn" value="新增拜访" style="margin-left:450px;float:left;"></input></a>
							<td>
						</tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 列表展示开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="">
                                                <table width="100%"  class="cont tr_color">
                                                    <tr>
                                                        <th>客户</th>
                                                        <th>负责人</th>
                                                        <th>拜访时间</th>
														<th>拜访地点</th>
														<th>拜访详情</th>
                                                        <th>下次拜访时间</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    
                                                    <!-- 遍历获取值 -->
                                                    <s:iterator value="list">
                                                    <tr align="center" class="d">
                                                        <td><a href="../resource/view.html"><s:property value="customer.cust_name"/></a></td>
                                                        <td><s:property value="user.user_name"/></td>
                                                        <td><s:date name="visit_time" format="yyyy-MM-dd"/></td>
                                                        <td><s:property value="visit_addr"/></td>
                                                        <td><s:property value="visit_detail"/></td>
														<td><s:date name="visit_nexttime" format="yyyy-MM-dd"/></td>
                                                        <td>
															<a href="${pageContext.request.contextPath }/saleVisit_delete.action?visit_id=<s:property value="visit_id"/>">删除</a>
														</td>
                                                    </tr>
                                                    </s:iterator>
                                                </table>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <!--列表展示结束 -->
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
                                    <div style="float:right;"><b>
                                <s:if test="currentPage!=1">
                               	 	<a href="saleVisit_findAll.action?currentPage=1" />首页</a>&nbsp;&nbsp;&nbsp;
									<a href="saleVisit_findAll.action?currentPage=<s:property value="currentPage-1"/> " >上一页</a>&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:iterator var="i" begin="1" end="pageTotal">
									<s:if test="#i==currentPage">
										<s:property value="#i"/>
									</s:if>
									<s:else>
										<a href="saleVisit_findAll.action?currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
									</s:else>
								</s:iterator>
								
								
							 <s:if test="currentPage!=pageTotal">
								<a href="saleVisit_findAll.action?currentPage=<s:property value="currentPage+1" />" >下一页</a>&nbsp;&nbsp;&nbsp;
								<a href="saleVisit_findAll.action?currentPage=<s:property value="pageTotal" />" >尾页</a>&nbsp;&nbsp;&nbsp;
							</s:if>
								每页显示<s:property value="pageSize"/>条&nbsp;&nbsp;&nbsp;
								当前 <s:property value="currentPage"/>/<s:property value="pageTotal"/>页&nbsp;&nbsp;&nbsp;
								共<s:property value="totalCount"/>条
                            </b></div>
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
                    <img src="images/buttom_bgs.gif" width="17" height="17">
                </td>
                <td valign="bottom" background="images/mail_right_bg.gif">
                    <img src="images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr>
        </table>
    </body>
</html>