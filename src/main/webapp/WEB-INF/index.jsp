<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>主页</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/index.css" />
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo layui-hide-xs layui-bg-black">后台管理系统</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item layui-hide layui-show-md-inline-block">
					<a href="javascript:;"> <img src="/img/0.jpg"
						class="layui-nav-img" /> <span></span>
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="/logout">注销</a>
						</dd>
					</dl>
				</li>
			</ul>
		</div>

		<!-- sider -->
		<div class="layui-side layui-bg-black" id="admin-side">
			<div class="layui-side-scroll" id="admin-navbar-side"
				lay-filter="side"></div>
		</div>

		<!-- center -->
		<div class="layui-body" id="admin-body">
			<div class="layui-tab admin-nav-card layui-tab-brief"
				lay-allowClose="true" lay-filter="admin-tab">
				<ul class="layui-tab-title">

				</ul>
				<div class="layui-tab-content"
					style="min-height: 150px; padding: 0px;"></div>
			</div>
		</div>
		<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script src="js/index.js"></script>
	</div>
</body>

</html>