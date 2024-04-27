package com.henry.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.domain.Note;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteDao extends BaseMapper<Note> {
    // 查询笔记列表
}
