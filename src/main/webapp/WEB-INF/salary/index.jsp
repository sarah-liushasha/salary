<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/plugins/layui/css/layui.css"
	type="text/css">
</head>
<body style="padding-top: 25px">

	<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">关键字</label>
			<div class="layui-input-inline">
				<input id="keyword" type="text" name="title" required placeholder="请输入关键字"
					autocomplete="off" class="layui-input">
			</div>
			<input id="searchBtn" type="button" class="layui-btn" value="查询" />
			
			<input id="addBtn" type="button" class="layui-btn" value="新增">
			
		</div>
	</form>
	<table id="bt1">
	</table>

	<!-- 数据表格工具条的模板 -->
	<script type="text/html" id="operateTemplate">
		 <a lay-event="edit" class="layui-btn layui-btn-xs">编辑</a>
 		 <a lay-event="del" class="layui-btn layui-btn-xs layui-btn-danger">删除</a>
		 
	</script>
	
	<script type="text/html" id="addTemplate">
	<form class="layui-form layui-form-pane"
		method="post" lay-filter="saveForm">

		<input type="hidden" name="action" value="save"/>

		<input type="hidden" name="id">
		<div class="layui-form-item">
			<label class="layui-form-label">科室：</label>
			<div class="layui-input-inline">
				<select id="deptSelect" lay-filter="selectTemplate"></select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">员工：</label>
			<div class="layui-input-inline">
				<select id="empSelect" name="empId"></select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">月份：</label>
			<div class="layui-input-inline">
				<input class="layui-input" type="text" id="monthInput" name="salaryMonth" readonly/>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">奖金：</label>
			<div class="layui-input-inline">
				<input class="layui-input" type="text" id="bonus" name="bonus"/>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="saveBtn" type="button" class="layui-btn">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	
	</script>

	<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="/js/salary.js" ></script>
</body>
</html>