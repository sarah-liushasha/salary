package com.lss.salary.entity;

import java.io.Serializable;

import lombok.Data;
//添加注解  lombok get/set方法就创建好了
/*@Getter
@Setter*/

//get  set   toString
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String sex;
	private String status;
	
}
