package com.lss.salary.service;

import java.util.List;

import com.lss.salary.dao.DeptDao;
import com.lss.salary.entity.Department;
import com.lss.salary.vo.LayuiTableVO;

public class DeptServiceImpl implements IDeptService {

	DeptDao dao = new DeptDao();

	@Override
	public LayuiTableVO select(String keyword, Integer page, Integer limit) {
		LayuiTableVO vo = new LayuiTableVO();
		vo.setMsg("");
		vo.setCount(dao.selectCount(keyword));
		vo.setData(dao.selectList(keyword, page, limit));
		return vo;
	}

	@Override
	public void add(Department dept) {
		if(dept.getId()==null) {
			dao.add(dept);
		}else {
			dao.update(dept);
		}
	}

	@Override
	public void delete(Integer did) {
		dao.delete(did);
	}

	@Override
	public Department selectById(Integer did) {
		return dao.selectById(did);
	}
	
	@Override
	public void update(Department dept) {
		dao.update(dept);
	}

	@Override
	public List<Department> getAll() {
		return dao.selectAll();
		
	}


}
