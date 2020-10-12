package com.ql.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ql.springbootdemo.mapper.UserMapper;
import com.ql.springbootdemo.model.User;
import com.ql.springbootdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/10 4:31 下午
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery());
    }

    @Override
    public User getUserById(Integer id) {
        return baseMapper.selectById(id);
    }

}
