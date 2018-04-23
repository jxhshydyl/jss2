<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="../assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../assets/css/admin.css">
    <script src="../assets/js/jquery.min.js"></script>
</head>

<body>
<header class="am-topbar admin-header">
    <div style="margin-top: 15px; margin: 0 auto; width: 300px; height: 40px;">
        <b style="font-size: 30px">
          ${taskBasic.taskName}
        </b>
    </div>
</header>
<div class="nav-navicon admin-main admin-sidebar" style="margin-top: -45px; background-color: #f0f0f0;position:fixed;width:15%;z-index:2">
    <div style="background-color: #2a3542; border-radius: 5px; height: 35px;">
        <label style="font-size: 20px; margin: 0px 0px 0px 55px; color: white">页面导航</label>
    </div>
    <div style="background-color: #f0f0f0">
        <label style="font-size: 18px; margin-top: 10px">题目分数</label>
        <hr style="height: 1px; border: none; border-top: 1px solid #555555;"/>
    </div>
    <div style="background-color: #f0f0f0">
        <ul style="margin-left: -20px; margin-bottom: 10px">
            <c:forEach var="detail" items="${taskBasic.list}">
                <li style="list-style-type: none; margin-top: 10px">
                    <c:if test="${detail.types!=null && detail.types!='' }">
                        <font style="font-size: 15px;">
                                ${detail.types}&nbsp;&nbsp;&nbsp;
                        </font>
                        <font style="font-size: 15px;">
                                ${detail.count}题&nbsp;&nbsp;&nbsp;
                        </font>
                        <font style="font-size: 15px;">
                                ${detail.score}分
                        </font>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <div style="margin-left: 50%; font-size: 18px; display: inline;">
            <b>共${taskBasic.score}分
            </b>
        </div>
    </div>
    <div style="background-color: #f0f0f0; margin-top: 20px">
        <label style="font-size: 18px;">题目内容</label>
        <hr style="height: 1px; border: none; border-top: 1px solid #555555;"/>
    </div>
    <div style="background-color: #f0f0f0">
        <li style="list-style-type: none; font-size: 15px; color: #1E90FF;">所属课程：${taskBasic.cname}</li>
        <li style="list-style-type: none; font-size: 15px; color: #1E90FF;">所属章节：${taskBasic.chapter}</li>
    </div>
</div>
<div class=" admin-content">
    <div class="admin-biaogelist" style="background-color: white">
        <div class="fbneirong">
            <c:forEach var="question" items="${taskDetail}" varStatus="status" >
                <div name="replaceQuestionDiv" id="replaceQuestion<s:property value="#no.getCount()"/>">
                    <div class="am-panel am-panel-default" style="margin-left: 10%;margin-right: 10%">
                        <div id="Question${status.index+1}">
                            <div class="am-panel-hd">
                                <b style="font-size: 18px">${status.count}.</b>
                                (${question.qtype})
                                <c:choose>
                                    <c:when test="${question.qtype == '单选题' || question.qtype == '多选题' || question.qtype == '填空题' || question.qtype == '判断题' || question.qtype == '简答题'}">
                                        ${question.qcontent}
                                    </c:when>
                                    <c:otherwise>
                                        ${question.qdescribe}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="am-panel-bd">
                                <c:if test='${question.qtype=="单选题" || question.qtype=="多选题"}'>
                                    <c:forEach items="${question.choice}" var="choice">
                                        <c:if test='${choice.trim()!=""}'>
                                            <li type=A>${choice}<br></li>
                                        </c:if>
                                    </c:forEach>
                                    <a><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
                                    <a onCilck="" href="">答案：</a>
                                    <b style="font-size: 15px">${question.qanswer}</b>
                                </c:if>
                                <c:if test='${question.qtype=="判断题"}'>
                                    <a><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
                                    <a onCilck="" href="">答案：</a>
                                    <b style="font-size: 15px">${question.qanswer}</b>
                                </c:if>
                            </div>
                        </div>
                        <div style="display: block; margin-left: 80%; margin-bottom: 10px">
                            <button type="button" class="am-btn am-btn-default am-round"
                                    data-am-modal="{target: '#my-popup'}"
                                    style="border: 1px solid #555555"
                                    onClick="queryQuestion('${question.qtype}','${question.cname}','${question.qchapter}',${status.count},
                                        ${question.qid},${taskBasic.tid})">更换
                            </button>
                            <button type="button" class="am-btn am-btn-default am-round"
                                    style="border: 1px solid #555555;"
                                    onClick="deleteQuestion(${question.qid},${taskBasic.tid},'${question.qtype}')">删除
                            </button>
                        </div>
                    </div>
                    <br>
                </div>
            </c:forEach>
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
                    换一批
                </button>
            </div>
        </div>
        <div style="margin-top: 60px" id="replaceQuestion"></div>
    </div>
</div>

