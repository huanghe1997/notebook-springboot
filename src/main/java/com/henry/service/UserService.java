package com.henry.service;

import com.henry.domain.Register;
import com.henry.domain.User;

import java.util.List;

public interface UserService {
   public List<User> getUserList();
   public  Integer register(Register register);
   public User  verifyAuth(User user);
   public User getUser(String userId);
   public boolean updateUser(User user);
}
