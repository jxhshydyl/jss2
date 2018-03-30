package com.wf.ew.clazz.service;

import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.model.Question;
import java.util.List;

public interface ClassService {
    PageResult<Class>  queryClass(Integer page, Integer limit, String searchKey, String searchValue, String tno);
    PageResult<Students> queryStudentsByClass(Integer page, Integer limit, String searchKey, String searchValue, String cno);
}
