package com.lss.salary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lss.salary.entity.User;
import com.lss.salary.service.IUserService;
import com.lss.salary.service.UserServiceImpl;
import com.lss.salary.vo.LayuiTableVO;


@WebServlet("/user")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	IUserService service=new UserServiceImpl();

	//转到主页面
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/index.jsp").forward(request,response);
	}
	
	//显示所有用户
	public LayuiTableVO list(String keyword,String page,String limit) throws IOException {
		return service.select(keyword,Integer.parseInt(page),Integer.parseInt(limit));
	}

	public void del(Integer uid) {
		service.delete(uid);
	}

	public void insert(User user) throws IOException {
		service.add(user);
	}
	
	public User get(Integer uid, HttpServletResponse response) throws IOException {
		return service.selectById(uid);
	}

	

}
