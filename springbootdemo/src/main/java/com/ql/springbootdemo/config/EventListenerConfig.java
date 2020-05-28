package com.ql.springbootdemo.config;

import com.ql.springbootdemo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/27 4:33 下午
 **/
@Configuration
@Slf4j
public class EventListenerConfig {


    @EventListener(condition = "#event.eventCode == 'auto_push'")
    @Async
    public void handleCustomEvent(CustomEvent event) {
        log.info("handleCustomEvent:{}", event);
    }

}
