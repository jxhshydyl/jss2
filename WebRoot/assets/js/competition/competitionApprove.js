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
            $("#competitionFrom").css("display","");
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
    //增加按钮点击事件
    $("#addBtn").click(function(){
        $("#showTable").css("display","none");
        $("#competitionFrom").css("display","");
    });

    $("#reset").click(function(){
        $("#showTable").css("display","");
        $("#competitionFrom").css("display","none");
    });

    //监听是否关闭开关操作
    layui.form.on('switch(statusCB1)', function(obj){
        updateClose(obj);
    });
    //监听是否发布操作
    layui.form.on('switch(statusCB2)', function(obj){
        updatePublic(obj);
    });


    /* 比赛表单*/

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#competitionBeginTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#competitionEndTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#competitionApplyBeginTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#competitionApplyEndTime',
            type: 'datetime'
        });
        //创建一个编辑器
        var editIndex = layedit.build('competitionDescription');
        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 1){
                    return '请输入标题!';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        form.on('radio(declare)', function(data){
            if(data.value==1){
                $("#isCanDeclare").css("display","")
            }else{
                $("#isCanDeclare").css("display","none")
            }
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            data.field.roleId = getCurrentUser().roleId;
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            $.ajax({
                url: "competition/addCompetition/",
                type: "post",
                data:data.field,
                success: function(data){
                    console.log(data);
                    layer.closeAll('loading');
                    if(data.code==200){
                        layer.msg(data.msg,{icon: 1});
                        window.location.reload();
                    }else{
                        layer.msg(data.msg,{icon: 2});
                    }
                }
            });
            return false;
        });
        form.render();
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
            {field:'competitionApplyBeginTime', sort: true, title: '开始申请'},
            {field:'competitionApplyEndTime', sort: true, title: '申请结束'},
            {field:'competitionBeginTime', sort: true, title: '开始时间'},
            {field:'competitionEndTime', sort: true,title: '结束时间'},
            {field:'isClose', sort: true,templet: '#statusTpl1',title: '是否开启'},
            {align:'center', toolbar: '#barTpl', title: '操作'}
        ]]
    });
}

//更改是否关闭状态
function updateClose(obj){
    layer.load(1);
    var newStatus = obj.elem.checked?1:0;
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
    var newStatus = obj.elem.checked?1:0;
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

function showEditModel(data){
    if(data!=null){
        $("#competitionName").val(data.competitionName);
        $("#competitionDescription").val(data.competitionDescription);
        $("#competitionPlayersCount").val(data.competitionPlayersCount);

        if(data.isClose==1){
            $("#close1").attr("checked","checked");
            $("#close2").removeAttr("checked");
        }else{
            $("#close1").removeAttr("checked");
            $("#close2").attr("checked","checked");
        }

        $("intput[name=isPublish]").val(data.isPublish);
        if(data.isPublish==1){
            $("#public1").attr("checked","checked");
            $("#public2").removeAttr("checked");
        }else{
            $("#public1").removeAttr("checked");
            $("#public2").attr("checked","checked");
        }
        $("#competitionBeginTime").val(data.competitionBeginTime);
        $("#competitionEndTime").val(data.competitionEndTime);

        $("intput[name=isCanDeclare]").val(data.isCanDeclare);
        if(data.isCanDeclare==1){
            $("#canDeclare1").attr("checked","checked");
            $("#canDeclare2").removeAttr("checked");
            $("#isCanDeclare").css("display","")
        }else{
            $("#canDeclare1").removeAttr("checked");
            $("#canDeclare2").attr("checked","checked");
            $("#isCanDeclare").css("display","none")
        }
        $("#competitionApplyBeginTime").val(data.competitionApplyBeginTime);
        $("#competitionApplyEndTime").val(data.competitionApplyEndTime);

        $("#competitionProblemIds").val(data.competitionProblemIds);
        $("#competitionProblemCount").val(data.competitionProblemCount);

        layui.form.render("radio");
    }
}