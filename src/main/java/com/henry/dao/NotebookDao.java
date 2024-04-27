package com.henry.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.domain.Notebook;
import org.apache.ibatis.annotations.Mapper;


@Mapper

public interface NotebookDao extends BaseMapper<Notebook> {
    //根据userId查询笔记数据

}
