<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>登录</title>
<link href="plugins/layui/css/layui.css" rel="stylesheet"
	type="text/css" />
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href="css/admin.css" rel="stylesheet" type="text/css">
</head>
<body class="layui-layout-body">
	<div class="layadmin-user-login layadmin-user-display-show"
		id="LAY-user-login">
		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>后台管理系统登录</h2>
				<p></p>
			</div>
			<form action="/login" method="post">
				<div
					class="layadmin-user-login-box layadmin-user-login-body layui-form">
					<div class="layui-form-item">
						<label
							class="layadmin-user-login-icon layui-icon layui-icon-username"
							for="LAY-user-login-username"></label> <input type="text"
							name="username" id="LAY-user-login-username"
							lay-verify="required" class="layui-input" placeholder="用户名">
					</div>
					<div class="layui-form-item">
						<label
							class="layadmin-user-login-icon layui-icon layui-icon-password"
							for="LAY-user-login-password"></label> <input type="password"
							name="password" id="LAY-user-login-password"
							lay-verify="required" placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label
									class="layadmin-user-login-icon layui-icon layui-icon-vercode"
									for="LAY-user-login-vercode"></label> <input type="text"
									name="vercode" id="LAY-user-login-vercode" placeholder="图形验证码"
									class="layui-input">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<img src="https://www.oschina.net/action/user/captcha"
										class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-bottom: 20px;">
						<input type="checkbox" name="remember" lay-skin="primary"
							title="记住密码">
						<div class="layui-unselect layui-form-checkbox" lay-skin="primary">
							<span>记住密码</span><i class="layui-icon layui-icon-ok"></i>
						</div>
						<a lay-href="/user/forget"
							class="layadmin-user-jump-change layadmin-link"
							style="margin-top: 7px;">忘记密码？</a>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" type="button" id="submitBtn"
							lay-filter="LAY-user-login-submit">登 录</button>
					</div>
					<div class="layui-trans layui-form-item layadmin-user-login-other">
						<label>切换语言</label>
						<!-- <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
						<a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
						<a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a> -->
						<a href="/i18n?language=zh" class="layadmin-link">中文</a> <a
							href="/i18n?language=en" class="layadmin-link">English</a> <a
							lay-href="/user/reg"
							class="layadmin-user-jump-change layadmin-link">注册帐号</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['form','layer'],function() {
			var form=layui.form;
			var $=layui.jquery;
			var layer=layui.layer;
			//给登录按钮绑定事件
			$("#submitBtn").on("click",function(){
				var username=$("#LAY-user-login-username").val();
				var password=$("#LAY-user-login-password").val();
				//没录值
				if(!username){
					layer.msg("用户名不能为空");
					return;
				}
				if(!password){
					layer.msg("密码不能为空");
					return;
				}
				$.post("/login",{username:username,password:password},function(result){
					if(result=="true"){
						window.location.href="/index";
					}else{
						layer.msg("用户名或密码错误");
					}
				});
				
			});

		});
	</script>
</body>
</html>