package com.book.rabbitmq.processor.system;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.enums.message.MessageTypeEnum;
import com.book.rabbitmq.message.QueueMessage;
import com.book.rabbitmq.service.IMessageQueueService;
import com.book.rabbitmq.utils.JSONUtils;

/**
 * 
 * @author victor
 * @desc 死信接收处理消费者
 */
@Component
@RabbitListener(queues = MessageQueueConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeProcessor {
	
	@Autowired
	private IMessageQueueService messageQueueService;

	@RabbitHandler
    public void process(String content) {
		QueueMessage message = JSONUtils.toBean(content, QueueMessage.class);
		message.setType(MessageTypeEnum.DEFAULT.getIndex());
		messageQueueService.send(message);
    }
}
