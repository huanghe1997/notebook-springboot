package com.henry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.henry.dao.UserDao;
import com.henry.domain.Register;
import com.henry.domain.User;
import com.henry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
        return userList;
    }
    @Override
    public Integer register(Register register) {
        //判断是否一致;
        if (register.getPassword().equals(register.getEnterPassword())){
            User user = new User();
            user.setName(register.getName());
            user.setPassword(register.getPassword());
            //判断用户名是否重复
            QueryWrapper wrapper = new QueryWrapper<User>();
            wrapper.eq("name",register.getName());
            User result  = userDao.selectOne(wrapper);
            if (result!=null){
                return 2;
            }else{
                int userId = userDao.insert(user);
                if (userId!=0) return 1;
                else{
                    return 0;
                }
            }
        }
        return  0;
    }

    @Override
    public User verifyAuth(User user) {
        QueryWrapper wrapper = new QueryWrapper<User>();
        wrapper.eq("name",user.getName());
        wrapper.eq("password",user.getPassword());
        User result = userDao.selectOne(wrapper);
        return result;
    }

    @Override
    public User getUser(String userId) {
         User user = userDao.selectById(userId);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
         int flag = userDao.updateById(user);
         boolean target = (flag == 1) ? true : false;
        return target;

    }
}
