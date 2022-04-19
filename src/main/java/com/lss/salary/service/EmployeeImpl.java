package com.lss.salary.service;

import java.util.List;

import com.lss.salary.dao.EmpDao;
import com.lss.salary.entity.Employee;
import com.lss.salary.vo.LayuiTableVO;

public class EmployeeImpl implements IEmployee{
	
	EmpDao dao=new EmpDao();	

	@Override
	public LayuiTableVO select(String keyword, Integer page, Integer limit) {
		LayuiTableVO vo = new LayuiTableVO();
		vo.setMsg("");
		vo.setCount(dao.selectCount(keyword));
		vo.setData(dao.selectList(keyword, page, limit));
		return vo;
	}

	@Override
	public void add(Employee emp) {
		if(emp.getId()==null) {
			dao.add(emp);
		}else {
			dao.update(emp);
			System.out.println(emp);
		}
	}

	@Override
	public void delete(Integer eid) {
		dao.delete(eid);
	}

	@Override
	public Employee selectById(Integer eid) {
		return dao.selectById(eid);
	}

	@Override
	public void update(Employee emp) {
		dao.update(emp);
		
	}

	@Override
	public List<Employee> selectAll(Integer did) {
		return dao.selectAll(did);
	}

	
}
