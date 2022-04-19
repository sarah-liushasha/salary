layui.config({
	base: 'js/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var $ = layui.jquery,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 115);
		$content.find('iframe').each(function() {
			$(this).height($content.height()-3);
		});
	}).resize();
	
	//设置navbar
	navbar.set({
		spreadOne: true,
		elem: '#admin-navbar-side',
		cached: true,
		data: navs
	});
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});
	
});
//调整左边的菜单
var navs = [{
	"title": "基本信息管理",
	"icon": "fa-cubes",
	"spread": true,
	"children": [{
		"title": "科室管理",
		"icon": "&#xe641;",
		"href": "/dept?action=index"  //根：webcontent  根里面的stu里的list
	},{
		"title": "员工管理",
		"icon": "&#xe641;",
		"href": "/emp?action=index"
	},{
		"title": "工资管理",
		"icon": "&#xe641;",
		"href": "/salary?action=index"
	}]
}, {
	"title": "综合查询",
	"icon": "fa-cogs",
	"spread": false,
	"children": [{
		"title": "分类统计人数",
		"icon": "fa-table",
		"href":""
	}, {
		"title": "分类统计工资",
		"icon": "fa-navicon",
		"href":""
	}]
}, {
	"title": "系统管理",
	"icon": "fa-cogs",
	"spread": false,
	"children": [{
		"title": "系统用户",
		"icon": "fa-table",
		"href":"/user?action=index"  //通过请求来拿
	}]
}];