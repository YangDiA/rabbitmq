package com.book.rabbitmq.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.enums.message.MessageTypeEnum;
import com.book.rabbitmq.exception.MessageException;
import com.book.rabbitmq.message.QueueMessage;
import com.book.rabbitmq.service.IMessageQueueService;
import com.book.rabbitmq.utils.JSONUtils;
import com.book.rabbitmq.utils.StringUtils;

/**
 * rabbit mq 消息队列实现
 * @author victor
 *
 */
@Service("deafaultMessageService")
public class DeafaultMessageServiceImpl implements IMessageQueueService{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void send(QueueMessage message) {
		this.checkMessage(message);
		if(message.getType() == MessageTypeEnum.DEFAULT.getIndex()){//即时消息
			this.sendMessage(message.getExchange(),message.getQueueName(),message.getMessage());
		}
		if(message.getType() == MessageTypeEnum.DELAYED.getIndex()){//延时消息
			sendTimeMessage(message);
		}
	}
	
	private void sendMessage(String exchange,String queueName,String msg){
		rabbitTemplate.convertAndSend(exchange,queueName, msg);
	}
	
	public void sendTimeMessage(QueueMessage message) {
		int seconds = message.getSeconds();
		if(seconds <= 0){// 直接发送，无需进入死信队列
			sendMessage(message.getExchange(),message.getQueueName(), message.getMessage());
		}else{
			long times = seconds * 1000;//rabbit默认为毫秒级
			MessagePostProcessor processor = new MessagePostProcessor(){
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {
					message.getMessageProperties().setExpiration(times + "");
					return message;
				}
			};
			rabbitTemplate.convertAndSend(MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME,MessageQueueConstants.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSONUtils.toJson(message), processor);
		}
	}
	
	
	private void checkMessage(QueueMessage message){
		if (StringUtils.isNullOrEmpty(message.getExchange())) {
			throw new MessageException(10, "发送消息格式错误: 消息交换机(exchange)不能为空!");
		}
		if(message.getGroup() == null){
			throw new MessageException(10, "发送消息格式错误: 消息组(group)不能为空!");
		}
		if(message.getType() == null){
			throw new MessageException(10, "发送消息格式错误: 消息类型(type)不能为空!");
		}
		if(message.getStatus() == null){
			throw new MessageException(10, "发送消息格式错误: 消息状态(status)不能为空!");
		}
		if(StringUtils.isNullOrEmpty(message.getQueueName())){
			throw new MessageException(10, "发送消息格式错误: 消息目标名称(queueName)不能为空!");
		}
		if (StringUtils.isNullOrEmpty(message.getMessage())) {
			throw new MessageException(10, "发送消息格式错误: 消息内容(message)不能为空!");
		}
	}

}
