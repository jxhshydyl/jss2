<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>作业</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="../assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed"
          href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="../assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="../assets/css/admin.css">
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/app.js"></script>
</head>
<body>
<!--[if lte IE 9]><p class="browsehappy">升级你的浏览器吧！ <a href="http://se.360.cn/" target="_blank">升级浏览器</a>以获得更好的体验！</p><![endif]-->
</head>

<body>
<header class="am-topbar admin-header">
    <div
            style="margin-top: 15px; margin: 0 auto; width: 300px; height: 40px;">
        <b style="font-size: 30px"><s:property
                value="#request['task'].task_name" /></b>
    </div>
</header>
<div class="nav-navicon admin-main admin-sidebar"
     style="margin-top: -45px; background-color: #f0f0f0;position:fixed;width:15%;z-index:2">
    <div
            style="background-color: #2a3542; border-radius: 5px; height: 35px;">
        <label
                style="font-size: 20px; margin: 0px 0px 0px 55px; color: white">页面导航</label>
    </div>
    <div style="background-color: #f0f0f0">
        <label style="font-size: 18px; margin-top: 10px">题目分数</label>
        <hr style="height: 1px; border: none; border-top: 1px solid #555555;" />
    </div>
    <div style="background-color: #f0f0f0">
        <ul style="margin-left: -20px; margin-bottom: 10px">
            <s:iterator value="#request['detail']" id="detail">
                <li style="list-style-type: none; margin-top: 10px"><font
                        style="font-size: 15px;"> <s:property value="#detail.type" />&nbsp;&nbsp;&nbsp;
                </font> <font style="font-size: 15px;"> <s:property
                        value="#detail.count" />题&nbsp;&nbsp;&nbsp;
                </font> <font style="font-size: 15px;"> <s:property
                        value="#detail.score" />分
                </font></li>
            </s:iterator>
        </ul>
        <div style="margin-left: 50%; font-size: 18px; display: inline;">
            <b>共<s:property value="#request['task'].tscore" />分
            </b>
        </div>
    </div>
    <div style="background-color: #f0f0f0; margin-top: 20px">
        <label style="font-size: 18px;">题目内容</label>
        <hr style="height: 1px; border: none; border-top: 1px solid #555555;" />
    </div>
    <div style="background-color: #f0f0f0">
        <li style="list-style-type: none; font-size: 15px; color: #1E90FF;">所属课程：<s:property
                value="#request['task'].cno" /></li>
        <li style="list-style-type: none; font-size: 15px; color: #1E90FF;">所属章节：<s:property
                value="#request['task'].tchapter" /></li>
    </div>
</div>
<div class=" admin-content">
    <div class="admin-biaogelist" style="background-color: white">
        <div class="fbneirong">
            <s:iterator value="#request['question']" id="question" status="no">
                <div name="replaceQuestionDiv"
                     id="replaceQuestion<s:property value="#no.getCount()"/>">
                    <div class="am-panel am-panel-default"
                         style="margin-left: 10%;margin-right: 10%">
                        <div id="Question<s:property value="#no.getCount()"/>">
                            <div class="am-panel-hd">
                                <b style="font-size: 18px"> <s:property
                                        value="#no.getCount()" />.
                                </b> (
                                <s:property value="#question.Qtype" />
                                )
                                <s:property value="#question.Qcontent" />
                            </div>
                            <div class="am-panel-bd">
                                <s:if test="#question.Qtype=='单选题' || #question.Qtype=='多选题'">
                                    <s:iterator value="#question.choice" id="choice">
                                        <s:if test='#choice.trim()!="" && #choice!="\n"'>
                                            <li type=A><s:property value="#choice" /><br></li>
                                        </s:if>
                                    </s:iterator>
                                    <a><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
                                    <a onCilck="" href="">答案：</a>
                                    <b style="font-size: 15px"> <s:property
                                            value="#question.Qanswer" />
                                    </b>
                                </s:if>
                                <s:elseif test='#question.Qtype=="判断题"'>
                                    <a><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
                                    <a onCilck="" href="">答案：</a>
                                    <b style="font-size: 15px"> <s:property
                                            value="#question.Qanswer" />
                                    </b>
                                </s:elseif>
                            </div>
                        </div>
                        <div
                                style="display: block; margin-left: 80%; margin-bottom: 10px">
                            <button type="button" class="am-btn am-btn-default am-round"
                                    data-am-modal="{target: '#my-popup'}"
                                    style="border: 1px solid #555555"
                                    onClick="queryQuestion('<s:property value="#question.qtype"/>','<s:property value="#question.qchapter"/>',<s:property value="#no.getCount()"/>,<s:property value="#question.Qid"/>,<s:property value="#request['task'].Tid"/>)">更换
                            </button>
                            <button type="button" class="am-btn am-btn-default am-round"
                                    style="border: 1px solid #555555;"
                                    onClick="deleteQuestion(<s:property value="#question.Qid"/>,<s:property value="#request['task'].Tid"/>)">删除
                            </button>
                        </div>
                    </div>
                    <br>
                </div>
            </s:iterator>
        </div>
        <div class="foods">
            <ul>版权所有@2015
            </ul>
            <dl>
                <a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
            </dl>
        </div>
    </div>
