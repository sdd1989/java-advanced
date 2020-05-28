package com.ql.springbootdemo.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author qiuliang@xiaomi.com
 * @desc todo:描述
 * @date 2020/5/27 3:54 下午
 **/
@Getter
@Setter
@ToString
public class CustomEvent<T> extends ApplicationEvent {

    private String eventCode;

    private String message;

    private Class<T> type;

    public CustomEvent(Object source, String eventCode, String message, Class<T> type) {
        super(source);
        this.eventCode = eventCode;
        this.message = message;
        this.type = type;
    }


}
