$(function() {
	//渲染表格
    creatClassTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){//查看详情
            console.log(data);
            showEditModel(data);
            $("#showTable").css("display","none");
            $("#addForm").css("display","");
		}else if(layEvent === 'del'){ //删除
            console.log(obj.data);
			doDelete(obj);
		}
	});


    //监听是否发布操作
    layui.form.on('switch(statusCB2)', function(obj){
        updatePublic(obj);
    });
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});
	$("#addBtn").click(function(){
	    $("#showTable").css("display","none");
        $("#addForm").css("display","");
    });
    $("#reset").click(function(){
        $("#showTable").css("display","");
        $("#addForm").css("display","none");
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

function updatePublic(obj){
    layer.load(1);
    var newStatus = obj.elem.checked?1:0;
    $.post("announcement/updatePublic", {
        announcementId: obj.elem.value,
        isPublish: newStatus,
    }, function(data){
        layer.closeAll('loading');
        if(data.code==200){
            layui.table.reload('table', {});
        }else{
            layer.msg(data.msg,{icon: 2});
            layui.table.reload('table', {});
        }
    });
}

function showEditModel(data){
    if(data!=null){
        $("#announcementTitle").val(data.announcementTitle);
        $("#announcementIntroduction").val(data.announcementIntroduction);
        $("#announcementContent").val(data.announcementContent);
        if(data.isPublish==1){
            $("#isPublish").checked;
        }
        layui.form.render();
    }

}