$(function() {
	//渲染表格
    creatClassTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){//查看详情
            console.log(data);
            window.open("/page/queryPage?tid="+data.tid);
            //creatStudentTable(data.cno);
		} else if(layEvent === 'del'){ //删除
            console.log(obj.data);
			doDelete(obj);
		}else if(layEvent ==="report"){
		    download(obj);
        }
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});

    //监听是否关闭开关操作
    layui.form.on('switch(statusCB1)', function(obj){
        updateClose(obj);
    });
    //监听是否发布操作
    layui.form.on('switch(statusCB2)', function(obj){
        updatePublic(obj);
    });

});

function creatClassTable(){
    layui.table.render({
        elem : '#table',
        url : 'competition/queryCompetition',
        where: {
            token : getToken(),
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'competitionName', sort: true, title: '比赛名称'},
            {field:'competitionDescription', sort: true, title: '比赛描述'},
            {field:'competitionApplyBeginTime', sort: true, title: '开始申请时间'},
            {field:'competitionApplyEndTime', sort: true, title: '申请结束时间'},
            {field:'competitionBeginTime', sort: true, title: '开始时间'},
            {field:'competitionEndTime', sort: true,title: '结束时间'},
            {field:'isClose', sort: true,templet: '#statusTpl1',title: '是否开启'},
            {field:'isPublish', sort: true,templet: '#statusTpl2',title: '是否发布'},
            {align:'center', toolbar: '#barTpl', minWidth: 230, title: '操作'}
        ]]
    });
}

//更改是否关闭状态
function updateClose(obj){
    layer.load(1);
    var newStatus = obj.elem.checked?0:1;
    $.post("competition/updateClose", {
        competitionId: obj.elem.value,
        isClose: newStatus,
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
//下载报告
function download(obj){
    $.ajax({
        url: "competition/downloadCompetitionReport/"+obj.data.competitionId,
        type: "POST",
        dataType: "JSON",
        success: function(data){

        }
    });
}

//更改是否发布状态
function updatePublic(obj){
    layer.load(1);
    var newStatus = obj.elem.checked?0:1;
    $.post("competition/updatePublic", {
        competitionId: obj.elem.value,
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
//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "competition/deleteCompetition/"+obj.data.competitionId,
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
