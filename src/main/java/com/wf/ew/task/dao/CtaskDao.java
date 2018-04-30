package com.wf.ew.task.dao;

import com.wf.ew.task.model.Ctask;

import java.util.List;

public interface CtaskDao {
    List<Ctask> querySharedTask(String tid);
}
