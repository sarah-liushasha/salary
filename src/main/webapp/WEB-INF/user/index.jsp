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

		<input type="hidden" name="action" value="insert"/>

		<input type="hidden" name="id">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名：</label>
			<div class="layui-input-inline">
				<input type="text" name="username" lay-verify="required"
					placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">密码：</label>
			<div class="layui-input-inline">
				<input type="text" name="password" lay-verify="required"
					placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">姓名：</label>
			<div class="layui-input-inline">
				<input type="text" name="name" lay-verify="required"
					placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>

		
		<div class="layui-form-item">
			<label class="layui-form-label">电话号码：</label>
			<div class="layui-input-inline">
				<input type="text" name="tel" lay-verify="required"
					placeholder="请输入" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-inline">
			<label class="layui-form-label">性别：</label>
			<div class="layui-input-inline">
				<input type="radio" name="sex" value="男" title="男" checked>
				<input type="radio" name="sex" value="女" title="女">
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
	<script type="text/javascript">
		//引入table组件   做事情
		layui.use([ "table","layer","form"], function() {
			var table = layui.table;
			var $ = layui.jquery;
			var layer=layui.layer;
			var form = layui.form;
			
			//找查询按钮  绑定事件
			$("#searchBtn").on("click", function() {
				var keyword=$("#keyword").val();
				table.render({
					elem : "#bt1",
					url : "/user?action=list",//给一些数据   table.render自动发送一个ajax请求
					where:{keyword:keyword},
					cols : [ [ {
						title : "ID",
						field : "id"
					}, {
						title : "用户名",
						field : "username"
					}, {
						title : "姓名",
						field : "name"
					}, {
						title : "电话",
						field : "tel"
					}, {
						title : "性别",
						field : "sex"
					} , {
						title : "操作",
						toolbar:"#operateTemplate"
					} 
					] ],
					page : true,//设置分页工具
					limits : [ 2, 5, 8 ],//设置下拉框里面的选项
					limit : 3
				});

			}).click();
			
			
			//给表格绑定工具条事件
			table.on("tool",function(obj){
				var userId=obj.data.id;
				if(obj.event=="del"){
					//问有两个函数   确定,取消
					layer.confirm("你确定要删除这个用户吗？",function(){
						//uid:传到后台的参数名字  userId传到后台
						$.post("/user?action=del",{uid:userId},function(){
							$("#searchBtn").click();
						});
						//只要点了确定  无论成功不成功  都要关闭询问窗口
						layer.closeAll();
					});
				}
				if(obj.event=="edit"){
					//window.location.href="/user?action=add&uid="+userId;
					//发送请求，根据用户id获取用户的所有数据
					$.get("/user?action=get&uid="+userId,function(userData){
						layer.open({
							type:1,
							area:['400px','400px'],
							title:"编辑用户",
							content:$("#addTemplate").html()
						});
						form.val("saveForm",userData);
					});
				} 
			});
			
			
			
			$("#addBtn").on("click",function(){
				layer.open({
					type:1,
					area:['400px','400px'],
					title:"新增用户",
					content:$("#addTemplate").html()
				});
				form.render();
				//弹完窗口之后再保存，否则找不到这个按钮
				/* $("#saveBtn").on("click",function(){
					//获取这个元素中的所有值打包  通过lay-filter
					var formData=form.val("saveForm");
					//发请求，把数据给servlet让他们帮忙保存到数据库
					$.post("/user",formData,function(){
						//请求成功之后  1.点查询按钮  2.关闭窗口
						$("#searchBtn").click();
						layer.closeAll();
						
					});	
				}); */

			});
		
			//给弹出窗口中的保存按钮绑定委托事件 
			$("body").on("click","#saveBtn",function(){
				//获取这个元素中的所有值打包  通过lay-filter
				var formData=form.val("saveForm");
				//发请求，把数据给servlet让他们帮忙保存到数据库
				$.post("/user",formData,function(){
					//请求成功之后  1.点查询按钮  2.关闭窗口
					$("#searchBtn").click();
					layer.closeAll();
				});	
			});
			
			
		});
	
	</script>
</body>
</html>