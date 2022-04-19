package com.lss.salary.dao;

import java.util.List;

import com.lss.salary.entity.Salary;
import com.lss.salary.util.DBHelper;
import com.lss.salary.vo.SalaryVo;

public class SalaryDao {
	public List<SalaryVo> selectList(String keyword,Integer page,Integer limit) {
		String sql="select employee.name 'empName',department.name 'deptName',salaryMonth ,actual"
				+ " from salary_info"
				+ " left join employee on employee.id=salary_info.empId"
				+ " left join department on department.id=employee.deptId";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(employee.name,'"+keyword+"')>0 or salary_info.salaryMonth='"+keyword+"'";
		}
		sql+=" limit ?,?";
		return DBHelper.executeQuery(sql, SalaryVo.class,(page-1)*limit,limit);
	}
	
	public int insert(Salary salary){
		String sql="insert into salary_info(empId,salaryMonth,actual,createtime,createby,bonus) values(?,?,?,?,?,?)";
		return DBHelper.executeDML(sql, salary.getEmpId(),salary.getSalaryMonth(),salary.getActual(),salary.getCreatetime(),salary.getCreateby(),salary.getBonus());
	}

	public int selectCount(String keyword) {
		String sql="select count(1) from salary_info";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(employee.name,'"+keyword+"')>0 or salary_info.salaryMonth='"+keyword+"'";
		}
		return DBHelper.executeInt(sql);
	}


}
