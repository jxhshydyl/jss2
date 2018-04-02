package com.wf.ew.corpus.service.impl;

import com.wf.ew.corpus.dao.UploadDao;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.UploadService;
import com.wf.ew.task.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService{
    @Autowired
    UploadDao uploadDao;

    public int bulkAddQuestion(InputStream inputStream, String questonType, String fileName) {
        questonType="noCode";
        System.out.println(fileName);
        System.out.println("上传文件");
        if ("code".equals(questonType)) {
            try {
                int temp2;
                String str1 = "题目标题";
                String str2 = "题目描述";
                String str3 = "输入描述";
                String str4 = "输出描述";
                String str5 = "输入例子";
                String str6 = "输出例子";
                String str7 = "参考答案";
                String str8 = "时间限制";
                String str9 = "内存限制";
                String str10 = "难易程度";
                String str11 = "题目类型";
                String str12 = "所属课程";
                String str13 = "所属章";
                String str14 = "所属节";
                Code que = new Code();
                List<String> list = new ArrayList<String>();
                list.add(str1);
                list.add(str2);
                list.add(str3);
                list.add(str4);
                list.add(str5);
                list.add(str6);
                list.add(str7);
                list.add(str8);
                list.add(str9);
                list.add(str10);
                list.add(str11);
                list.add(str12);
                list.add(str13);
                list.add(str14);
                ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(inputStream,
                        fileName.split("\\.")[fileName.split("\\.").length - 1]);
                for (int j = 0; j < result.get(0).size(); j++) {
                    if (result.get(0).get(j) == null || !(result.get(0).get(j).toString().equals(list.get(j))))
                        return 0;
                }
                List<Code> codes = new ArrayList<Code>();
                for (int i = 1; i < result.size(); i++) {
                    que.setQname((String) result.get(i).get(0));
                    que.setQdescribe((String) result.get(i).get(1));
                    que.setInputDescribe(result.get(i).get(2).toString().trim());
                    que.setOutputDescripe(result.get(i).get(3).toString());
                    que.setExampleInput((String) result.get(i).get(4));
                    que.setExampleOutput(result.get(i).get(5).toString());
                    que.setReferenceAnswer((String) result.get(i).get(6));
                    if (result.get(i).get(7).toString().equals("")) {
                        que.setLimitTime(0);
                    } else {
                        que.setLimitTime(Integer.valueOf(result.get(i).get(7).toString()));
                    }
                    if (result.get(i).get(8).toString().equals("")) {
                        que.setLimitMemory(0);
                    } else {
                        que.setLimitTime(Integer.valueOf(result.get(i).get(8).toString()));
                    }

                    if ("".equals((String) result.get(i).get(9))) {
                        que.setQdegree(3);
                    } else {
                        que.setQdegree((int) Float.parseFloat((String) result.get(i).get(9)));
                    }
                    if (result.get(i).size() == 11) {
                        que.setQtype((String) result.get(i).get(10));
                    }
                    if (result.get(i).size() == 12) {
                        que.setQtype((String) result.get(i).get(10));
                        que.setCname((String) result.get(i).get(11));
                    }
                    if (result.get(i).size() == 13) {
                        que.setQtype((String) result.get(i).get(10));
                        que.setCname((String) result.get(i).get(11));
                        que.setQchapter((String) result.get(i).get(12));
                    }
                    if (result.get(i).size() == 14) {
                        que.setQtype((String) result.get(i).get(10));
                        que.setCname((String) result.get(i).get(11));
                        que.setQchapter((String) result.get(i).get(12));
                        que.setQparagraph((String) result.get(i).get(13));
                    }
                    codes.add(que);
                }
                return uploadDao.bulkAddCode(codes);
            } catch (Exception e) {
            }
            return 0;
        } else if ("noCode".equals(questonType)) {
            try {
                int temp2;
                String str1 = "题目内容";
                String str2 = "题目类型";
                String str3 = "题目选项";
                String str4 = "题目答案";
                String str5 = "题目难度";
                String str6 = "所属课程";
                String str7 = "所属章";
                String str8 = "所属节";
                List<String> list = new ArrayList<String>();
                list.add(str1);
                list.add(str2);
                list.add(str3);
                list.add(str4);
                list.add(str5);
                list.add(str6);
                list.add(str7);
                list.add(str8);
                ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(inputStream,
                        fileName.split("\\.")[fileName.split("\\.").length - 1]);
                for (int j = 0; j < 8; j++) {
                    if (result.get(0).get(j) == null || !(result.get(0).get(j).toString().equals(list.get(j))))
                        return 0;
                }
                List<Question> questions = new ArrayList<Question>();
                for (int i = 1; i < result.size(); i++) {
                    if ((result.get(i).get(0) != null && result.get(i).get(1) != null
                            && result.get(i).get(5) != null && result.get(i).get(6) != null)
                            && (!((String) result.get(i).get(0)).trim().equals("")
                            && !((String) result.get(i).get(1)).trim().equals("")
                            && !((String) result.get(i).get(5)).trim().equals("")
                            && !((String) result.get(i).get(6)).trim().equals(""))) {
                        Question que = new Question();
                        que.setQcontent((String) result.get(i).get(0));
                        que.setQtype((String) result.get(i).get(1));
                        if(result.get(i).get(2)!=null){
                            System.out.println((String) result.get(i).get(2));
                            que.setQchoice(result.get(i).get(2).toString().trim());
                        }
                        if(result.get(i).get(3)!=null){
                            que.setQanswer(result.get(i).get(3).toString());
                        }
                        if(result.get(i).get(4)!=null){
                            str2 = result.get(i).get(4).toString();
                            if (str2.trim().equals("")) {
                                temp2 = 3;
                            } else {
                                temp2 = (int) Float.parseFloat(str2);
                            }
                            que.setQdegree(temp2);
                        }
                        que.setCname((String) result.get(i).get(5));
                        que.setQchapter((String) result.get(i).get(6));
                        if (result.get(i).size() == 8) {
                            que.setQparagraph((String) result.get(i).get(7));
                        }
                        questions.add(que);
                        continue;
                    }
                }
                return uploadDao.bulkAddQuestion(questions);
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
