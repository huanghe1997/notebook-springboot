package com.henry.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
