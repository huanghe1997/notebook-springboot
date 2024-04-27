package com.henry.controller;
import com.alibaba.fastjson.JSON;
import com.henry.domain.Note;
import com.henry.domain.SumNoteMessage;
import com.henry.service.NoteService;
import com.henry.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    /*
    * 新增笔记
    * */
    @PostMapping()
    protected String addNote(@RequestBody Note note){
        boolean flag = noteService.insertNote(note);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"插入笔记成功":"插入笔记失败",flag==true?200:300));
    };
    /*
    * 查询所有笔记列表
    * */
    @GetMapping()
    protected  String queryNoteList(@RequestParam Integer userId,Integer notebookId){
        List<Note> noteList = noteService.queryNoteList(userId,notebookId);
        return JSON.toJSONString(new ApiResponse<List<Note>>().success(noteList,noteList.size()>0?"查询成功":"查询失败",noteList.size()>0?200:300));
    };
    /*
    * 删除笔记
    * */
    @DeleteMapping()
    protected String deleteNote(@RequestParam Integer id){
        boolean flag = noteService.deleteNote(id);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"删除成功":"删除失败",flag==true?200:300));
    };
    /*
    * 修改笔记
    * */
    @PutMapping()
    protected  String updateNote(@RequestBody Note note){
        boolean flag = noteService.updateNote(note);
        return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"修改成功":"修改失败",flag==true?200:300));
    }
   /*
   * 查询所有笔记信息，还有用户信息
   * */
    @GetMapping("/getNotes")
    protected String getNotes(){
        List<SumNoteMessage> sumNoteMessageList= noteService.getNotes();
        return JSON.toJSONString(new ApiResponse<List<SumNoteMessage>>().success(sumNoteMessageList,sumNoteMessageList.size()>0?"查询成功":"查询失败",sumNoteMessageList.size()>0?200:300));
    }
  /*
  * 查询特定笔记
  * */
    @GetMapping("/search")
    protected String searchNotes(String searchValue){
         List<SumNoteMessage> searchNotes = noteService.searchNotes(searchValue);
        return JSON.toJSONString(new ApiResponse<List<SumNoteMessage>>().success(searchNotes,searchNotes.size()>0?"查询成功":"未找到数据",searchNotes.size()>0?200:300));
    }

}
