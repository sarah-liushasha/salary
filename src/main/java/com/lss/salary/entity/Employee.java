package com.lss.salary.entity;

import java.math.BigDecimal;

import lombok.Data;

//员工
@Data
public class Employee {
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	private Integer deptId;
	private BigDecimal performance;
	private BigDecimal insure;
	private BigDecimal fund;
	
}
