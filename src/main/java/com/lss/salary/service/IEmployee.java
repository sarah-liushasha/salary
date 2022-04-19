package com.lss.salary.service;

import java.util.List;

import com.lss.salary.entity.Employee;
import com.lss.salary.vo.LayuiTableVO;

public interface IEmployee {
	LayuiTableVO select(String keyword,Integer page,Integer limit);
	void add(Employee emp);
	void delete(Integer eid);
	Employee selectById(Integer eid);
	void update(Employee emp);
	List<Employee> selectAll(Integer did);

}
