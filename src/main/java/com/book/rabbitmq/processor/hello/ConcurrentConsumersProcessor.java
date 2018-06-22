package com.book.rabbitmq.processor.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.book.rabbitmq.constants.MessageQueueConstants;


@Component
@RabbitListener(queues = MessageQueueConstants.CONCURRENT_CONSUMERS_NAME,containerFactory = "concurrentConsumersContainerFactory")
public class ConcurrentConsumersProcessor {
	
	private  static int num = 0;
	
	@RabbitHandler
    public void process(String content) throws InterruptedException {
		System.out.println("接收消息：" + content);
		num ++;
		System.out.println("消息处理完成" + num);
    }

}
