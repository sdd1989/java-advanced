package com.ql.springbootdemo.controller;

import com.ql.springbootdemo.model.User;
import com.ql.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/10 4:36 下午
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

    @GetMapping("/insert")
    public Boolean insert(User user) {
        return userService.save(user);
    }

    @GetMapping("/selectBy")
    public User selectBy(Integer id) {
        return userService.getUserById(id);
    }

}
