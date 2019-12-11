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
                    <table style="width:100%" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <!-- 空白行-->
                        <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
						<tr>
							<td colspan="4">
								<form class="clearfix">
									<div style="float:left">
										<p style="float:left; margin-left:30px;"><label>客户姓名：</label><input class="text" style="width:80px;"/></p>&nbsp;&nbsp;&nbsp;
										<p style="float:left; margin-left:30px;"><input type="button" class="btn" value="查询"/></p>
									</div>
									<div style="float:right">
										<input type="button" onclick='window.location="add.html"' class="btn" value="添加"/> 
										<input type="button" class="btn" value="批量删除"/>										
									
									</div>
								</form>
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
                                            <form action="" method="">
                                                <table width="100%"  class="cont tr_color">
                                                    <tr>
                                                        <th>序号</th>
                                                        <th>选择</th>
                                                        <th>客户姓名</th>
                                                        <th>客户类型</th>
														<th>调查时间</th>
														<th>质量满意度</th>
                                                        <th>服务满意度</th>
                                                        <th>总体满意度</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    <s:iterator value="list">
                                                    <tr align="center" class="d">
                                                   		<td><s:property value="satisfaction_id"></s:property></td>
														<td><input type="checkbox" value="" /></td>
                                                        <td><a href="../resource/view.html"><s:property value="customer.cust_name"></s:property></a></td>
                                                        <td><s:property value="customer.levelBaseDict.dict_item_name"></s:property></td>
                                                        <td><s:date name="survey_date" format="yyyy-MM-dd"></s:date></td>
                                                        <td><s:property value="quality_satisfaction"></s:property></td>
														<td><s:property value="service_satisfaction"></s:property></td>
														<td><s:property value="overall_satisfaction"></s:property></td>
                                                        <td>
                                                        	<a href="${pageContext.request.contextPath }/customerSatisfaction_findToEdit.action?satisfaction_id=<s:property value="satisfaction_id"/>">修改</a>&nbsp;&nbsp;
															<a href="${pageContext.request.contextPath }/customerSatisfaction_delete.action?satisfaction_id=<s:property value="satisfaction_id"/>" onclick="javascript:confirm('确定删除吗？')">删除</a>&nbsp;&nbsp;
															<a href="${pageContext.request.contextPath }/customerSatisfaction_findInfo.action?satisfaction_id=<s:property value="satisfaction_id"/>">查看</a>
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
                           		<input type="button" value="全选" />&nbsp;&nbsp;&nbsp;
                                <input type="button" value="反选" />
                            	
                            
                            
                            <div style="float:right;"><b>
                                <s:if test="currentPage!=1">
                               	 	<a href="customerSatisfaction_pageFind.action?currentPage=1" />首页</a>&nbsp;&nbsp;&nbsp;
									<a href="customerSatisfaction_pageFind.action?currentPage=<s:property value="currentPage-1"/> " >上一页</a>&nbsp;&nbsp;&nbsp;
								</s:if>
								<s:iterator var="i" begin="1" end="pageTotal">
									<s:if test="#i==currentPage">
										<s:property value="#i"/>
									</s:if>
									<s:else>
										<a href="customerSatisfaction_pageFind.action?currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
									</s:else>
								</s:iterator>
								
							<%-- 	<!-- 设置每页的数量 -->
								<select name="pageSize">
									<option value="3"  <s:if test="pageSize == 3">selected</s:if>><a href="customer_pageFind?pageSize=3">3</a></option>
									<option value="5" <s:if test="pageSize == 5">selected</s:if>><a href="customer_pageFind?pageSize=5">5</a></option>
									<option value="10" <s:if test="pageSize == 10">selected</s:if>><a href="customer_pageFind?pageSize=10">10</a></option>
								</select> --%>
								
							 <s:if test="currentPage!=pageTotal">
								<a href="customerSatisfaction_pageFind.action?currentPage=<s:property value="currentPage+1" />" >下一页</a>&nbsp;&nbsp;&nbsp;
								<a href="customerSatisfaction_pageFind.action?currentPage=<s:property value="pageTotal" />" >尾页</a>&nbsp;&nbsp;&nbsp;
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