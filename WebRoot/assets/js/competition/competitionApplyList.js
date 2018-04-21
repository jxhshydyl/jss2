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
            //creatStudentTable(data.cno);
		} else if(layEvent === 'sendEmail'){ //发布邮件
            shareTask(data);
        }
	});
	
	//搜索按钮点击事件
	$("#searchBtn").click(function(){
		doSearch(table);
	});

    //监听是否关闭开关操作
    layui.form.on('switch(statusCB1)', function(obj){
        updateSuspendCompetition(obj);
    });
});

function creatClassTable(){
    layui.table.render({
        elem : '#table',
        url : 'competitionApply/queryCompetitionApply',
        where: {
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'email', sort: true,title: '邮箱'},
            {field:'applicationPeopleCount', sort: true,title: '申请人数'},
            {field:'isHaveSendEmail', sort: true,templet: '#statusTpl', title: '是否发送邮件'},
            {field:'competitionAccountId', sort: true, title: '分配帐号编号'},
            {field:'isSuspendCompetition', sort: true,templet: '#statusTpl1',title: '是否禁赛'},
            {align:'center', toolbar: '#barTpl', title: '操作'}
        ]]
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


//显示表单弹窗
function showEditModel(data){
    layer.open({
        type: 1,
        title: "申请详情",
        area: '550px',
        offset: '120px',
        content: $("#addModel").html()
    });
    if(data!=null){
        $("#competitionApplicationId").text(data.competitionApplicationId);
        $("#phone").text(data.phone);
        $("#competitionName").text(data.competitionName);
        $("#competitionAccountId").text(data.competitionAccountId);
        $("#applicationSummary").text(data.applicationSummary);
        $("#applicationContent").text(data.applicationContent);
    }
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });
}
function updateSuspendCompetition(obj) {
    layer.load(1);
    var newStatus = obj.elem.checked?-1:0;
    $.post("competitionApply/updateSuspendCompetition", {
        competitionApplicationId: obj.elem.value,
        isSuspendCompetition: newStatus,
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
