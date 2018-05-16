<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>学生作业详情</title>
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
    <link rel="stylesheet" href="../assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="../assets/css/admin.css">
    <script src="../assets/js/jquery.min.js"></script>
</head>

<body>
<c:if test="${taskSubmitDetail.size()==0}">
    <script>
        window.close();
    </script>
</c:if>

<header class="am-topbar admin-header">
    <div style="margin-top: 15px;margin: 0 auto;width:300px;height:40px;">
        <b style="font-size: 30px">${studentBasic.taskName}</b>
    </div>
</header>
<div class="nav-navicon admin-main admin-sidebar"
     style="margin-top:-45px;background-color: #f0f0f0">
    <div style="background-color:#2a3542 ;border-radius:5px;height:35px;">
        <label style="font-size: 20px;margin:0px 0px 0px 55px ;color: white">页面导航</label>
    </div>
    <div style="background-color: #f0f0f0">
        <label style="font-size:18px;margin-top: 10px">学生信息</label>
        <hr style="height:1px;border:none;border-top:1px solid #555555;" />
    </div>
    <div style="background-color: #f0f0f0">
        <ul style="margin-left: -20px;margin-bottom: 10px">
            <li style="list-style-type: none;margin-top: 10px">
                <font style="font-size:15px;font-weight: bolder;">
                    学号&nbsp;&nbsp;&nbsp;
                </font>
                <font style="font-size:15px;">
                    ${studentBasic.sno}
                </font>
            </li>
            <li style="list-style-type: none;margin-top: 10px">
                <font style="font-size:15px;font-weight: bolder;">
                    姓名&nbsp;&nbsp;&nbsp;
                </font>
                <font style="font-size:15px;">
                    ${studentBasic.sname}
                </font>
            </li>
            <li style="list-style-type: none;margin-top: 10px">
                <font style="font-size:15px;font-weight: bolder;">
                    班级&nbsp;&nbsp;&nbsp;
                </font>
                <font style="font-size:15px;">
                    ${studentBasic.sclass}
                </font>
            </li>
        </ul>
    </div>
    <div style="background-color: #f0f0f0;margin-top: 20px">
        <label style="font-size:18px;">作业信息</label>
        <hr style="height:1px;border:none;border-top:1px solid #555555;" />
    </div>
    <div style="background-color: #f0f0f0">
        <li style="list-style-type: none;font-size:15px;color:	#1E90FF;">所属课程：${studentBasic.courseName}</li>
        <li style="list-style-type: none;font-size:15px;color:	#1E90FF;">所属章节：${studentBasic.taskChapter}</li>
        <li style="list-style-type: none;font-size:15px;color:	#1E90FF;">作业附件：
            <c:choose>
                <c:when test="${studentBasic.sappendixes != null && studentBasic.sappendixes != ''}">
                   <a href="#">下载</a>
                </c:when>
                <c:otherwise>
                    无
                </c:otherwise>
            </c:choose>
        </li>
    </div>
    <div style="text-align: right;margin-top: 10%">
        <p style="font-size: 20px;font-weight: bold;margin-right: 10%">分数：<span id="totalScore">${studentBasic.taskGrade}</span></p>
    </div>
