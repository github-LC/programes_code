package com.lc.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.User;
import com.lc.store.service.UserService;
import com.lc.store.service.impl.UserServiceImpl;
import com.lc.store.utils.MailUtils;
import com.lc.store.utils.MyBeanUtils;
import com.lc.store.utils.UUIDUtils;
import com.lc.store.web.base.BaseServlet;

/**
 * 实现用户功能模块
 * @author user LC
 *
 */
public class UserServlet extends BaseServlet {
	
	//约定让客户端通过servlet访问jsp而不是从客户端直接访问jsp
	public String registUI(HttpServletRequest request, HttpServletResponse response) {
		//踩坑记录：之前的方法registUI()当中没有添加参数报错反射找不到该方法，添加上这两个参数后正常运行
		return "/jsp/register.jsp";
	}
	
	/**
	 * 获取客户端的传送的注册信息
	 * 将注册信息添加到user中
	 * 调用业务层和dao层的功能将注册信息写入到数据库中
	 * 将数据库是否写入成功的状态返回到info.jsp中
	 * @param req
	 * @param resp
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) {
		
		//调用工具类写入保存用户注册信息
		User user = new User();
		MyBeanUtils.populate(user,request.getParameterMap());
		
		//将注册信息中没有的信息补上
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);
		//调用业务层的功能
		UserService service = new UserServiceImpl();
		try {
			service.regist(user);
			
			//写入成功跳转到另一个页面提示
			//注册成功给客户端发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg","注册成功，请激活");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg","注册失败，请重试");
		}
		
		//跳转到提示页面
		return "/jsp/info.jsp";
	}
	
	/**
	 * 激活用户
	 * 首先获取激活码
	 * 调用service和dao层的功能将激活码传递校验
	 * 校验成功就修改用户状态并清空激活码并跳转到用户界面
	 * 校验失败跳转到info.jsp 页面
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		//获取激活码
		String code = request.getParameter("code");
	
		//调用业务层和dao层功能
		User user = new User();
		UserService service = new UserServiceImpl();
		Boolean flag = service.userActive(code);
		
		//判断激活码是否存在，存在就激活成功，否则失败
		if(flag==false) {
			//提示激活失败
			request.setAttribute("msg","用户激活失败，请重试");
			return "/jsp/info.jsp";
		}else{
			//激活成功跳转到登陆界面 
			request.setAttribute("msg","用户激活成功，请登陆");
			return "/jsp/login.jsp";
		}
	}
	
	/**
	 * 用户登陆界面
	 * @param request
	 * @param response
	 * @return
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		
		return "/jsp/login.jsp";
		
	}
	
	/**
	 * 用户登陆验证功能
	 * 获取用户登陆信息
	 * 调用业务层和dao层的功能验证用户是否存在
	 * 用户存在就跳转到登陆页面并显示用户信息
	 * 如果用户不存在就跳转到info.jsp提示登陆失败
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		//获取用户登陆信息并保存到user中
		User user = new User();
		MyBeanUtils.populate(user,request.getParameterMap());
		
		//调用业务层和dao层的功能
		UserService service = new UserServiceImpl();
		
		//判断用户是否存在
		User uu = null;
		try {
			uu = service.userLogin(user);
			//登陆成功将信息保存在session中传递
			request.getSession().setAttribute("userInfo",uu);
			//重定向
			response.sendRedirect("index.jsp");
			return null;
		}catch(Exception e){
			request.setAttribute("msg",e.getMessage());
			return "/jsp/login.jsp";
		}
	}
	
	/**
	 * 用户退出功能
	 * 退出：让session失效然后重定向到首页
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.getSession().invalidate();
		
		//重定向到index.jsp
		response.sendRedirect("index.jsp");
		return null;
	}
}
