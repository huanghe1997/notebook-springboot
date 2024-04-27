package com.henry.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.domain.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskDao extends BaseMapper<Task> {
}