<script language="javascript" src="../assets/js/huixian.js"></script>
<script language="javascript" src="../assets/js/amazeui.js"></script>
<script type="text/javascript">
    function deleteQuestion(qid, tid,qtype) {
        console.log(qid);
        console.log(tid);
        console.log(qtype);
        if (window.confirm('您确定要删除吗？')) {
            $.ajax({
                type: "POST",
                url: "/page/deleteTaskQuestion",
                data: {"qid": qid,"tid":tid ,"qtype": qtype},
                dataType: "json",
                success: function (data) {
                    if(data.code==200){
                        window.location.reload();
                    }
                }
            });
        }
    }

    var questionList = new Array();//查询出的相似题目数组
    var num = 0;//更换的题目的序号
    var t = -1;

    function queryQuestion(qtype,cname, qchapter, no, qid, tid) {
        if (t != qid && t != -1) {
            qid = t;
        }
        console.log(qtype + qchapter + no);
        num = no;
        $.ajax({
            type: "POST",
            url: "/page/querySimilarQuestion",
            data: {"qtype": qtype,"cname":cname ,"qchapter": qchapter, "tid": tid},
            dataType: "json",
            success: function (d) {
                document.getElementById("replaceQuestion").innerHTML = "";

                console.log(d);

                questionList = d;
                document.getElementById("tihuan").innerHTML = "<label style='margin:10px 10px'>我们为您筛选了和当前题目类型一样的几道题：</label>" +
                    "<button class='am-btn am-btn-default am-round' onClick=\"queryQuestion('" + qtype + "','"+cname+"','" + qchapter + "'," + no + "," + qid + "," + tid + ")\" " +
                    "style='margin-left:20%;height:35px;margin-top: 5px;background-color: #F21B11;color:white;padding-top: 8px'>" +
                    "换一批</button>";
                for (var i = 0; i < d.list.length; i++) {
                    var str = "";
                    var str1 = "";
                    if (d.list[i].qtype == "单选题" || d.list[i].qtype == "多选题") {
                        var choice=d.list[i].qchoice.split(/[A-Z][、:：.]\s*/g);
                        for (var j = 0; j < choice.length; j++) {
                            if (choice[j].trim() != "") {
                                str += "<li type=A>" + choice[j] + "<br></li>";
                            }
                        }
                        str += "	<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>" +
                            "	<a onCilck='' href=''>答案：</a>" +
                            "	<b style='font-size: 15px'>" + d.list[i].qanswer + "</b>";
                    } else if (d.list[i].qtype == "判断题") {
                        str1 = "<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>" +
                            "	<a onCilck='' href=''>答案：</a>" +
                            "	<b style='font-size: 15px'>" + d.list[i].qanswer + "</b>";
                    }
                    var content;
                    if(d.list[i].qcontent==undefined){
                        content=d.list[i].qdescribe;
                    }else if(d.list[i].qdescribe==undefined){
                        content=d.list[i].qcontent;
                    }
                    var qparagraph="";
                    if(d.list[i].qparagraph==null){
                        qparagraph="";
                    }else if(d.list[i].qparagraph==undefined){
                        qparagraph="";
                    }else{
                        qparagraph=d.list[i].qparagraph;
                    }
                    document.getElementById("replaceQuestion").innerHTML +=
                        "<div class='am-panel am-panel-default'" +
                        "style='max-width:800px;margin-left: 10px;margin-right: 10px'>" +
                        "<div>" +
                        "<div style='display:inline;margin-left: 5px'>" +
                        "	<label style='color:blue;'>" + d.list[i].qchapter + "</label>" +
                        "</div>" +
                        "<div style='display:inline;margin-left: 5px'>"+qparagraph+"</div>" +
                        "<button type='submit'  class='am-btn am-btn-default am-round' onClick='replaceQuestion(" + i + "," + d.list[i].qid + "," + qid + "," + tid + ")' " +
                        "style='border: 1px solid #555555;height:35px;width:70px;margin-left:65%;margin-top: 5px'>替换</button>" +
                        "</div>" +
                        "<div class='am-panel-bd'>" +
                        "	<b style='font-size: 18px'>" + (i * 1 + 1) + ".</b>(" +
                        "" + d.list[i].qtype + "" +
                        "	)" +
                        "" + content + "" +
                        "" + str + "" +
                        "" + str1 + "" +
                        "</div>" +
                        "</div>" +
                        "<br>";
                }
            }
        });
    }

    /**
     *i 序号
     */
    function replaceQuestion(i, newQid, oldQid, tid) {
        if (window.confirm("是否替换？")) {
            var str = "";
            var str1 = "";
            if (questionList.list[i].qtype == "单选题" || questionList.list[i].qtype == "多选题") {
                var choice=questionList.list[i].qchoice.split(/[A-Z][、:：.]\s*/g);
                for (var j = 0; j < choice.length; j++) {
                    if (choice[j].trim() != "") {
                        str += "<li type=A>" + choice[j] + "<br></li>";
                    }
                }
                str += "	<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>" +
                    "	<a onCilck='' href=''>答案：</a>" +
                    "	<b style='font-size: 15px'>" + questionList.list[i].qanswer + "</b>";
            } else if (questionList.list[i].qtype == "判断题") {
                str1 = "<a><i class='fa fa-angle-double-up' aria-hidden='true'></i></a>" +
                    "	<a onCilck='' href=''>答案：</a>" +
                    "	<b style='font-size: 15px'>" + questionList.list[i].qanswer + "</b>";
            }
            var content;
            if(questionList.list[i].qcontent==undefined){
                content=questionList.list[i].qdescribe;
            }else if(questionList.list[i].qdescribe==undefined){
                content=questionList.list[i].qcontent;
            }

            var html = "<div class='am-panel-hd'>" +
                "<b style='font-size: 18px'>" + (num) + ".</b>" +
                "(" + questionList.list[i].qtype + ")" +
                "" + content+ "" +
                "</div>" +
                "<div class='am-panel-bd'>" +
                "" + str + "" +
                "" + str1 + "" +
                "</div>";
            set_innerHTML("Question" + num + "", html);
            //document.getElementsByName("replaceQuestionDiv")[num*1-1].
            $.ajax({
                type: "POST",
                url: "/page/replaceSilimarQuestion",
                data: {"newQid": newQid, "oldQid": oldQid, "tid": tid},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        t = newQid;
                    }
                }
            });
            var $modal = $('#my-popup');
            $modal.modal('close');
        }
    }
</script>
</body>
</html>