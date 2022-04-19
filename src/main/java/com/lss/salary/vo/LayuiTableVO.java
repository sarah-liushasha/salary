package com.lss.salary.vo;

import java.util.List;

import lombok.Data;

@Data
public class LayuiTableVO {
	private int code;//code是0 表示这个查询成功   默认值正好是0，表示成功
	private String msg;//查询失败原因
	private int count;//总条数分页使用
	private List<?> data;//分页之后查出来的数据  占位一下data里面是一个？的集合
}
