package com.lss.salary.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
//要啥返回啥
public class SalaryVo {
	
	private String empName;
	private String deptName;
	private String salaryMonth;
	private BigDecimal actual;

}
