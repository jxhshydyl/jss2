$(function() {
	//渲染表格
    console.log("question");
	layui.table.render({
		elem : '#table',
		url : 'api/question',
 		where: {
	  		token : getToken()
		},
		page: true,
		cols: [[
			{type:'numbers'},
			{field:'qtype', sort: true, title: '类型'},
			{field:'qcontent', sort: true, title: '内容'},
			{field:'qchoice', sort: true, title: '选项'},
			{field:'qanswer', sort: true, title: '答案'},
			{field:'qdegree', sort: true,title: '难易程度'},
            {field:'cname', sort: true,title: '课程名称'},
            {field:'qchapter', sort: true,title: '章节'},
            {field:'qparagraph', sort: true,title: '节'},
			{align:'center', toolbar: '#barTpl', title: '操作'}
    	]]
	});
    console.log("question1");
	//添加按钮点击事件

    //增加题目信息
    layui.use(['form', 'layedit'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;
        var $ = layui.$;
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');
        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });
        form.on('select(qtype)',function(data){
            var select=$("#qtype");
            var type=select.val();
            console.log(type);
            var checkboxs=document.getElementsByName("daan");
            if(type=='单选题'){
                $("#xuanze").css('display','');
                $("#panduan").css('display','none');
                $("input[type='checkbox']").attr('type','radio');
                /*for(var i=0;i<checkboxs.length;i++){
                    console.log(checkboxs[i]);
                    checkboxs[i].type='radio';
                }*/
            }else if(type=='多选题'){
                $("#xuanze").css('display','');
                $("#panduan").css('display','none');
                $("input[type='radio']").attr('type','checkbox');
                /*                        for(var i=0;i<checkboxs.length;i++){
                                            console.log(checkboxs[i]);
                                            checkboxs[i].type='checkbox';
                                        }*/
            }else if(type=='填空题' || type == '简答题'){
                $("#xuanze").css('display','none');
                $("#panduan").css('display','none');
            } else if(type=='判断题'){
                $("#xuanze").css('display','none');
                $("#panduan").css('display','');
            }
            form.render('radio');
            form.render('checkbox');
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            data.field.token = getToken();
            data.field._method = $("#editForm").attr("method");
            layer.load(1);
            $.ajax({
                url: "jss/addQuestion",
                type: "post",
				data:data.field,
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
            return false;
        });

        $("#reset").click(function(){
            $("#xuanze").css('display','');
            $("#showTable").css('display','');
            $("#showForm").css('display','none');
        });

        $("#addBtn").click(function(){
            var course=getCurrentCourse();
            console.log(course);
            html='';
            for(var i=0;i<course.length;i++){
                html+="<option value='"+course[i].courseName+"'>"+course[i].courseName+"</option>";
            }
            console.log(html);
            $("#course").html(html);
            $("#showTable").css('display','none');
            $("#showForm").css('display','');
            form.render();
        });
        form.render();
    });

    //修改题目信息
    layui.use(['form', 'layedit'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;
        var $ = layui.$;

        $("#resets").click(function(){
            $("#addQdescribe").val("");
            $("#addInputDescribe").val("");
            $("#addOutputDescripe").val("");
            $("#addExampleInput").val("");
            $("#addExampleOutput").val("");
            //createEdit(layedit);
            $("#showTable").css('display','');
            $("#showForm").css('display','none');
            $("#showQuestionDetail").css('display','none');
        });
        form.on('submit(demo2)', function(data){
            data.field.token = getToken();
            data.field._method = $("#editForm").attr("method");
            layer.load(1);
            $.ajax({
                url: "jss/addQuestion",
                type: "post",
                data:data.field,
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
            return false;
        });
        form.render();
    });
	//工具条点击事件
	layui.table.on('tool(table)', function(obj){
		var data = obj.data;
		var layEvent = obj.event;
		console.log(obj);
		if(layEvent === 'edit'){ //修改
            $("#showQuestionDetail").css("display","");
            $("#showTable").css("display","none");
            $("#showForm").css('display','none');
            showDataModel(data);
            showCharts(data);
            showEditModel(data);
			//showEditModel(data);
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

/*	//增加选项
	$("#addOptions").click(function(){
		console.log("zengjiaxuanxiang");
        showEditModel(null);
	});*/
});

function showCharts(data){
    var myChart = echarts.init(document.getElementById('chartStatistic'));
    // 指定图表的配置项和数据
    console.log(data);
    var legend = [""];
    var item = ['提交', '通过'];
    var data = [
        [data.totalSubmitCount, data.totalRightCount]
    ];
    //柱状图
    var option = {
        title: {
            text: '次数',
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
}


function showDataModel(data){
    var course=getCurrentCourse();
    html='';
    for(var i=0;i<course.length;i++){
        html+="<option value='"+course[i].courseName+"'>"+course[i].courseName+"</option>";
    }
    $("#courses1").html(html);
    $("#courses1").val(data.cname);
    $("#qchapter1").val(data.qchapter);
    $("#qparagraph1").val(data.qparagraph);
    $("#qtypes1").val(data.qtype);
    $("#LAY_demo_editors1").val(data.qcontent);
    if("单选题"==data.qtype){
        $("#xuanzes1").css("display","");
        $("#panduans1").css("display","none");
        var html='';
        var arr=data.qchoice.split(/[A-Z][、:：.]\s*/g);
        // var arr=data.qchoice.split(/[A-Z][|:|：|、|.| .| 、]/);
        var char=["A","B","C","D","E","F","G","H","I"];
        var k=0;
        console.log(arr);
        for(var i=0;i<arr.length;i++){
            if(arr[i]!==''){
                html+=`<tr name="xuanxianghangshu">
							<td>${char[k]}</td>
							<td><textarea name="answer${k+1}" style="resize:none;border:0;height:40px;width:100%;margin: 0 0" disabled>${arr[i]}</textarea></td>
							<td><input type="radio" name="daans" value="A" ></td>
						</tr>`;
                k++;
            }
        }
        $("#xuanxiang1").html(html);
        for(var i=0;i<char.length;i++){
            if(char[i]==data.qanswer.trim()){
                console.log(char[i]);
                $("input[name=daans]")[i].checked=true;
            }
        }
    }else if("多选题"==data.qtype){
        $("#xuanzes1").css("display","");
        $("#panduans1").css("display","none");
    }else if("判断题"==data.qtype){
        $("#xuanzes1").css("display","none");
        $("#panduans1").css("display","");
        console.log();
        if(data.qanswer==="true"){
            $("#daantrue1").attr("checked",true);
        }else{
            $("#daanfalse1").attr("checked",true);
        }

    }else{
        $("#xuanzes1").css("display","none");
        $("#panduans1").css("display","none");
    }
    layui.form.render();
}
function showEditModel(data){
    var course=getCurrentCourse();
    html='';
    for(var i=0;i<course.length;i++){
        html+="<option value='"+course[i].courseName+"'>"+course[i].courseName+"</option>";
    }
    $("#courses").html(html);
    $("#courses").val(data.cname);
    $("#qids").val(data.qid);
    $("#qchapters").val(data.qchapter);
    $("#qparagraphs").val(data.qparagraph);
    $("#qtypes").val(data.qtype);
    $("#LAY_demo_editors").val(data.qcontent);
    if("单选题"==data.qtype){
        $("#xuanzes").css("display","");
        $("#panduans").css("display","none");
        var html='';
        var arr=data.qchoice.split(/[A-Z][、:：.]\s*/g);
        var char=["A","B","C","D","E","F","G","H","I"];
        var k=0;
        console.log(arr);
        var k=0;
        for(var i=0;i<arr.length;i++){
            if(arr[i].trim()!==''){
                html+=`<tr name="xuanxianghangshu">
							<td>${char[k]}</td>
							<td><textarea style="resize:none;border:0;height:40px;width:100%;margin: 0 0" disabled>${arr[i]}</textarea></td>
							<td><input type="radio" name="daan" value="A" ></td>
						</tr>`;
                k++;
            }
        }
        $("#xuanxiang").html(html);
       for(var i=0;i<char.length;i++){
            if(char[i]==data.qanswer.trim()){
                console.log(char[i]);
                $("input[name=daan]")[i].checked=true;
            }
        }
    }else if("多选题"==data.qtype){
        $("#xuanzes1").css("display","");
        $("#panduans1").css("display","none");
    }else if("判断题"==data.qtype){
        $("#xuanzes1").css("display","none");
        $("#panduans1").css("display","");
        console.log();
        if(data.qanswer==="true"){
            $("#daantrue1").attr("checked",true);
        }else{
            $("#daanfalse1").attr("checked",true);
        }

    }else{
        $("#xuanzes1").css("display","none");
        $("#panduans1").css("display","none");
    }
    layui.form.render();
}

//删除
function doDelete(obj){
	layer.confirm('确定要删除吗？', function(index){
		console.log(index);
		layer.close(index);
		layer.load(1);
		$.ajax({
			url: "api/question/"+obj.data.qid+"?token="+getToken(),
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

//搜索
function doSearch(table){
	var key = $("#searchKey").val();
	var value = $("#searchValue").val();
	if (value=='') {
		key = '';
	}
	layui.table.reload('table', {where: {searchKey: key,searchValue: value}});
}