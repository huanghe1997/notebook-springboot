package com.henry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.henry.dao.TaskDao;
import com.henry.domain.Task;
import com.henry.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
     @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> queryTaskList(Integer userId) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Task> tasks = taskDao.selectList(queryWrapper);
        return tasks;
    }

    @Override
    public boolean insertTask(Task task) {
        int flag = taskDao.insert(task);
        boolean  target = flag==1? true:false;
        return target;
    }
    @Override
    public boolean deleteTask(Integer id) {
        int flag = taskDao.deleteById(id);
        boolean  target = flag==1? true:false;
        return target;
    }
}
