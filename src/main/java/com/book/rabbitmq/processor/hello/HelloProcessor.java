package com.book.rabbitmq.processor.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.book.rabbitmq.constants.MessageQueueConstants;

@Component
@RabbitListener(queues = MessageQueueConstants.QUEUE_HELLO_NAME)
public class HelloProcessor {

	@RabbitHandler
    public void process(String content) {
		System.out.println("hello 接受消息：" + content);
    }
}
