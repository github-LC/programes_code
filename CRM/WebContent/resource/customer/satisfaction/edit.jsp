<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="Style/skin.css" />
</head>
    <body>
    	<form action="${pageContext.request.contextPath }/customerSatisfaction_edit.action" method="post">
    	<input type="hidden" value="<s:property value="customer.cust_id"/>" name="satisfaction_cust_id"/>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <!-- 头部开始 -->
            <tr>
                <td width="17" valign="top" background="images/mail_left_bg.gif">
                    <img src="images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="images/content_bg.gif">
                    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="images/content_bg.gif">
                        <tr><td height="31"><div class="title">客户满意度</div></td></tr>
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
                        <!-- 客户信息开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%" align="center">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
											<br/>
											<font style="font-size:13pt; font-weight:700">客户信息</font>
											<br/><br/>
											<table width="90%" class="cont">
												<tr>
													<td width="2%">&nbsp;</td>
													<td width="10%">客户编码：</td>
													<td width="25%"><input type="hidden" value="<s:property value="satisfaction_id"/>" name="satisfaction_id"/><s:property value="satisfaction_id"/></td>
													<td width="10%">客户类型：</td>
													<td width="25%"><s:property value="customer.levelBaseDict.dict_item_name"/></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>客户姓名：</td>
													<td><s:property value="customer.cust_name"/></td>
													<td>调查时间：</td>
													<td><input type="hidden" value="<s:property value="survey_date"/>" name="survey_date"/><s:date name="survey_date" format="yyyy-MM-dd"/></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													 <td width="2%">&nbsp;</td>
													<td width="10%">消费次数：</td>
													<td width="25%"><input type="hidden" value="<s:property value="consumer_times"/>" name="consumer_times"/><s:property value="consumer_times"></s:property></td>
													
													<td width="10%">客户状态：</td>
													<td width="25%">
														<input type="hidden" value="<s:property value="customer_state"/>" name="customer_state"/>
														<s:if test="customer_state==1">
															正常
														</s:if>
														<s:elseif test="customer_state==2">
															异常
														</s:elseif>
														
													</td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
											</table>
                                        </td>
                                    </tr>
								</table>
							</td>
						</tr>
						 <!-- 客户信息结束 -->
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
						<!-- 满意度调查开始 -->
						<tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%" align="center">
                                <table width="100%">
									<tr>
                                        <td colspan="2">
												<br/>
												<font style="font-size:13pt; font-weight:700">客户满意度调查</font>
												<br/><br/>
                                                <table width="90%"  class="cont tr_color">
                                                    <tr>
														<td width="2%">&nbsp;</td>
														<td width="13%">质量满意度：</td>
														<td width="25%"><input type="text" size="1" value="<s:property value="quality_satisfaction"/>" name="quality_satisfaction"/></td>
														<td width="13%">服务满意度：</td>
														<td width="25%"><input type="text" size="1" value="<s:property value="service_satisfaction"/>" name="service_satisfaction"/></td>
														<td></td>
														<td width="2%">&nbsp;</td>
													</tr>
													<tr>
														<td width="2%">&nbsp;</td>
														<td>性价满意度：</td>
														<td><input type="text" size="1" value="<s:property value="price_satisfaction"/>" name="price_satisfaction"/></td>
														<td>总体满意度：</td>
														<td><input type="text" size="1" value="<s:property value="overall_satisfaction"/>" name="overall_satisfaction"/></td>
														<td></td>
														<td width="2%">&nbsp;</td>
													</tr>
													<tr>
														<td width="2%">&nbsp;</td>
														<td>客户反馈：</td>
														<td colspan="4"><textarea style="width:700px;height:100px;" name="customer_feedback"><s:property value="customer_feedback"/></textarea></td>
														<td width="2%">&nbsp;</td>
													</tr>
                                                </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <!-- 满意度调查结束 -->
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
            <tr>
            	<td colspan="7" align="center">
					<input type="submit" value="提交调查"/>&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/customerSatisfaction_listUI.action"><input type="button" value="返回列表"/></a>
				</td>
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
        </form>
    </body>
</html>