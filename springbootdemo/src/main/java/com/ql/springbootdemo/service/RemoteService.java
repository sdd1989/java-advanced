package com.ql.springbootdemo.service;

import com.ql.springbootdemo.model.User;

import java.util.List;

public interface RemoteService {
    List<User> process() throws Exception;
}
