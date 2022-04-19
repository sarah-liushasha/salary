package com.lss.salary.service;

import javax.servlet.http.HttpServletRequest;

import com.lss.salary.entity.User;
import com.lss.salary.vo.LayuiTableVO;

//系统用户的接口
public interface IUserService {

	/**
	 * 分页查询用户列表
	 * @param keyword 模糊查询的关键数字
	 * @param page 当前第几页
	 * @param limit 每页几条
	 * @return layuiTableVO 查到的数据
	 */
	//分页查询用户
	LayuiTableVO select(String keyword,Integer page,Integer limit);
	void add(User user);
	void delete(Integer uid);
	User selectById(Integer uid);
	void update(User user);
	Boolean check(HttpServletRequest request, String username, String password);
}
