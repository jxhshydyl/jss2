var myChart = echarts.init(document.getElementById('chartStatistic'));
$(function() {
	//渲染表格
	layui.table.render({
		elem : '#table',
		url : 'api/code',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'qname', sort: true, title: '名称'},
			{field:'qdegree', sort: true, title: '难易程度'},
			{field:'qtype', sort: true, title: '类型'},
            {field:'cname', sort: true,title: '所属课程'},
            {field:'qchapter', sort: true,title: '所属章节'},
			{field:'limitTime', sort: true, title: '限制时间'},
			{field:'limitMemory', sort: true,title: '限制内存'},
			{align:'center', toolbar: '#barTpl', title: '操作'}
    	]]
	});
	//添加按钮点击事件

	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
		console.log(obj);
		if(layEvent === 'edit'){ //修改
            $("#showQuestionDetail").css("display","");
            $("#showTable").css("display","none");
            $("#showForm").css('display','none');
			showQuestionDetail(data);
			showCharts(data);
			editQuestion(data);
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



    layui.use(['form', 'layedit'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;
        var $ = layui.$;
        //createEdit(layedit);

        $("#resets").click(function(){
            $("#addQdescribe").val("");
            $("#addInputDescribe").val("");
            $("#addOutputDescripe").val("");
            $("#addExampleInput").val("");
            $("#addExampleOutput").val("");
            createEdit(layedit);
            $("#showTable").css('display','');
            $("#showForm").css('display','none');
            $("#showQuestionDetail").css('display','none');
        });
        form.render();
    });
});

//显示表单弹窗
function showQuestionDetail(data){
	console.log(data);
	if(data!=null){
		$("#qname").html(data.qname);
		$("#qdescribe").val(data.qdescribe);
		$("#inputDescribe").val(data.inputDescribe);
		$("#limitMemory").html(data.limitMemory);
        $("#limitTime").html(data.limitTime);
        $("#outputDescripe").val(data.outputDescripe);
        $("#exampleInput").val(data.exampleInput);
        $("#exampleOutput").val(data.exampleOutput);
        $("#qdegrees").html(data.qdegree);
        $("#limitTimes").html(data.limitTime+"ms");
        $("#limitMemorys").html(data.limitMemory+"KB");
        $("#cnames").html(data.cname);
        $("#chapter").html(data.qchapter);
        $("#paragraph").html(data.qparagraph);
	}
}
function createSelectCourse(){
    var course=getCurrentCourse();
    html='';
    for(var i=0;i<course.length;i++){
        html+="<option value='"+course[i].courseName+"'>"+course[i].courseName+"</option>";
    }
    $("#courses").html(html);
}
function editQuestion(data){
    createSelectCourse();
    $("#addQnames").val(data.qname);
    $("#qid").val(data.qid);
    $("#addQdescribes").val(data.qdescribe);
    $("#addInputDescribes").val(data.inputDescribe);
    $("#addlimitMemorys").val(data.limitMemory);
    $("#addlimitTimes").val(data.limitTime);
    $("#addOutputDescripes").val(data.outputDescripe);
    $("#addExampleInputs").val(data.exampleInput);
    $("#addExampleOutputs").val(data.exampleOutput);
    $("#addqdegrees").val(data.qdegree);
    $("#qchapters").val(data.qchapter);
    $("#qparagraphs").val(data.qparagraph);
    $("#courses").val(data.cname);
    layui.form.render();
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		console.log(index);
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/code/"+obj.data.qid+"?token="+getToken(),
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
function showCharts(data){
    $.ajax({
        url: "jss/addQuestion/evaluateStatistics",
        type: "post",
        data:{"qid":data.qid},
        dataType: "JSON",
        success: function(data){
            layer.closeAll('loading');
            console.log(data);
            if(data.code==200){
                // 指定图表的配置项和数据
                var legend = ["oj系统","作业系统"];
                var item = ['提交', '通过'];
                var data = [
                    [data.myRecord.totalSubmitCount, data.myRecord.rightSubmitCount], //Berlin
                    [data.codes[0].totalSubmitCount, data.codes[0].totalRightCount]
                ];
                //柱状图
                var option = {
                    title: {
                        text: '编程题',
                    },
                    // 提示框，鼠标悬浮交互时的信息提示
                    tooltip: {
                        show: true,
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    // 图例
                                    legend: {
                                        data: legend
                                    },
                    // 横轴坐标轴
                    xAxis: [{
                        type: 'category',
                        data: item
                    }],
                    // 纵轴坐标轴
                    yAxis: [{
                        type: 'value'
                    }],
                    // 数据内容数组
                    series: function () {
                        var serie = [];
                        for (var i = 0; i < legend.length; i++) {
                            var item = {
                                name: legend[i],
                                type: 'bar',
                                barWidth: '40%',
                                data: data[i]
                            };
                            serie.push(item);
                        }
                        return serie;
                    }()
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }else{
                layer.msg(data.msg,{icon: 2});
            }
        }
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
$("#formSubmit").click(function(){
	var data=$("#addQuestion").serialize();
    $.ajax({
        url: "jss/addQuestion/code",
        type: "post",
        data:data,
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
});