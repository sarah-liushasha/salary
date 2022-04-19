package com.lss.salary.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登录拦截
@WebFilter("/*")  //什么请求地址我都要拦截
public class LoginFilter extends HttpFilter implements Filter{

	private static final long serialVersionUID = 1L;
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest)request;
		String uri=req.getRequestURI();
		
		//获取当前请求的属于的会话
		HttpSession session=req.getSession();
		//只要session上存着东西说明之前登录过
		Boolean isLogin=session.getAttribute("current")!=null;
		//session
		
		//登录过忽略
		//  /login忽略
		//静态资源忽略  css js plugins img
		if (isLogin || "/login".equals(uri) || uri.startsWith("/css/") ||uri.startsWith("/js/")||uri.startsWith("/plugins/")||uri.startsWith("/img/")) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp=(HttpServletResponse) response;
			resp.sendRedirect("/login");
		}
	}

}
