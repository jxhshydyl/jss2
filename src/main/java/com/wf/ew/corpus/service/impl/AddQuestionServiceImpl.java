package com.wf.ew.corpus.service.impl;

import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.AddQuestionService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class AddQuestionServiceImpl implements AddQuestionService{

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
                StringBuilder strb=null;
                for( Field field:declaredFields){
                    field.setAccessible(true);
                    String str=null ;
                    if(field.get(addQuestion)!=null){
                        str = field.get(addQuestion).toString();
                    }
                    if(str!=null && "".equals(str)){
                        if(question.getQchoice()==null){
                            strb=new StringBuilder("");
                        }else{
                            strb=new StringBuilder(question.getQchoice());
                        }
                        strb=strb.append("\n").append(chars[key]+"："+str);
                    }
                }
                question.setQchoice(strb.toString());
                StringBuilder strb1=null;
                for(String daan:addQuestion.getDaan()){
                    if(question.getAnswer()==null){
                        strb1=new StringBuilder("");
                    }else{
                        strb1=new StringBuilder(question.getQchoice());
                    }
                    strb1=strb1.append(" ").append(daan);
                }
                question.setAnswer(strb1.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if("判断题".equals(type)){
            question.setAnswer(addQuestion.getDaan1());
        }else{

        }
        System.out.println(question);
        return 0;
    }
}
