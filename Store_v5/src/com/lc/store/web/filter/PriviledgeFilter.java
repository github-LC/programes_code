package com.lc.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.lc.store.domain.User;

/**
 * 权限过滤器：可以配置多个路径进行过滤
 * @author user LC
 *
 */
public class PriviledgeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PriviledgeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		//判断用户是否登陆，若没有登陆就过滤掉
		HttpServletRequest req = (HttpServletRequest)request;
		User user = (User)req.getSession().getAttribute("userInfo");
		if(null!=user) {
			chain.doFilter(request, response);
		}else {
			req.setAttribute("msg","用户还未登陆，请登录");
			req.getRequestDispatcher("info.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
