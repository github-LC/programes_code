package com.lc.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器，如果未登录就没有权限操作首页
 * @author user LC
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		//如果没有登录就限制操作然后跳转到登陆页面
		if(ServletActionContext.getRequest().getSession().getAttribute("loginUser")==null) {
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("你没有权限操作，请先登录！");
			return actionSupport.LOGIN;
		}
		
		//如果登录了就放行
		return invocation.invoke();
	}

}
