package com.exampl.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jiyujuJava
 */
@Log4j2
@Component
@RabbitListener(queues = "testDirectQueue")
public class DirectReceiver {
    @RabbitHandler
    public void process(Map testMessage){
        log.info("第一个 DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

}
