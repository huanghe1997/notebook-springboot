package com.henry.service;

import com.henry.domain.Notebook;
import java.util.HashMap;
import java.util.List;


public interface NotebookService {
    //根据userId查询笔记列表信息
    public List<Notebook> getNotebookList(int userId);
    //插入数据,userId必填
    public  boolean insertNotebook(Notebook notebook);
    //查询笔记本名称
    public HashMap<String,Object> getNameList(int userId);
    //删除笔记本
    public boolean deleteNotebook(Integer id);
    //修改笔记本
    public  boolean updateNotebook(Notebook notebook);

}
