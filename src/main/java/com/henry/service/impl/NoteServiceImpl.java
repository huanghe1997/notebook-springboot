package com.henry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.henry.dao.NoteDao;
import com.henry.dao.NotebookDao;
import com.henry.dao.UserDao;
import com.henry.domain.Note;
import com.henry.domain.Notebook;
import com.henry.domain.SumNoteMessage;
import com.henry.domain.User;
import com.henry.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private NotebookDao notebookDao;
    @Autowired
    private UserDao userDao;
    //新增笔记本，userId必须要
    @Override
    public boolean insertNote(Note note) {
        BigInteger timestamp = BigInteger.valueOf(System.currentTimeMillis());
        note.setNoteTime(timestamp);
        int flag = noteDao.insert(note);
        if (flag ==1) return true;
        return false;
    }
    /*
    * 查询笔记
    * */
    @Override
    public List<Note> queryNoteList(Integer userId,Integer notebookId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notebook_id",notebookId);
        queryWrapper.eq("user_id",userId);
        List<Note> noteList = noteDao.selectList(queryWrapper);
        return noteList;
    }
    /*
    * 删除笔记
    * */
    @Override
    public boolean deleteNote(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        int flag = noteDao.delete(queryWrapper);
        if(flag==1) return true;
        return false;
    }

    @Override
    public boolean updateNote(Note note) {
        BigInteger timestamp = BigInteger.valueOf(System.currentTimeMillis());
        note.setNoteTime(timestamp);
        int flag = noteDao.updateById(note);
        if(flag ==1) return true;
        return false;
    }
   /*
   * 返回的信息除了笔记信息还有用户和笔记本信息，查询起来比较复杂
   * */
    @Override
    public List<SumNoteMessage> getNotes() {
        List<SumNoteMessage> sumNoteMessageList =new ArrayList<>();
        // 先查询用户信息
        List<User> userList= userDao.selectList(null);
        if(userList.size()>0){
            for (User user :userList){
                //根据userId查询笔记本
                QueryWrapper queryWrapperNoteBook = new QueryWrapper<>();
                queryWrapperNoteBook.eq("user_id",user.getId());
                List<Notebook> notebookList = notebookDao.selectList(queryWrapperNoteBook);
                if(notebookList.size()>0){
                    for (Notebook notebook:notebookList){
                        //根据笔记本id查询笔记
                        QueryWrapper queryWrapperNote = new QueryWrapper<>();
                        queryWrapperNote.eq("notebook_id",notebook.getId());
                        List<Note> noteList = noteDao.selectList(queryWrapperNote);
                        if(noteList.size()>0){
                            for (Note note:noteList){
                                SumNoteMessage noteMessage = new SumNoteMessage();
                                noteMessage.setNoteId(note.getId());
                                noteMessage.setNoteTitle(note.getNoteTitle());
                                noteMessage.setNoteMd(note.getNoteMd());
                                noteMessage.setNoteTime(note.getNoteTime());
                                noteMessage.setNickName(user.getNickName());
                                noteMessage.setImageUrl(user.getImageUrl());
                                noteMessage.setNotebookName(notebook.getNotebookName());
                                noteMessage.setNotebookImageUrl(notebook.getNotebookImageUrl());
                                sumNoteMessageList.add(noteMessage);
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(sumNoteMessageList, Comparator.comparing(SumNoteMessage::getNoteTime).reversed());
        return sumNoteMessageList;
    }

    @Override
    public List<SumNoteMessage> searchNotes(String searchValue) {
         QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
         queryWrapper.like("note_title",searchValue);
        List<Note> noteList = noteDao.selectList(queryWrapper);
        Collections.sort(noteList,Comparator.comparing(Note::getNoteTime).reversed());
        //返回给前端的数据格式
        List<SumNoteMessage> searchNoteMessageList =new ArrayList<>();
        if(noteList.size()>0){
            for (Note note:noteList){
                SumNoteMessage noteMessage =  new SumNoteMessage();
                noteMessage.setNoteId(note.getId());
                noteMessage.setNoteTitle(note.getNoteTitle());
                noteMessage.setNoteMd(note.getNoteMd());
                noteMessage.setNoteTime(note.getNoteTime());
                //查询用户信息
                QueryWrapper<User>   queryWrapperUser = new QueryWrapper<>();
                queryWrapperUser.eq("id",note.getUserId());
                User user= userDao.selectOne(queryWrapperUser);
                noteMessage.setImageUrl(user.getImageUrl());
                noteMessage.setNickName(user.getNickName());
                //查询笔记本信息
                QueryWrapper<Notebook> queryWrapperNotebook = new QueryWrapper<>();
                queryWrapperNotebook.eq("id",note.getNotebookId());
                Notebook notebook = notebookDao.selectOne(queryWrapperNotebook);
                noteMessage.setNotebookImageUrl(notebook.getNotebookImageUrl());
                noteMessage.setNotebookName(notebook.getNotebookName());
                searchNoteMessageList.add(noteMessage);
            }
        }
        Collections.sort(searchNoteMessageList, Comparator.comparing(SumNoteMessage::getNoteTime).reversed());
        return searchNoteMessageList;
    }
}
