package com.ql.springbootdemo;

import com.ql.springbootdemo.bean.MyLazyBean;
import com.ql.springbootdemo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest(classes = SpringbootdemoApplication.class)
@Slf4j
class SpringbootdemoApplicationTests {

	@Autowired
	ApplicationEventPublisher eventPublisher;

//	@Autowired
//	private MyLazyBean myLazyBean;

	@Test
	void contextLoads() {
	}

	@Test
	public void testEvent() {
		CustomEvent customEvent = new CustomEvent(this, "auto_push", "hello", String.class);
		eventPublisher.publishEvent(customEvent);
	}

	public void callbackEvent() {
		log.info("callbackEvent");
	}

}
