package com.wf.ew.task.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.core.utils.DateUtil;
import com.wf.ew.task.model.AutoMakePaperPara;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/jss/arrangeTask")
public class ArrangeTaskController {
    @Autowired
    ArrangeService arrangeService;

    @PostMapping()
    @ResponseBody
    public ResultMap queryQuestionCountByType(String type,String cno,@RequestParam(value = "chapter[]",defaultValue = "") String[] chapter){
        System.out.println(type);
        System.out.println(cno);
        System.out.println(chapter.length);
        int num = arrangeService.queryQuestionCountByType(type,cno,chapter);
        return ResultMap.ok(0,"查询成功！").put("value",num);
    }
    @PostMapping("/arrange")
    public String arrangeTask(Task task,AutoMakePaperPara autoMakePaperPara,Condition condition,HttpServletRequest request){
        System.out.println(task);
        System.out.println(autoMakePaperPara);
        System.out.println(condition);
        System.out.println(request.getRequestURI());
        MultipartFile file=task.getAppendixesFile();
        String currentDate = DateUtil.getCurrentDate();
        task.setSubtime(currentDate);
        try {
            file.getInputStream();
            if (!file.isEmpty()) {
                //上传文件路径
                String tashPath="file//teacher//"+task.getTno()+"//task//"+new Date().getTime()+task.getTaskName();
                String path = request.getServletContext().getRealPath(tashPath);
                //上传文件名
                String filename = file.getOriginalFilename();

                File filepath = new File(path, filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                task.setTappendixes(filename);
                //将上传文件保存到一个目标文件当中
                file.transferTo(new File(path + File.separator + filename));
                task.setTappendixes(path + File.separator + filename);
            }
        } catch (Exception e) {
        }
        arrangeService.arrangeTask(task,autoMakePaperPara,condition);
        return "redirect:"+request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"index.html#!task/arrangeTask";
    }
    @RequestMapping("/judgeTaskName")
    @ResponseBody
    public ResultMap judgeTaskName(String taskName){
        int i = arrangeService.judgeTaskName(taskName);
        if(i==1){
            return ResultMap.error(0,"有相同名称");
        }
        return ResultMap.ok(200,"无相同名称");
    }
}
