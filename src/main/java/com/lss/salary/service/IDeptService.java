package com.lss.salary.service;

import java.util.List;

import com.lss.salary.entity.Department;
import com.lss.salary.vo.LayuiTableVO;

public interface IDeptService {
	//分页查询用户
	LayuiTableVO select(String keyword,Integer page,Integer limit);
	void add(Department dept);
	void delete(Integer did);
	Department selectById(Integer did);
	void update(Department dept);
	List<Department> getAll();

}
