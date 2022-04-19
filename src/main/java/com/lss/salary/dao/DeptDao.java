package com.lss.salary.dao;

import java.util.List;

import com.lss.salary.entity.Department;
import com.lss.salary.util.DBHelper;

public class DeptDao {

	public List<Department> selectList(String keyword,Integer page,Integer limit) {
		String sql="select * from department";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(name,'"+keyword+"')>0";
		}
		sql+=" limit ?,?";
		return DBHelper.executeQuery(sql, Department.class,(page-1)*limit,limit);

	}
	
	//统计数据  显示在页面上
	public int selectCount(String keyword) {

		String sql="select count(1) from department";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(name,'"+keyword+"')>0";
		}
		return DBHelper.executeInt(sql);
	}

	public int add(Department dept){
		String sql="insert into department(name,basicSalary) values(?,?)";
		return DBHelper.executeDML(sql, dept.getName(),dept.getBasicSalary());
	}

	public int delete(Integer did) {
		return DBHelper.executeDML("delete from department where id=?",did);
		
	}
	
	public Department selectById(Integer deptId) {
		String sql="select * from department where id=?";
		List<Department> list=DBHelper.executeQuery(sql,Department.class,deptId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(Department dept){
		String sql="update department set name=?,basicSalary=? where id=?";
		DBHelper.executeDML(sql, dept.getName(),dept.getBasicSalary(),dept.getId());
	}

	public List<Department> selectAll() {
		String sql="select * from department";
		return DBHelper.executeQuery(sql, Department.class);
	}
}
