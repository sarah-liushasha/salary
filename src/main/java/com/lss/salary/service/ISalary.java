package com.lss.salary.service;

import javax.servlet.http.HttpServletRequest;

import com.lss.salary.entity.Salary;
import com.lss.salary.vo.LayuiTableVO;

public interface ISalary {
	LayuiTableVO select(String keyword,Integer page,Integer limit);

	void save(Salary salary,HttpServletRequest request);

}
