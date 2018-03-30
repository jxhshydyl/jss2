$(function() {
	//渲染表格
	layui.table.render({
		elem : '#table',
		url : 'api/code',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'qid', sort: true, title: 'ID'},
			{field:'qname', sort: true, title: '名称'},
			{field:'qdegree', sort: true, title: '难易程度'},
			{field:'qtype', sort: true, title: '类型'},
            {field:'cname', sort: true,title: '所属课程'},
            {field:'qchapter', sort: true,title: '所属章节'},
			{field:'limitTime', sort: true, title: '限制时间'},
			{field:'limitMemory', sort: true,title: '限制内存'},
            {field:'totalSubmitCount', sort: true,title: '总提交次数'},
            {field:'totalRightCount', sort: true,title: '正确提交次数'},
			{align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
    	]]
	});
	//添加按钮点击事件

	
	//表单提交事件
	layui.form.on('submit(btnSubmit)', function(data) {
		data.field.token = getToken();
		data.field._method = $("#editForm").attr("method");
		layer.load(1);
		$.post("api/question", data.field, function(data){
			layer.closeAll('loading');
			if(data.code==200){
				layer.msg(data.msg,{icon: 1});
				layer.closeAll('page');
				layui.table.reload('table', {});
			}else{
				layer.msg(data.msg,{icon: 2});
			}
		}, "JSON");
		return false;
	});
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
		console.log(obj);
		if(layEvent === 'edit'){ //修改
            $("#showQuestionDetail").css("display","");
            $("#showTable").css("display","none");
            $("#showForm").css('display','none');
			showQuestionDetail(data);
			editQuestion(data);
		} else if(layEvent === 'del'){ //删除
			doDelete(obj);
		}
	});
	
	//监听状态开关操作
	layui.form.on('switch(statusCB)', function(obj){
		updateStatus(obj);
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});

/*	//增加选项
	$("#addOptions").click(function(){
		console.log("zengjiaxuanxiang");
        showEditModel(null);
	});*/
});


//显示表单弹窗
function showQuestionDetail(data){
	console.log(data);
	if(data!=null){
		$("#qname").html(data.qname);
		$("#qdescribe").val(data.qdescribe);
		$("#inputDescribe").val(data.inputDescribe);
		$("#limitMemory").html(data.limitMemory);
        $("#limitTime").html(data.limitTime);
        $("#outputDescripe").val(data.outputDescripe);
        $("#exampleInput").val(data.exampleInput);
        $("#exampleOutput").val(data.exampleOutput);
        $("#qdegree").html(data.qdegree);
	}
}
function editQuestion(data){
    var layedit = layui.layedit
        ,$ = layui.jquery;
    console.log($("#qdescribe_editor"));
    $("#qdescribe_editor").val(data.qdescribe);
    console.log($("#qdescribe_editor"));
    $("#inputDescribe_editor").val(data.inputDescribe);
    $("#limitMemory").html(data.limitMemory);
    $("#limitTime").html(data.limitTime);
    $("#outputDescripe_editor").val(data.outputDescripe);
    $("#exampleInput_editor").val(data.exampleInput);
    $("#exampleOutput_editor").val(data.exampleOutput);
    $("#qdegree").html(data.qdegree);
    $("#inputDescribe_editor").html(data.qdescribe);
    document.getElementById("qdescribe_editor").value=data.qdescribe;

    //创建一个编辑器
    var editQdescribe = layedit.build('qdescribe_editor',{
        height: 200,
        tool: [ 'strong','italic','underline','del','|','left','center','right'  ,'image']
    });
    //创建一个编辑器
    var editInputDescribe = layedit.build('inputDescribe_editor',{
        height: 150,
        tool: [ 'strong','italic','underline','del','|','left','center','right'  ,'image']
    });
    //创建一个编辑器
    var editOutputDescripe = layedit.build('outputDescripe_editor',{
        height: 150,
        tool: [ 'strong','italic','underline','del','|','left','center','right'  ,'image']
    });
    //创建一个编辑器
    var editExampleInput = layedit.build('exampleInput_editor',{
        height: 150,
        tool: [ 'strong','italic','underline','del','|','left','center','right'  ,'image']
    });
    //创建一个编辑器
    var editExampleOutput = layedit.build('exampleOutput_editor',{
        height: 150,
        tool: [ 'strong','italic','underline','del','|','left','center','right'  ,'image']
    });
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		console.log(index);
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/code/"+obj.data.qid+"?token="+getToken(),
			type: "DELETE", 
			dataType: "JSON",
			success: function(data){
				layer.closeAll('loading');
				console.log(data);
				if(data.code==200){
					layer.msg(data.msg,{icon: 1});
					obj.del();
				}else{
					layer.msg(data.msg,{icon: 2});
				}
			}
		});
	});
}

//搜索
function doSearch(table){
	var key = $("#searchKey").val();
	var value = $("#searchValue").val();
	if (value=='') {
		key = '';
	}
	layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
}