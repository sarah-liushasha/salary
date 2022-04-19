layui.use(["table", "layer", "form", "laydate"], function() {
	var table = layui.table;
	var layer = layui.layer;
	var $ = layui.jquery;
	var form = layui.form;
	var laydate = layui.laydate;

	$("#searchBtn").on("click", function() {
		var keyword=$("#keyword").val();
		table.render({
			elem: "#bt1",
			url: "/salary?action=list",//给一些数据   table.render自动发送一个ajax请求
			where: { keyword: keyword },
			cols: [[{
				title: "员工",
				field: "empName"
			}, {
				title: "科室",
				field: "deptName"
			}, {
				title: "工资月份",
				field: "salaryMonth"
			}, {
				title: "工资",
				field: "actual"
			}
			]]
		});
	}).click();

	$("#addBtn").on("click", function() {
		$.get("/dept?action=all", function(depts) {
			layer.open({
				type: 1,
				area: ['400px', '400px'],
				title: "发工资",
				content: $("#addTemplate").html()
			});

			laydate.render({
				elem: "#monthInput",
				type: "month"
			});
			$.each(depts, function(index, item) {
				$("#deptSelect").append("<option value='" + item.id + "'>" + item.name + "</option>");
			});
					
			form.on('select(selectTemplate)', function(data) {
				changeDept(data.value);
			});
			form.render();
			changeDept($("#deptSelect").val());
			
			$("#saveBtn").on("click",function(){
				$.post("/salary",form.val("saveForm"),function(){
					$("#searchBtn").click();
					layer.closeAll();
				});
			});
				
		});
	});


	var changeDept = function(deptId) {
		$.get("/emp?action=selectAll&deptId=" + deptId, function(emps) {
			$("#empSelect").html("");
			$.each(emps, function(index, item) {
				$("#empSelect").append("<option value='" + item.id + "'>" + item.name + "</option>");
			});
			form.render();
		});
	};

});
