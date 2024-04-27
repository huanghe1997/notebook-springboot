package com.henry.controller;

import com.alibaba.fastjson.JSON;
import com.henry.domain.Register;
import com.henry.domain.User;
import com.henry.service.UserService;
import com.henry.util.ApiResponse;
import com.henry.util.JwtUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    protected  String register(@RequestBody Register register){
        Integer code = userService.register(register);
        String result = null;
         switch (code){
             case 1:result="注册成功";break;
             case 0:result="注册失败";break;
             case 2:result="用户名重复";break;
             default:break;
         }
        return JSON.toJSONString(new ApiResponse<String>().success(result,"注册信息",code));
    }
    @PostMapping("/login")
    protected ResponseEntity<String> authUser(@RequestBody User user){
         //验证用户名和密码
        User userData = userService.verifyAuth(user);
        if (userData!=null){
             //生成token
            String token = JwtUitls.createToken(userData.getId(),userData.getName());
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization",  token);
            String bodyMessage = JSON.toJSONString(new ApiResponse<User>().success(userData,token,200));
            return ResponseEntity.ok().headers(headers).body(bodyMessage);
        }
            return ResponseEntity.ok().body("用户信息不正确");
    }
    /*
    *  获取用户的name
    * */
    @GetMapping("/getUser")
    protected  String getUser(@RequestParam String userId){
        //获取用户信息
        User user = userService.getUser(userId);
        return JSON.toJSONString(new ApiResponse<User>().success(user,user!=null?"查询成功":"查询失败",user!=null?200:300));
    }
    /*
    *  更新用户信息
    * */
    @PutMapping()
    protected  String updateUser(@RequestBody User user){
        //更新用户信息
        boolean flag = userService.updateUser(user);
       return JSON.toJSONString(new ApiResponse<Boolean>().success(flag,flag==true?"修改成功":"修改失败",flag==true?200:300));
    }
}
