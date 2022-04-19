package com.lss.salary.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

//工资
@Data
public class Salary {
	private Integer id;
	private Integer empId;
	private String salaryMonth;
	private BigDecimal actual;
	private LocalDateTime createtime;
	private Integer createby;
	private BigDecimal bonus;

}
