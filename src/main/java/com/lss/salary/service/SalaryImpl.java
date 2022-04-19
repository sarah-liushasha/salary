package com.lss.salary.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lss.salary.dao.DeptDao;
import com.lss.salary.dao.EmpDao;
import com.lss.salary.dao.SalaryDao;
import com.lss.salary.entity.Department;
import com.lss.salary.entity.Employee;
import com.lss.salary.entity.Salary;
import com.lss.salary.entity.User;
import com.lss.salary.vo.LayuiTableVO;

public class SalaryImpl implements ISalary{

	SalaryDao dao=new SalaryDao();
	EmpDao empDao=new EmpDao();
	DeptDao deptDao=new DeptDao();
	@Override
	public LayuiTableVO select(String keyword, Integer page, Integer limit) {
		LayuiTableVO vo=new LayuiTableVO();
		vo.setData(dao.selectList(keyword,page,limit));
		vo.setCount(dao.selectCount(keyword));
		return vo;
	}

	public void save(Salary salary,HttpServletRequest request) {
		salary.setCreatetime(LocalDateTime.now());
		//登录成功时我们在session中保存了当前登录人是谁
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("current");
		salary.setCreateby(user.getId());
		
		//先根据empId查出员工的所有数据
		Employee emp=empDao.selectById(salary.getEmpId());
		//根据员工的科室ID字段获取科室的详细数据
		Department dept=deptDao.selectById(emp.getDeptId());
		//到手工资：科室的基础工资+员工的绩效工资+这个月的奖金-员工的保险-员工的公积金
		BigDecimal sum=dept.getBasicSalary().add(emp.getPerformance());
		if(salary.getBonus()!=null) {
			sum=sum.add(salary.getBonus());
		}
		sum=sum.subtract(emp.getInsure()).subtract(emp.getFund());
		salary.setActual(sum);
		dao.insert(salary);
	}

	
}
