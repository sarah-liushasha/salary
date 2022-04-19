package com.lss.salary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lss.salary.service.IUserService;
import com.lss.salary.service.UserServiceImpl;

//登录servlet
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service=new UserServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用post请求校验用户名密码是否正确
		//用get请求打开页面
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		Boolean b=service.check(request,username,password);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(b.toString());
		
	}

}
