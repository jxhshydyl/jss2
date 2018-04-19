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
        } else if(layEvent === 'isCancel'){ //取消分配
            console.log(obj.data);
			doDelete(obj);
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
        url : 'competitionApply/queryCompetitionApply',
        where: {
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'email', sort: true,minWidth: 240,title: '邮箱'},
            {field:'applicationPeopleCount', sort: true,width: 120,title: '申请人数'},
            {field:'isHaveSendEmail', sort: true,width: 120,templet: '#statusTpl', title: '是否发送邮件'},
            {field:'competitionAccountId', sort: true, title: '分配帐号编号'},
            {align:'center', toolbar: '#barTpl', minWidth: 280, title: '操作'}
        ]]
    });
}
//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "competitionApply/cancelCompetitionAccount/"+obj.data.competitionApplicationId,
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


//显示表单弹窗
function showEditModel(data){
    layer.open({
        type: 1,
        title: data==null?"添加用户":"修改用户",
        area: '550px',
        offset: '120px',
        content: $("#addModel").html()
    });
    $("#editForm")[0].reset();
    $("#editForm").attr("method","POST");
    var selectItem = "";
    if(data!=null){
        $("#editForm input[name=userId]").val(data.userId);
        $("#editForm input[name=userAccount]").val(data.userAccount);
        $("#editForm input[name=userNickname]").val(data.userNickname);
        $("#editForm input[name=mobilePhone]").val(data.mobilePhone);
        $("#editForm").attr("method","PUT");
        selectItem = data.roleId;
        if('男'==data.sex){
            $("#sexMan").attr("checked","checked");
            $("#sexWoman").removeAttr("checked");
        }else{
            $("#sexWoman").attr("checked","checked");
            $("#sexMan").removeAttr("checked");
        }
        layui.form.render('radio');
    }
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });
}
