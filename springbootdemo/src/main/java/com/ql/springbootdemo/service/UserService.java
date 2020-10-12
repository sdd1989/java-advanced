package com.ql.springbootdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ql.springbootdemo.model.User;

import java.util.List;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/10 4:31 下午
 **/
public interface UserService extends IService<User> {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(User entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();

    User getUserById(Integer id);
}
