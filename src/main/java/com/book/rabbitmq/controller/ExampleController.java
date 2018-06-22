package com.book.rabbitmq.controller;

import com.book.rabbitmq.service.IAckMessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.enums.message.MessageTypeEnum;
import com.book.rabbitmq.message.QueueMessage;
import com.book.rabbitmq.service.IMessageQueueService;

@RestController
public class ExampleController {
	
	@Autowired
	private IMessageQueueService messageQueueService;

	@GetMapping("/send")
	public String send(){
		System.out.println("*****************");
		QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试及时消息...");
		messageQueueService.send(message);
		return "ok";
	}
	
	@RequestMapping("/send/{seconds}")
	public String send(@PathVariable("seconds") int seconds){
		System.out.println("发送延迟消息...");
		QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试延时消息...");
		message.setType(MessageTypeEnum.DELAYED.getIndex());
		message.setSeconds(seconds);
		messageQueueService.send(message);
		return "ok";
	}

	@Autowired
	private IAckMessageSendService ackMessageSendService;

	@GetMapping("/ack")
	public String ack(){
		System.out.println("*****************");
		QueueMessage message = new QueueMessage(MessageQueueConstants.ACK_QUEUE_NAME, "测试ack消息...");
		message.setExchange(MessageQueueConstants.ACK_EXCHANGE_NAME);
		ackMessageSendService.send(message);
		return "ok";
	}
}
