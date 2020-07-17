package com.ql.springbootdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/7/9 7:19 下午
 **/
@Slf4j
@Component
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("start event:{}", event);
    }
}