</div>
<div class=" admin-content">
    <div class="admin-biaogelist" style="background-color: white">
        <div class="fbneirong">
            <c:forEach var="question" items="${taskSubmitDetail}" varStatus="no">
                <div name="replaceQuestionDiv"
                     id="replaceQuestion${no.count}">
                    <div class="am-panel am-panel-default"
                         style="max-width:800px;margin-left: 100px">
                        <div id="Question${no.count}">
                            <div class="am-panel-hd">
                                <b style="font-size: 18px"> ${no.count}.</b>
                                (${question.qtype})
                                <c:choose>
                                    <c:when test="${question.qtype == '单选题' || question.qtype == '多选题' || question.qtype == '填空题' || question.qtype == '判断题' || question.qtype == '简答题'}">
                                        ${question.qcontent}
                                    </c:when>
                                    <c:otherwise>
                                        ${question.qdescribe}
                                    </c:otherwise>
                                </c:choose>
                                (${question.score}分)
                            </div>
                            <div class="am-panel-bd">

                                <c:choose>
                                    <c:when test="${question.qtype == '单选题' || question.qtype == '多选题' || question.qtype == '填空题' || question.qtype == '判断题' || question.qtype == '简答题'}">
                                        <c:if test='${question.qtype=="单选题" || question.qtype=="多选题"}'>
                                            <c:forEach items="${question.choice}" var="choice">
                                                <c:if test='${choice.trim()!=""}'>
                                                    <li type=A>${choice}<br></li>
                                                </c:if>
                                            </c:forEach>
                                            <a><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>
                                            <a onCilck="" href="">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：</a>
                                            <b style="font-size: 15px">${question.qanswer}</b>
                                            <br>
                                            <a onCilck="" href="">学生答案：</a>
                                            <b style="font-size: 15px"> ${question.answer}</b>
                                            <c:choose>
                                                <c:when test="${question.qanswer == question.answer }">
                                                    <img alt="" src="../assets/images/true.jpg" width=30px heigth=20px>
                                                    <strong>得分：${question.score}</strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <img alt="" src="../assets/images/false.jpg" width=30px heigth=20px>
                                                    <strong>得分：0.0</strong>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                        <c:if test='${question.qtype=="判断题"}'>
                                            <a onCilck="" href="">答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：</a>
                                            <b style="font-size: 15px"> ${question.qanswer}</b>
                                            <br>
                                            <a onCilck="" href="">学生答案：</a>
                                            <b style="font-size: 15px">${question.answer}</b>
                                            <c:choose>
                                                <c:when test="${question.qanswer == question.answer }">
                                                    <img alt="" src="../assets/images/true.jpg" width=30px heigth=20px>
                                                    <strong>得分：${question.score}</strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <img alt="" src="../assets/images/false.jpg" width=30px heigth=20px>
                                                    <strong>得分：0.0</strong>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                        <c:if test='${question.qtype=="填空题"}'>
                                            <a onCilck="" href="">参考答案：</a>
                                            <b style="font-size: 15px"> ${question.qanswer}</b>
                                            <br>
                                            <a onCilck="" href="">学生答案：</a>
                                            <div style="border:1px thin red;display: inline-block;margin-bottom: 20px">
                                                <b style="font-size: 15px">${question.answer}
                                                </b>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${question.grade != null}">
                                                        <strong>得分：${question.grade}</strong>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" value="${question.qid}" name="qid">
                                                        <input type="hidden" value="${question.qtype}" name="qtype">
                                                        <b>评分：</b>
                                                        <input type="number" name="short_answer_score"
                                                               onBlur="short_answer_score(${question.score},this)">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </c:if>
                                        <c:if test='${question.qtype=="简答题"}'>
                                            <a onCilck="" href="">学生答案：</a>
                                            <div style="border:1px thin red;display: inline-block;margin-bottom: 20px">
                                                <b style="font-size: 15px">${question.answer}</b>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${question.grade != null}">
                                                        <strong>得分：${question.grade}</strong>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" value="${question.qid}" name="qid">
                                                        <input type="hidden" value="${question.qtype}" name="qtype">
                                                        <b>评分：</b>
                                                        <input type="number" name="short_answer_score"
                                                               onBlur="short_answer_score(${question.score},this)">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <a onCilck="" href="">学生答案：</a>
                                        <div style="border:1px thin red;display: inline-block;margin-bottom: 20px">
                                            <b style="font-size: 15px">${question.answer}
                                            </b>
                                        </div>
                                        <div>
                                            <c:choose>
                                                <c:when test="${question.grade != ''&&question.grade != null}">
                                                    <strong>得分：${question.grade}</strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" value="${question.qid}" name="qid">
                                                    <input type="hidden" value="${question.qtype}" name="qtype">
                                                    <b>评分：</b>
                                                    <input type="number" name="short_answer_score" onBlur="short_answer_score(${question.score},this)">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
            </c:forEach>
            <div style="margin-left: 30%">
                <button type="button" class="am-btn am-btn-primary am-round" id="saveTaskGrade">确定</button>
                <button type="button" class="am-btn am-btn-primary am-round">取消</button>
            </div>
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
</div>

<script language="javascript" src="../assets/js/huixian.js"></script>
<script language="javascript" src="../assets/js/amazeui.js"></script>
<script type="text/javascript">
    function short_answer_score(score,obj){
        var newScore=obj.value*1;
        var total=0;
        if(newScore<0){
            obj.value=0;
        }else if(newScore>score*1){
            obj.value=score;
        }
        var arr=document.getElementsByName("short_answer_score");
        for(var i=0;i<arr.length;i++){
            total=total+arr[i].value*1;
        }
        document.getElementById("totalScore").innerText=total+${studentBasic.taskGrade}*1;
    }
</script>
<script type="text/javascript">
    $("#saveTaskGrade").click(function(){
        var qids=new Array();
        var arrIndex=new Array();
        var types=new Array();
        var scores=new Array();
        var totalScore=$("#totalScore").text();
        var id="${studentBasic.id}";

        $("input[name='short_answer_score']").each(function(index,el){
            if(el.value!=""){
                scores.push(el.value);
                arrIndex.push(index);
            }
        });
        $("input[name='qid']").each(function(index,el){
            for(var i=0;i<arrIndex.length;i++){
                if(arrIndex[i]==index){
                    qids.push(el.value);
                }
            }
        });
        $("input[name='qtype']").each(function(index,el){
            for(var i=0;i<arrIndex.length;i++){
                if(arrIndex[i]==index){
                    types.push(el.value);
                }
            }
        });
        console.log(qids);
        console.log(scores);
        console.log(types);
        if(qids.length<=0){
            qids=null;
        }
        if(scores.length<=0){
            scores=null;
        }
        if(types.length<=0){
            types=null;
        }
        $.ajax({
            type : "POST",
            url : "/page/saveSubmitTaskDetail",
            data : {"qids":qids,"scores":scores,"types":types,"id":id,"totalScore":totalScore},
            dataType : "json",
            success:function(data){
                console.log(data);
                if(data.code==200){
                    window.location.reload();
                }else{
                    alert("保存失败！");
                }
            }
        });
    });
</script>
</body>
</html>