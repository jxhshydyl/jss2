$(function() {
	//渲染表格
    creatSubmitTaskTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){//查看详情
            creatStudentSubmitTaskTable(data);
            $("#class").css("display","none");
            $("#student").css("display","");
		}
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});
});

function creatSubmitTaskTable(){
    layui.table.render({
        elem : '#table',
        url : 'api/onlineMarking',
        where: {
            token : getToken(),
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'className', sort: true, title: '班级名称'},
            {field:'taskName', sort: true, title: '作业名称'},
            {field:'courseName', sort: true, title: '所属课程'},
            {field:'tchapter', sort: true, title: '所属章节'},
            {field:'endTime', sort: true, title: '截止时间'},
            {field:'unsubmittedNumber', sort: true, title: '未提交人数'},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]
    });
}
function creatStudentSubmitTaskTable(data){
    layui.table.render({
        elem : '#table',
        url : 'api/onlineMarking/studentTask',
        method:'post',
        where: {
            token : getToken(),
            cno:data.classNo,
            tid:data.tid
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'sno', sort: true, title: '学号'},
            {field:'sname', sort: true, title: '姓名'},
            {field:'taskName', sort: true, title: '作业名称'},
            {field:'submitTime', sort: true, title: '提交时间'},
            {field:'endTime', sort: true, title: '截止时间'},
            {field:'status', sort: true, title: '状态'},
            {align:'center', toolbar: '#bar', minWidth: 180, title: '操作'}
        ]]
    });
    //工具条点击事件
    layui.table.on('tool(table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;
        if(layEvent === 'readOver'){//批阅
            console.log(data);
            console.log("批阅");
        }
    });
}
//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/user/"+obj.data.userId+"?token="+getToken(), 
			type: "DELETE", 
			dataType: "JSON", 
			success: function(data){
				layer.closeAll('loading');
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
	var keys = $("#searchKeys").val();
    var values = $("#searchValues").val();

	if (value=='') {
		key = '';
	}
	layui.table.reload('table',
        {where:
                {
                    searchKey: key,
                    searchValue: value,
                    searchKeys: keys,
                    searchValues: values
                }
        });
}

//查看
function viewStudentsByClass(cno){
        layer.load(1);
        console.log(cno);
        $.post("api/class/students/"+cno, {
            token: getToken(),
            _method: "post"
        }, function(data){
        	console.log(data);
            layer.closeAll('loading');
            if(data.code==200){
                layer.msg(data.msg,{icon: 1});
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        },"JSON");
}