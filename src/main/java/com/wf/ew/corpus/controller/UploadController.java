package com.wf.ew.corpus.controller;

import com.wf.ew.core.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UploadController {

    @RequestMapping("/upload")
    @ResponseBody
    public ResultMap uploadCorpus(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        //如果文件不为空，写入上传路径
        try {
            if (!file.isEmpty()) {
                //上传文件路径
                String path = request.getServletContext().getRealPath("test");
                //上传文件名
                String filename = file.getOriginalFilename();
                File filepath = new File(path, filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                System.out.println(path + File.separator + filename);
                //将上传文件保存到一个目标文件当中
                file.transferTo(new File(path + File.separator + filename));
                return ResultMap.ok(0,"上传成功");
            } else {
                return ResultMap.ok(1,"上传失败");
            }
        } catch (Exception e) {
        }
        return ResultMap.ok(1,"上传失败");
    }
}
