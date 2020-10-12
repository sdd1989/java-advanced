package com.ql.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ql.springbootdemo.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/10/10 4:30 下午
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
