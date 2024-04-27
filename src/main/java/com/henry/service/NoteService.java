package com.henry.service;

import com.henry.domain.Note;
import com.henry.domain.SumNoteMessage;

import java.util.HashMap;
import java.util.List;

public interface NoteService {
    //添加笔记
    public boolean insertNote(Note note);
    //查询笔记列表
    public List<Note> queryNoteList(Integer userId,Integer notebookId);
   //删除笔记
    public boolean deleteNote(Integer id);
    //修改笔记
    public boolean updateNote(Note note);
    //查询所有笔记
    public List<SumNoteMessage> getNotes();
    //查询特定笔记
    public  List<SumNoteMessage> searchNotes(String searchValue);
}
