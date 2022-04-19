package com.lss.salary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lss.salary.entity.Salary;
import com.lss.salary.service.ISalary;
import com.lss.salary.service.SalaryImpl;
import com.lss.salary.vo.LayuiTableVO;


@WebServlet("/salary")
public class SalaryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	ISalary service=new SalaryImpl();
	//转到主页面
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/salary/index.jsp").forward(request,response);
	}
	
	//显示所有用户
	public LayuiTableVO list(String keyword,String page,String limit) throws IOException {
		return service.select(keyword,Integer.parseInt(page),Integer.parseInt(limit));
	}
	
	public void save(Salary salary,HttpServletRequest request) {
		service.save(salary,request);
	}

}
