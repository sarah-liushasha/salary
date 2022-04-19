package com.lss.salary.dao;

import java.util.List;

import com.lss.salary.entity.User;
import com.lss.salary.util.DBHelper;

//系统用户dao
public class UserDao {
	public List<User> selectList(String keyword,Integer page,Integer limit) {
		String sql="select id,username,name,tel,sex from user";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(username,'"+keyword+"')>0";
		}
		sql+=" limit ?,?";
		return DBHelper.executeQuery(sql, User.class,(page-1)*limit,limit);
		
	}
	
	public int selectCount(String keyword) {

		String sql="select count(1) from user";
		if(keyword!=null&&!keyword.equals("")) {
			sql+=" where instr(username,'"+keyword+"')>0";
		}
		return DBHelper.executeInt(sql);
	}

	public int add(User user){
		String sql="insert into user(username,password,name,tel,sex) values(?,?,?,?,?)";
		return DBHelper.executeDML(sql, user.getUsername(),user.getPassword(),user.getName(),user.getTel(),user.getSex());
	}

	public int delete(Integer uid) {
		return DBHelper.executeDML("delete from user where id=?",uid);
		
	}
	
	public User selectById(Integer userId) {
		String sql="select * from user where id=?";
		List<User> list=DBHelper.executeQuery(sql,User.class,userId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(User user){
		String sql="update user set username=?,password=?,name=?,tel=?,sex=? where id=?";
		DBHelper.executeDML(sql, user.getUsername(),user.getPassword(),user.getName(),user.getTel(),user.getSex(),user.getId());
	}

	public User selectByUsername(String username) {
		String sql="select * from user where username=?";
		List<User> list=DBHelper.executeQuery(sql,User.class,username);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
