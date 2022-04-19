package com.lss.salary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lss.salary.entity.Employee;
import com.lss.salary.service.EmployeeImpl;
import com.lss.salary.service.IEmployee;
import com.lss.salary.vo.LayuiTableVO;

//员工servlet
@WebServlet("/emp")
public class EmpServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	IEmployee service=new EmployeeImpl();
	
	//转到主页面
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/emp/index.jsp").forward(request,response);
	}
	
	//显示所有用户
	public LayuiTableVO list(String keyword,String page,String limit) throws IOException {
		return service.select(keyword,Integer.parseInt(page),Integer.parseInt(limit));
	}
	

	public void del(Integer eid) {
		service.delete(eid);
	}

	public void insert(Employee emp) throws IOException {
		service.add(emp);
	}
	
	public Employee get(Integer eid) throws IOException {
		return service.selectById(eid);
	}
	
	public List<Employee> selectAll(Integer deptId) throws IOException {
		return service.selectAll(deptId);
		
	}
	

}
