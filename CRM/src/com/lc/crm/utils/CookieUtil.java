package com.lc.crm.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.lc.crm.dao.UserDao;
import com.lc.crm.dao.impl.UserDaoImpl;
import com.lc.crm.domain.User;

public class CookieUtil {
	
	/**
	 * 获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Boolean getCookie(HttpServletRequest request,String name){
		//判断是否为第一次登陆
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(name.equals(cookie.getName())){
					if(cookie!=null) {
						//不是第一次登陆，取出cookie中的值
						User newUser = new User();
						String value = cookie.getValue();
						newUser.setUser_code(value.split("#")[0]);
						newUser.setUser_password(value.split("#")[1]);
						
						//若用户存在
						if(newUser!=null) {
							//将用户信息存进session中
							request.getSession().setAttribute("loginUser",newUser);
						    return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	//添加不cookie
	public static Cookie addCookie(User user) {
		Cookie cookie = new Cookie("autoLogin",user.getUser_code()+"#"+user.getUser_password());
		cookie.setMaxAge(60*60*24*3);
		return cookie;
	}
}
