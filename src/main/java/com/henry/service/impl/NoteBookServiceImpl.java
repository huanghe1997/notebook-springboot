package com.henry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.henry.dao.NoteDao;
import com.henry.dao.NotebookDao;
import com.henry.domain.Note;
import com.henry.domain.Notebook;
import com.henry.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteBookServiceImpl implements NotebookService {
   @Autowired
   NotebookDao notebookDao;
   @Autowired
    NoteDao noteDao;
    @Override
    public List<Notebook> getNotebookList(int userId) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);

        List<Notebook> notebookList = notebookDao.selectList(wrapper);
        for(Notebook notebook:notebookList){
            //查询笔记数据
            QueryWrapper<Note> queryWrapperNote = new QueryWrapper<>();
            queryWrapperNote.eq("notebook_id",notebook.getId());
            Integer sum= Math.toIntExact(noteDao.selectCount(queryWrapperNote));
            notebook.setNotebookCount(sum);
        }
        return notebookList;
    }

    @Override
    public boolean insertNotebook(Notebook notebook) {
        BigInteger timestamp = BigInteger.valueOf(System.currentTimeMillis());
        notebook.setNotebookTime(timestamp);
        int flag = notebookDao.insert(notebook);
        if(flag ==1) return true;
        return false;
    }

    @Override
    public HashMap<String,Object> getNameList(int userId) {
        QueryWrapper wrapper = new QueryWrapper<Notebook>();
        wrapper.eq("user_id",userId);
        wrapper.select("id","notebook_name");
        HashMap<String,Object> nameMap = new HashMap<>();
        List<Notebook> notebooks =  notebookDao.selectList(wrapper);
        if(notebooks.size()>0){
            for (Notebook notebook:notebooks){
                nameMap.put(notebook.getId().toString(),notebook.getNotebookName());
            }
        }
        return nameMap;
    }
  /*  @Override
    public List<NoteLabel> getNameList(int userId) {
        QueryWrapper wrapper = new QueryWrapper<Notebook>();
        wrapper.eq("user_id",userId);
        wrapper.select("id","notebook_name");
//        HashMap<Integer,String> nameMap = new HashMap<>();
        List<NoteLabel> noteLabels = new ArrayList<>();
        List<Notebook> notebooks =  notebookDao.selectList(wrapper);
        if(notebooks.size()>0){
            for (Notebook notebook:notebooks){
//                nameMap.put(notebook.getId(),notebook.getNotebookName());
                NoteLabel noteLabel = new NoteLabel();
                noteLabel.setKey(notebook.getId());
                noteLabel.setValue(notebook.getNotebookName());
                noteLabels.add(noteLabel);
            }
        }
        return noteLabels;
    }*/
    @Override
    public boolean deleteNotebook(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        int flag = notebookDao.delete(queryWrapper);
        if(flag==1) return true;
        return false;
    }

    @Override
    public boolean updateNotebook(Notebook notebook) {
        //先查询数据是否改变了
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("notebook_name",notebook.getNotebookName());
        wrapper.eq("id",notebook.getId());
        Notebook notebookData = notebookDao.selectOne(wrapper);
        if(notebookData!=null){
            return false;
        }
        BigInteger timestamp = BigInteger.valueOf(System.currentTimeMillis());
        notebook.setNotebookTime(timestamp);
       Integer flag =  notebookDao.updateById(notebook);
        if (flag==1) return true;
        return false;
    }
}
