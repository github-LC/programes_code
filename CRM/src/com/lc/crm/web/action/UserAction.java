package com.lc.crm.web.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.http.Cookies;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lc.crm.domain.User;
import com.lc.crm.service.UserService;
import com.lc.crm.utils.MD5Utils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户action，负责处理用户模块
 * 
 * @author user LC
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserAction extends ActionSupport implements ModelDriven {
	// 注入业务层
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 封装提交的用户数据
	private User user = new User();

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	/**
	 * 跳转到用户注册界面
	 * 
	 * @return
	 */
	public String registUI() {
		return "registUI";
	}

	/**
	 * 完成用户注册功能，接受用户参数并进行持久化
	 * 
	 * @return
	 */
	public String regist() {
		userService.regist(user);
		return "regist";
	}

	public String login() throws UnsupportedEncodingException {
	
			//判断是否选中保存登录信息 如果是的话就添加cookie完成校验登陆
		   	//如果么有选中自动登陆就让cookie失效并完成登陆将用户信息保存到session中
			if("checked".equals(ServletActionContext.getRequest().getParameter("online"))){
				
				 //创建cookie
				 Cookie cookie1 = new Cookie("userCode",user.getUser_code());
				 Cookie cookie2 = new Cookie("userPassword",MD5Utils.convertMD5(MD5Utils.convertMD5(user.getUser_password())));
				
				//设置cookie的有效期
				cookie1.setMaxAge(60*60*24*3);
				cookie2.setMaxAge(60*60*24*3);
				
				//将cookie添加到响应中
				ServletActionContext.getResponse().addCookie(cookie1); 
				ServletActionContext.getResponse().addCookie(cookie2);
			}else {
				
				//让cookie失效
				Cookie[] cookies = ServletActionContext.getRequest().getCookies();
				if(cookies!=null) {
					for(Cookie cookie : cookies) {
						if("userCode".equals(cookie.getName()) || "userPassword".equals(cookie.getName())) {
							cookie.setMaxAge(0);
							ServletActionContext.getResponse().addCookie(cookie);
						}
					}
				}
			}
			
			// 查询用户是否存在
			List<User> loginUser = userService.login(user);
			if (loginUser.size() == 0) {
				
				// 返回错误信息
				this.addActionError("用户账号或密码错误，请重新输入");
				return LOGIN;
			}
				
			// 将用户信息保存在session中
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser.get(0));
			return SUCCESS;
		}
		

	/**
	 * 用户退出功能
	 * 
	 * @return
	 */
	public String logout() {

		ServletActionContext.getRequest().getSession().invalidate();
		
		//让cookie失效
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for(Cookie cookie : cookies) {
			if("userCode".equals(cookie.getName()) || "userPassword".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				ServletActionContext.getResponse().addCookie(cookie);
			}
		}
		
		return LOGIN;
	}

}
