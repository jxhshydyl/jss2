$(function() {
	//渲染表格
    creatClassTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){ //查看详情
            creatStudentTable(data.cno);
            $("#class").css("display","none");
            $("#students").css("display","");
		} else if(layEvent === 'del'){ //删除
			doDelete(obj);
		} else if(layEvent === 'viewDetail'){
            showEditModel(data);
        }
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});
});

function creatClassTable(){
    layui.table.render({
        elem : '#table',
        url : 'api/class',
        where: {
            token : getToken(),
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'cno', sort: true, title: '班级编号'},
            {field:'cname', sort: true, title: '班级名称'},
            {field:'cnum', sort: true, title: '班级人数'},
            {field:'academy', sort: true, title: '所属学院'},
            {field:'major', sort: true, title: '所属专业'},
            {field:'ctime_start', sort: true,title: '开始年月'},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]
    });
}

function creatStudentTable(cno){
    layui.table.render({
        elem : '#table',
        url : 'api/class/students',
		method:'post',
        where: {
            token : getToken(),
            cno:cno
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'sno', sort: true, title: '学号'},
            {field:'sname', sort: true, title: '姓名'},
            {field:'ssex', sort: true, title: '性别'},
            {field:'syear', sort: true, title: '入学日期'},
            {field:'sacademy', sort: true, title: '所属学院'},
            {field:'smajor', sort: true,title: '所属专业'},
            {field:'sclass', sort: true,title: '所属班级'},
            {align:'center', toolbar: '#view', minWidth: 180, title: '操作'}
        ]]
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
//显示表单弹窗
function showEditModel(data){
    layer.open({
        type: 1,
        title: data==null?"添加用户":"修改用户",
        area: '450px',
        offset: '120px',
        content: $("#addModel").html()
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