$(function() {
	//渲染表格
    console.log("question");
	layui.table.render({
		elem : '#table',
		url : 'api/question',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'qid', sort: true, title: 'ID'},
			{field:'qtype', sort: true, title: '类型'},
			{field:'qcontent', sort: true, title: '内容'},
			{field:'qchoice', sort: true, title: '选项'},
			{field:'qanswer', sort: true, title: '答案'},
			{field:'qdegree', sort: true,title: '难易程度'},
            {field:'cname', sort: true,title: '课程名称'},
            {field:'qchapter', sort: true,title: '章节'},
            {field:'qparagraph', sort: true,title: '节'},
			{align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
    	]]
	});
    console.log("question1");
	//添加按钮点击事件


    layui.use(['form', 'layedit'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;
        var $ = layui.$;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });
        form.on('select(qtype)',function(data){
            var select=$("#qtype");
            var type=select.val();
            console.log(type);
            var checkboxs=document.getElementsByName("daan");
            if(type=='单选题'){
                $("#xuanze").css('display','');
                $("#panduan").css('display','none');
                $("input[type='checkbox']").attr('type','radio');
                /*for(var i=0;i<checkboxs.length;i++){
                    console.log(checkboxs[i]);
                    checkboxs[i].type='radio';
                }*/
            }else if(type=='多选题'){
                $("#xuanze").css('display','');
                $("#panduan").css('display','none');
                $("input[type='radio']").attr('type','checkbox');
                /*                        for(var i=0;i<checkboxs.length;i++){
                                            console.log(checkboxs[i]);
                                            checkboxs[i].type='checkbox';
                                        }*/
            }else if(type=='填空题' || type == '简答题'){
                $("#xuanze").css('display','none');
                $("#panduan").css('display','none');
            } else if(type=='判断题'){
                $("#xuanze").css('display','none');
                $("#panduan").css('display','');
            }
            form.render('radio');
            form.render('checkbox');
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            data.field.token = getToken();
            data.field._method = $("#editForm").attr("method");
            layer.load(1);
            $.ajax({
                url: "jss/addQuestion",
                type: "post",
				data:data.field,
                dataType: "JSON",
                success: function(data){
                    layer.closeAll('loading');
                    if(data.code==200){
                        window.location.reload();
                       // layer.msg(data.msg,{icon: 1});
                    }else{
                        layer.msg(data.msg,{icon: 2});
                    }
                }
			});
            /*$.post("jss/addQuestion", data.field, function(data){
                layer.closeAll('loading');
                if(data.code==200){
                    /!*$("#xuanze").css('display','');
                    $("#showTable").css('display','');
                    $("#showForm").css('display','none');
                    layer.msg(data.msg,{icon: 1});
                    layer.closeAll('page');*!/
                    window.location.reload();
                   // layui.table.reload('table', {});
                }else{
                    layer.msg(data.msg,{icon: 2});
                }
            }, "JSON");*/
            return false;
        });

        $("#reset").click(function(){
            $("#xuanze").css('display','');
            $("#showTable").css('display','');
            $("#showForm").css('display','none');
        });

        $("#addBtn").click(function(){
            $("#showTable").css('display','none');
            $("#showForm").css('display','');
        });
        form.render();
    });
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
		console.log(obj);
		if(layEvent === 'edit'){ //修改
			showEditModel(data);
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

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		console.log(index);
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/question/"+obj.data.qid+"?token="+getToken(),
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