</div>

<div class="am-popup" id="my-popup" style="width: 45%;">
    <div class="am-popup-inner">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">换一题</h4>
            <span data-am-modal-close class="am-close">&times;</span>
            <div style="background-color: #f0f0f0; height: 50px;" id="tihuan">
                <label style="margin: 10px 10px">我们为您筛选了和当前题目类型一样的几道题：</label>
                <button class="am-btn am-btn-default am-round"
                        style="margin-left: 20%; height: 35px; margin-top: 5px; background-color: #F21B11; color: white; padding-top: 8px">
                    换一批</button>
            </div>
        </div>
        <div style="margin-top: 60px" id="replaceQuestion"></div>
    </div>
</div>

<!--[if (gte IE 9)|!(IE)]><!-->
<%-- <script src="../assets/js/amazeui.min.js"></script> --%>
<!--<![endif]-->
<script language="javascript" src="../dayin/jquery-1.4.4.min.js"></script>
<script language="javascript">
    function aa(){
        $("#dayin").jqprint();
    }
</script>
<script language="javascript" src="../dayin/jquery.jqprint-0.3.js"></script>
<script language="javascript" src="../js/huixian.js"></script>
<script language="javascript" src="../js/jquery.js"></script>
<script language="javascript" src="../js/amazeui.js"></script>
<script type="text/javascript">
    function deleteQuestion(qid,tid){
        console.log(qid);
        console.log(tid);
        if(window.confirm('您确定要删除吗？')){
            window.location.href="TaskDetail-deleteTaskQuestion.action?qid="+qid+"&tid="+tid;
        }
    }
    var questionList=new Array();//查询出的相似题目数组
    var num=0;//更换的题目的序号
    var t=-1;
    function queryQuestion(qtype,qchapter,no,qid,tid){
        if(t!=qid&&t!=-1){
            qid=t;
        }
        console.log(qtype+qchapter+no);
        num=no;
        $.ajax({
            type : "POST",
            url : "TaskDetail-querySilimarQuestion.action",
            data : {"qtype":qtype,"qchapter":qchapter,"tid":tid},
            dataType : "json",
            success : function(data) {
                document.getElementById("replaceQuestion").innerHTML="";
                var d = eval("("+data+")");
                /* var newdata = JSON.parse(data);
                var str1 = newdata[0].hidden;
                if (str1 === "1") {
                    alert("用户名或密码错误！");
                }else{
                    location.href = "./index.jsp";
                } */
                questionList=d;
                document.getElementById("tihuan").innerHTML="<label style='margin:10px 10px'>我们为您筛选了和当前题目类型一样的几道题：</label>"+
                    "<button class='am-btn am-btn-default am-round' onClick=\"queryQuestion('"+qtype+"','"+qchapter+"',"+no+","+qid+","+tid+")\" "+
                    "style='margin-left:20%;height:35px;margin-top: 5px;background-color: #F21B11;color:white;padding-top: 8px'>"+
                    "换一批</button>";
                for(var i=0;i<d.list.length;i++){
                    var str="";
                    var str1="";
                    if(d.list[i].qtype=="单选题"||d.list[i].qtype=="多选题"){
                        for(var j=0;j<d.list[i].choice.length;j++){
                            if(d.list[i].choice[j].trim()!=""){
                                str+="<li type=A>"+d.list[i].choice[j]+"<br></li>";
                            }
                        }
                        str+="	<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>"+
                            "	<a onCilck='' href=''>答案：</a>"+
                            "	<b style='font-size: 15px'>"+d.list[i].qanswer+"</b>";
                    }else if(d.list[i].qtype=="判断题"){
                        str1="<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>"+
                            "	<a onCilck='' href=''>答案：</a>"+
                            "	<b style='font-size: 15px'>"+d.list[i].qanswer+"</b>";
                    }
                    document.getElementById("replaceQuestion").innerHTML+=
                        "<div class='am-panel am-panel-default'"+
                        "style='max-width:800px;margin-left: 10px;margin-right: 10px'>"+
                        "<div>"+
                        "<div style='display:inline;margin-left: 5px'>"+
                        "	<label style='color:blue;'>"+d.list[i].qchapter+"</label>"+
                        "</div>"+
                        "<div style='display:inline;margin-left: 5px'>第一节</div>"+
                        "<button type='submit'  class='am-btn am-btn-default am-round' onClick='replaceQuestion("+i+","+d.list[i].qid+","+qid+","+tid+")' "+
                        "style='border: 1px solid #555555;height:35px;width:70px;margin-left:65%;margin-top: 5px'>替换</button>"+
                        "</div>"+
                        "<div class='am-panel-bd'>"+
                        "	<b style='font-size: 18px'>"+(i*1+1)+".</b>("+
                        ""+d.list[i].qtype+""+
                        "	)"+
                        ""+d.list[i].qcontent+""+
                        ""+str+""+
                        ""+str1+""+
                        "</div>"+
                        "</div>"+
                        "<br>";
                }
            }
        });
    }
    /**
     *i 序号
     */
    function replaceQuestion(i,newQid,oldQid,tid){
        if(window.confirm("是否替换？")){
            var str="";
            var str1="";
            if(questionList.list[i].qtype=="单选题"||questionList.list[i].qtype=="多选题"){
                for(var j=0;j<questionList.list[i].choice.length;j++){
                    if(questionList.list[i].choice[j].trim()!=""){
                        str+="<li type=A>"+questionList.list[i].choice[j]+"<br></li>";
                    }
                }
                str+="	<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>"+
                    "	<a onCilck='' href=''>答案：</a>"+
                    "	<b style='font-size: 15px'>"+questionList.list[i].qanswer+"</b>";
            }else if(questionList.list[i].qtype=="判断题"){
                str1="<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>"+
                    "	<a onCilck='' href=''>答案：</a>"+
                    "	<b style='font-size: 15px'>"+questionList.list[i].qanswer+"</b>";
            }


            var html="<div class='am-panel-hd'>"+
                "<b style='font-size: 18px'>"+(num)+".</b>"+
                "("+questionList.list[i].qtype+")"+
                ""+questionList.list[i].qcontent+""+
                "</div>"+
                "<div class='am-panel-bd'>"+
                ""+str+""+
                ""+str1+""+
                "</div>";
            set_innerHTML("Question"+num+"",html);
            //document.getElementsByName("replaceQuestionDiv")[num*1-1].
            $.ajax({
                type : "POST",
                url : "TaskDetail-replaceSilimarQuestion.action",
                data : {"newQid":newQid,"qid":oldQid,"tid":tid},
                dataType : "json",
                success : function(data) {
                    var d = eval("("+data+")");
                    /* var newdata = JSON.parse(data);
                    var str1 = newdata[0].hidden;
                    if (str1 === "1") {
                        alert("用户名或密码错误！");
                    }else{
                        location.href = "./index.jsp";
                    } */
                    if(d.isSuccess==true){
                        t=newQid;
                        //window.location.href="TaskDetail-queryTaskDetail.action?tid="+tid;
                    }
                }
            });
            var $modal = $('#my-popup');
            $modal.modal('close');
        }
        // window.location.reload();
        /*$(".btn1").click(function(){
              $("p").html("Hello <b>world</b>!");
            }); */
        //$("#replaceQuestion"+num+"").html(html);
        //$("div[name=replaceQuestionDiv]")[num*1-1].html(html);
    }
</script>
</body>
</html>