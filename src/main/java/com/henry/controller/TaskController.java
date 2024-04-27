package com.henry.controller;

import com.alibaba.fastjson.JSON;
import com.henry.domain.Task;
import com.henry.service.TaskService;
import com.henry.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    //查询用户所有任务
    @GetMapping()
    protected  String queryTaskList(@RequestParam Integer userId){
        List<Task> tasks = taskService.queryTaskList(userId);
         return JSON.toJSONString(new ApiResponse<List<Task>>().success(tasks,tasks.size()>0?"查询成功":"查询失败",tasks.size()>0?200:300));
    };
    //添加任务
    @PostMapping()
    protected  String insertTask (@RequestBody Task task){
       boolean flag = taskService.insertTask(task);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"添加成功":"添加失败",flag==true?200:300));
    }
    //删除任务
    @DeleteMapping()
    protected String deleteTask(@RequestParam Integer id){
        boolean flag = taskService.deleteTask(id);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"删除成功":"删除失败",flag==true?200:300));
    }
}
