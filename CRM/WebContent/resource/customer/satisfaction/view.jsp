<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../../../Style/skin.css" />
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
                        <tr><td height="31"><div class="title">查看详情</div></td></tr>
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
                            <td colspan="4">
                               
                            </td>
                        </tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 展示详情开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="" method="">
                                                <table width="100%"class="cont">
                                                  <tr>
													<td width="2%">&nbsp;</td>
													<td>客户编码：</td>
													<td><s:property value="satisfaction_id"/></td>
													<td>客户类型：</td>
													<td><s:property value="customer.levelBaseDict.dict_item_name"/></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													<td width="2%">&nbsp;</td>
													<td>客户姓名：</td>
													<td><s:property value="customer.cust_name"/></td>
													<td>调查时间：</td>
													<td><s:date name="survey_date" format="yyyy-MM-dd"/></td>
													<td></td>
													<td width="2%">&nbsp;</td>
												</tr>
												<tr>
													 <td>&nbsp;</td>
													<td>消费次数：</td>
													<td><s:property value="consumer_times"></s:property></td>
													
													<td>客户状态：</td>
													<td>
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
													<tr>
														<td width="2%">&nbsp;</td>
														<td>服务满意度：</td>
														<td><s:property value="service_satisfaction"/></td>
														<td>总体满意度：</td>
														<td><s:property value="overall_satisfaction"/></td>
														<td></td>
														<td width="2%">&nbsp;</td>
													</tr>
														<tr>
														<td width="2%">&nbsp;</td>
														<td>质量满意度：</td>
														<td><s:property value="quality_satisfaction"/></td>
														<td>性价满意度：</td>
														<td><s:property value="price_satisfaction"/></td>
														<td></td>
														<td width="2%">&nbsp;</td>
													</tr>
													<tr>
														<td width="2%">&nbsp;</td>
														<td>客户反馈：</td>
														<td colspan="4"><s:property value="customer_feedback"></s:property></td>
														<td></td>
														<td width="2%">&nbsp;</td>
													</tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
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
                        <!-- 展示详情结束 -->
                        <tr>
                            <td height="40" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                    
                                </table>
                                
                            </td>
                        </tr>
                        <tr> 
                        	<td colspan="4" align="center">
								<a href="${pageContext.request.contextPath }/customerSatisfaction_listUI.action"><input class="btn" type="button" value="返回" /></a>
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
                    <img src="images/buttom_bgs.gif" width="17" height="17">
                </td>
                <td valign="bottom" background="images/mail_right_bg.gif">
                    <img src="images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr>
        </table>
    </body>
</html>