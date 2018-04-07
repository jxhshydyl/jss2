package com.wf.ew.corpus.service.impl;

import com.wf.ew.corpus.dao.QuestionDao;
import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.AddQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class AddQuestionServiceImpl implements AddQuestionService{
    @Autowired
    QuestionDao questionDao;

    @Override
    public int addQuestion(Question question, AddQuestion addQuestion){
        String type=question.getQtype();
        String[] chars=new String[]{"A","B","C","D","E","F","G","H","I"};
        System.out.println(type);
        int key=0;
        if("单选题".equals(type)||"多选题".equals(type)){
            System.out.println(type);
            try{
                Field[] declaredFields = addQuestion.getClass().getDeclaredFields();
                StringBuilder strb=new StringBuilder();
                for(int i=0;i<9;i++){
                    declaredFields[i].setAccessible(true);
                    String str=null;
                    if(declaredFields[i].get(addQuestion)!=null){
                        str = declaredFields[i].get(addQuestion).toString();
                    }
                    if(str!=null && !"".equals(str)){
                        strb=new StringBuilder(strb);
                        System.out.println(str);
                        System.out.println(strb);
                        System.out.println(chars[key]);
                        strb=strb.append("\n").append(chars[key]+"："+str);
                        System.out.println(strb);
                        key++;
                    }
                }
                question.setQchoice(strb.toString());
                StringBuilder strb1=new StringBuilder();
                if(addQuestion.getDaan()!=null){
                    for(String daan:addQuestion.getDaan()){
                        if(question.getAnswer()==null){
                            strb1=new StringBuilder();
                        }else{
                            strb1=new StringBuilder(strb1);
                        }
                        strb1=strb1.append(" ").append(daan);
                    }
                }
                question.setQanswer(strb1.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if("判断题".equals(type)){
            question.setAnswer(addQuestion.getDaan1());
        }
        System.out.println(question);
        if(question.getQid()==null){
            return questionDao.addQuestion(question);
        }else{
            return questionDao.modifyQuestion(question);
        }

    }

    public int addCode(Code code){
        try{
            if(code.getQid()==null){
                return questionDao.addCode(code);
            }else{
                return questionDao.updateCode(code);
            }

        }catch (Exception e){
            return 0;
        }
    }
}
