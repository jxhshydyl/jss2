package com.wf.ew.grade.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;
import com.wf.ew.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequestMapping("/grade")
@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;

    @RequestMapping("/taskGradeStatisticByCnoAndTime")
    @ResponseBody
    public ResultMap taskGradeStatisticByCnoAndTime(String cno, String time, String tno){
        List<TaskGradeStatistic> list = gradeService.taskGradeStatisticByCnoAndTime(cno,time,tno);
        return ResultMap.ok().put("list",list);
    }
    @RequestMapping("/taskGradeDetailByCnameAndTime")
    @ResponseBody
    public ResultMap taskGradeDetailByCnameAndTime(String cname, String time, String cno, String tno) {
        Map<String, Object> map = gradeService.taskGradeDetailByCnameAndTime(cname, time, cno, tno);
        return ResultMap.ok().put("list",map.get("list")).put("questionStatistics",map.get("questionStatistics"));
    }

    @RequestMapping("/courseGradeByCnoAndTime")
    @ResponseBody
    public ResultMap courseGradeByCnoAndTime(String cno,String time, String tno) {
        List<TaskGradeStatistic> list = gradeService.courseGradeByCnoAndTime(cno,time,tno);
        return ResultMap.ok().put("list",list);
    }

    @RequestMapping("/courseDetailGradeByCnameAndTaskName")
    @ResponseBody
    public ResultMap courseDetailGradeByCnameAndTaskName(String cname, String taskName, String cno,String tno) {
        List<ClassDeatilGrade> list = gradeService.courseDetailGradeByCnameAndTaskName(cname,taskName,cno,tno);
        return ResultMap.ok().put("list",list);
    }
    @RequestMapping("/downLoadGrade")
    public ResponseEntity<byte[]> fileDownLoad(String clazzName,String tno,HttpServletRequest request) throws Exception{
        //public ResponseEntity（T  body，
        //                       MultiValueMap < String，String > headers，
        //                       HttpStatus  statusCode）
        //HttpEntity使用给定的正文，标题和状态代码创建一个新的。
        //参数：
        //body - 实体机构
        //headers - 实体头
        //statusCode - 状态码

        //ServletContext servletContext = request.getServletContext();
        InputStream in=gradeService.exportGrade(clazzName,tno);
        String fileName=clazzName+"作业成绩.xls";
        //String realPath = servletContext.getRealPath("D:\\IDEAWorkspace\\jss2\\WebRoot\\WEB-INF\\muban\\"+fileName);//得到文件所在位置
        //InputStream in=new FileInputStream(new File("D:\\IDEAWorkspace\\jss2\\WebRoot\\WEB-INF\\muban\\"+fileName));//将该文件加入到输入流之中
        byte[] body=null;
        body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        in.read(body);//读入到输入流里面

        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }
}
