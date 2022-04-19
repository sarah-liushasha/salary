package com.lss.salary.dao;

import java.util.List;

import com.lss.salary.entity.Employee;
import com.lss.salary.util.DBHelper;

public class EmpDao {
	public List<Employee> selectList(String keyword,Integer page,Integer limit) {
		String sql="select * from employee";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(name,'"+keyword+"')>0";
		}
		sql+=" limit ?,?";
		return DBHelper.executeQuery(sql, Employee.class,(page-1)*limit,limit);

	}
	
	//统计数据  显示在页面上
	public int selectCount(String keyword) {

		String sql="select count(1) from employee";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(name,'"+keyword+"')>0";
		}
		return DBHelper.executeInt(sql);
	}

	public int add(Employee emp){
		String sql="insert into employee(name,age,sex,deptId,performance,insure,fund) values(?,?,?,?,?,?,?)";
		return DBHelper.executeDML(sql, emp.getName(),emp.getAge(),emp.getSex(),emp.getDeptId(),emp.getPerformance(),emp.getInsure(),emp.getFund());
	}

	public int delete(Integer eid) {
		return DBHelper.executeDML("delete from employee where id=?",eid);
		
	}
	
	public Employee selectById(Integer empId) {
		String sql="select * from employee where id=?";
		List<Employee> list=DBHelper.executeQuery(sql,Employee.class,empId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(Employee emp){
		String sql="update employee set name=?,age=?,sex=?,deptId=?,performance=?,insure=?,fund=? where id=?";
		DBHelper.executeDML(sql, emp.getName(),emp.getAge(),emp.getSex(),emp.getDeptId(),emp.getPerformance(),emp.getInsure(),emp.getFund(),emp.getId());
	}

	public List<Employee> selectAll(Integer did) {
		String sql="select * from employee where deptId=?";
		return DBHelper.executeQuery(sql,Employee.class,did);
	}

}
