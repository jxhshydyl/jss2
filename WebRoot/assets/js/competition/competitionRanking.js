$(function() {
    $.ajax({
        url: "competition/queryEndedCompetition",
        type: "POST",
        dataType: "JSON",
        async: false,
        success: function(data){
            var html='';
            var status='';
            var date = new Date(getNowFormatDate());
            for(var i=0;i<data.competition.length;i++){
                if(new Date(data.competition[i].competitionBeginTime) <date &&
                    new Date(data.competition[i].competitionEndTime)>date){
                    status="比赛正在进行";
                }
                if(new Date(data.competition[i].competitionEndTime) <date){
                    status="比赛已结束";
                }
                html+=`<fieldset class="layui-elem-field">
        <legend><a style="cursor: pointer;" name="competition" value="${data.competition[i].competitionId}"> ${data.competition[i].competitionName}</a></legend>
        <div class="layui-field-box">
            <fieldset class="layui-elem-field">
                <div class="layui-field-box">
                    <div>
                        <label style="">开始时间：${data.competition[i].competitionBeginTime}</label>
                        <label style="">结束时间：${data.competition[i].competitionEndTime}</label>
                    </div>
                </div>
            </fieldset>
            <div class="layui-field-box">
                <div>
                    <label style="">比赛状态：${status}</label>
                </div>
            </div>
            <div class="layui-field-box">
                <div>
                    <label style="">${data.competition[i].competitionDescription}</label>
                </div>
            </div>
        </div>
    </fieldset>`;
            }
            $("#showEndCompetition").html(html);
        }
    });

    $("a[name=competition]").each(function(index,ele){
        $(this).click(function(){
            $("#showEndCompetition").css("display","none");
            $("#showCompetitionRanking").css("display","");
            $.ajax({
                url: "competitionRanking/queryCompetitionRanking",
                type: "POST",
                data: {"competitionId":$(this).attr("value")},
                dataType: "JSON",
                success: function(data){
                    console.log(data);
                    if(data.length>0){
                        $("#noData").css("display","none");
                        $("#table").css("display","");
                        var hangshu=data.length;
                        var leishu=data[0].competitionProblemCount;
                        var theadTh='';
                        var tbodyTr='';
                        var tbodyTd='';
                        for(var i=0;i<leishu;i++){
                            theadTh+="<th>"+(i+1)+"</th>";
                        }
                        $("#thead").html("<tr id=\"totalTR\">\n" +
                            "               <th>名次</th>\n" +
                            "                <th>用户名</th>\n" +
                            "                 <th>通过题目数量</th>\n" +
                            "                 <th>总时间</th>\n" +
                            "                  "+theadTh+"" +
                            "                </tr>");

                        for(var i=0;i<hangshu;i++){
                            tbodyTd='';
                            for(var j=0;j<leishu;j++){
                                tbodyTd+="<td></td>";
                            }
                            tbodyTr+="<tr>\n" +
                                "    <td>"+(i+1)+"</td>\n" +
                                "    <td>"+data[i].nickname+"</td>\n" +
                                "    <td>"+data[i].totalCount+"</td>\n" +
                                "    <td>"+data[i].totalTime+"</td>\n" +
                                "     "+tbodyTd+"" +
                                "     </tr>";
                        }
                        console.log(tbodyTr);
                        $("#tbody").html(tbodyTr);
                        for(var i=0;i<hangshu;i++){
                            for(var j=0;j<leishu;j++){
                                var num=data[i].problemSubmitInfos.length;
                                for(var k=0;k<num;k++){
                                    var competitionPeoblemNumber=data[i].problemSubmitInfos[k].competitionPeoblemNumber;
                                    if(competitionPeoblemNumber==j){
                                        var _tab = document.getElementById('table'); // 获取table对象
                                        var _row = _tab.rows; //获取table的行
                                        var _cell = _row[(i+1)].cells; //获取第二行的列
                                        _cell[(4+j-1)].innerHTML=data[i].problemSubmitInfos[k].acceptedTime; //获取第三列的值
                                    }
                                }
                            }
                        }
                    }else{
                        $("#noData").css("display","");
                        $("#table").css("display","none");
                    }

                }
            });
        });
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

});
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
function competitiionDetail(){

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


