package com.henry.service;

import com.henry.domain.Task;

import java.util.List;

public interface TaskService {
    //查询当前用户任务
    public List<Task> queryTaskList(Integer userId);
    //添加任务
    public boolean insertTask(Task task);
    //删除任务
    public boolean deleteTask(Integer id);
}
