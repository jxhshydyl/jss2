$(function() {
	//渲染表格
    creatClassTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){//查看详情
            console.log(data);
            //creatStudentTable(data.cno);
		}else if(layEvent === 'del'){ //删除
            console.log(obj.data);
			doDelete(obj);
		}
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});
	$("#addBtn").click(function(){

    });

	//渲染表单
});

function creatClassTable(){
    layui.table.render({
        elem : '#table',
        url : 'announcement/queryAnnouncement',
        where: {
            token : getToken(),
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'announcementId', sort: true, title: '编号'},
            {field:'announcementTitle', sort: true, title: '公告标题'},
            {field:'announcementIntroduction', sort: true, title: '公告简介'},
            {field:'announcementContent', sort: true, title: '公告内容'},
            {field:'isPublish', sort: true, templet: '#statusTpl',title: '是否发布'},
            {field:'announcementCreateTime', sort: true, title: '创建时间'},
            {field:'announcementPublishTime', sort: true,title: '最近一次公布时间'},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]
    });
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "announcement/deleteAnnouncement/"+obj.data.announcementId,
			type: "DELETE", 
			dataType: "JSON",
			success: function(data){
			    console.log(data);
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
	if (value=='') {
		key = '';
	}
	layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
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
