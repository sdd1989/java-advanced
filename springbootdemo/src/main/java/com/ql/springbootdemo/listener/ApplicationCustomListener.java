package com.ql.springbootdemo.listener;

import com.ql.springbootdemo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/27 3:58 下午
 **/
@Slf4j
@Component
public class ApplicationCustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        log.info("onApplicationEvent:{}", customEvent);
        Object object = customEvent.getSource();
    }
}
