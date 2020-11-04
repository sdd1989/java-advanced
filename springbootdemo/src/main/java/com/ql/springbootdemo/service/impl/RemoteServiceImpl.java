package com.ql.springbootdemo.service.impl;

import com.google.common.collect.Lists;
import com.ql.springbootdemo.exception.BusinessAException;
import com.ql.springbootdemo.exception.BusinessBException;
import com.ql.springbootdemo.model.User;
import com.ql.springbootdemo.service.RemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/11/4 11:55 上午
 **/
@Slf4j
@Service
public class RemoteServiceImpl implements RemoteService {

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public List<User> process() {
        int num = count.getAndIncrement();
        log.info("count的值 = " + num);
        if (num >= 7) {
            // 模拟数据库的正常查询
            return Lists.newArrayList();
        }
        if (num % 4 == 1){
//            throw new BusinessAException("异常A，不需要被记录");
            throw new RuntimeException("异常");
        }
        if (num % 4 == 2 || num % 4 == 3){
//            throw new BusinessBException("异常B，需要被记录");
            throw new RuntimeException("异常");
        }
        log.info("服务正常运行，获取用户列表");
        // 模拟数据库的正常查询
        return Lists.newArrayList();
    }
}
