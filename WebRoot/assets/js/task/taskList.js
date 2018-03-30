$(function() {
	//渲染表格
    creatClassTable();
	
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
 
		if(layEvent === 'view'){//查看详情
            window.open('views/task/page.html');
            //creatStudentTable(data.cno);
		} else if(layEvent === 'share'){ //发布作业
            shareTask(data);
        } else if(layEvent === 'del'){ //删除
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
        url : 'api/task',
        where: {
            token : getToken(),
            tno:getCurrentUser().userId
        },
        page: true,
        cols: [[
            {type:'numbers'},
            {field:'taskName', sort: true, title: '作业名称'},
            {field:'tappendixes', sort: true, title: '作业附件'},
            {field:'cname', sort: true, title: '所属课程'},
            {field:'tchapter', sort: true, title: '所属章节'},
            {field:'subtime', sort: true, title: '创建时间'},
            {field:'tstate', sort: true,title: '状态'},
            {align:'center', toolbar: '#barTpl', minWidth: 180, title: '操作'}
        ]]
    });
}

//发布作业
function shareTask(obj){
    var tid=obj.tid;
    var tno=obj.tno;
    var str='';
    var clazz=getCurrentClass();
    for(var i=0;i<clazz.length;i++){
        str+= "<tr height='50px'>" +
                 "<td style=\"margin-left: 10px\"><input type='checkbox' name='cno' value='" + clazz[i].cno + "'></td>" +
                 "<td style=\"margin-left: 10px\">" +clazz[i].cname+ "</td>" +
                 "<td style=\"margin-left: 10px\">" +
                    "<input type='text' style='display: none' class='layui-input' id='test" + i + "' name='time' placeholder='yyyy-MM-dd HH:mm:ss'>" +
                 "</td>" +
            "</tr>";
    }
    layer.open({
        type: 1,
        title: "发布作业",
        shadeClose: true,
        area: ['550px','450px'],
        offset: '120px',
        content: $("#shareTask").html()
    });
    $("#shareTaskToClass").html(str);
    for(var i=0;i<clazz.length;i++){
        layui.use('laydate', function() {
            var laydate = layui.laydate;
            //时间选择器
            laydate.render({
                elem: '#test'+i,
                type: 'datetime'
            });
        });
    }
    layui.form.render();
    //$("#editForm")[0].reset();
    $("#editForm").attr("method","POST");
    $("#btnCancel").click(function(){
        layer.closeAll('page');
    });
    var time=document.getElementsByName("time");
    $("input[name=cno]").each(function(index,ele){
        $(ele).click(function(){
            if(this.checked){
                time[index].style.display='';
            }else{
                time[index].style.display='none';
            }
        });
    });

    $("#btnSubmit").click(function(){
        var data=$('#editForm').serialize();
        var cno='';
        var time='';
        var key=0;
        var num=0;
        var cnos= document.getElementsByName("cno");
        var times=document.getElementsByName("time");
        for(var i=0;i<cnos.length;i++){
            if(cnos[i].checked){
                cno=cno+","+cnos[i].value;
                key++;
            }
            if(times[i].value!=''){
                time=time+","+times[i].value;
                num++;
            }
        }
        if(key!=0&&num==key){
            $.ajax({
                type:'post',
                url:"/jss/task/shareTask",
                cache: false,
                data:{"tno":tno,"tid":tid,"cno":cno,"time":time},
                dataType:'json',
                success:function(data){
                    alert(data.msg);
                },
                error:function(){
                    alert("请求失败")
                }
            });
        }else{
            alert("请选择班级或时间！");
        }
    });
}
//删除
function doDelete(obj){
    console.log(obj.data);
	layer.confirm('确定要删除吗？', function(index){
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/task/"+obj.data.tid+"?token="+getToken(),
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
