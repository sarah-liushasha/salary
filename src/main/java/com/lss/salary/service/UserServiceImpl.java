package com.lss.salary.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.lss.salary.dao.UserDao;
import com.lss.salary.entity.User;
import com.lss.salary.util.StringUtil;
import com.lss.salary.vo.LayuiTableVO;

//这是系统用户service实现类
public class UserServiceImpl implements IUserService {

	UserDao dao=new UserDao();
	@Override
	public LayuiTableVO select(String keyword,Integer page,Integer limit) {
		LayuiTableVO vo=new LayuiTableVO();
		vo.setMsg("");
		vo.setCount(dao.selectCount(keyword));
		vo.setData(dao.selectList(keyword, page, limit));
		return vo;
	}
	@Override
	public void add(User user) {
		try {
			user.setPassword(StringUtil.md5(user.getPassword()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(user.getId()==null) {//新增用户
			dao.add(user);
		}else {
			dao.update(user);
		}

	}
	@Override
	public void delete(Integer uid) {
		dao.delete(uid);
		
	}
	@Override
	public User selectById(Integer uid) {
		return dao.selectById(uid);
	}
	@Override
	public void update(User user) {
		dao.update(user);
	}
	@Override
	public Boolean check(HttpServletRequest request, String username, String password) {
		//在数据库中查用户名等于username，并且密码等于password的数据，如果能查到说明对了
		//根据用户名查出来，再看密码
		User user=dao.selectByUsername(username);
		
		/* User user=dao.selectByUsername(username); if(user==null) { return false; }
		 * if(user.getPassword().equals(password)) { return true; } return false;
		 */
		 
		Boolean success;
		try {
			success = user!=null && user.getPassword().equals(StringUtil.md5(password));
			if(success) {
				request.getSession().setAttribute("current", user);
			}
			return success;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	
	}

}
