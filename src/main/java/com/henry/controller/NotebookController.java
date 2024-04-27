package com.henry.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.henry.domain.Notebook;
import com.henry.service.NotebookService;
import com.henry.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("api/notebook")
public class NotebookController {
    @Autowired
    NotebookService notebookService;
    //新增笔记本
    @PostMapping()
    protected String addNotebook(@RequestBody Notebook notebook){
        boolean flag = notebookService.insertNotebook(notebook);
        return JSON.toJSONString(new ApiResponse<String>().success(flag,"添加笔记成功",200));
    }
    //查询笔记本列表
    @GetMapping()
    protected  String queryNotebooks(@RequestParam Integer userId){
        List<Notebook> notebookList = notebookService.getNotebookList(userId);
        if (notebookList.size()>0) return JSON.toJSONString(new ApiResponse<List<Notebook>>().success(notebookList,"笔记本列表",200));
        return  JSON.toJSONString(new ApiResponse<Object>().success(null,"未查询到笔记本信息",300));
    }
    /*
    * 查询笔记本名称和id
    * 返回key,value的对象数据形式
    */
    @GetMapping("/name")
    protected String queryName (@RequestParam int userId){
        HashMap<String, Object> nameMapper = notebookService.getNameList(userId);
     return JSON.toJSONString(new ApiResponse<HashMap>().success(nameMapper,!nameMapper.isEmpty()?"查询笔记名称成功":"未查询到笔记名称",!nameMapper.isEmpty()?200:300));
    }
    /*
    * 删除笔记本
    * */
    @DeleteMapping()
    protected  String deleteNotebook(@RequestParam Integer id){
        boolean flag = notebookService.deleteNotebook(id);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"删除成功":"删除失败",flag==true?200:300));
    }
    /*
    * 修改笔记本
    * */
    @PutMapping()
    protected  String updateNotebook(@RequestBody Notebook notebook){
        boolean flag = notebookService.updateNotebook(notebook);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"修改成功":"修改失败",flag==true?200:300));
    }
}
