package com.lss.salary.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

//部门
@Data
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private BigDecimal basicSalary;
}
