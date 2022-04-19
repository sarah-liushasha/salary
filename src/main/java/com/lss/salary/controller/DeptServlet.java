package com.lss.salary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lss.salary.entity.Department;
import com.lss.salary.service.DeptServiceImpl;
import com.lss.salary.service.IDeptService;
import com.lss.salary.vo.LayuiTableVO;

//部门servlet															
@WebServlet("/dept")
public class DeptServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	IDeptService service=new DeptServiceImpl();

	//转到主页面
	public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/dept/index.jsp").forward(request,response);
	}
	
	//显示所有用户
	public LayuiTableVO list(String keyword,String page,String limit) throws IOException {
		 return service.select(keyword,Integer.parseInt(page),Integer.parseInt(limit));
	}
	

	public void del(Integer did) {
		service.delete(did);
		
	}

	public void insert(Department dept) throws IOException {
		service.add(dept);
	}
	
	public Department get(Integer did) throws IOException {
		return service.selectById(did);
	} 
	
	public List<Department> all() throws IOException {
		 return service.getAll();
	}
}
