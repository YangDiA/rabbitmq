package com.book.rabbitmq.processor.ack;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.utils.JSONUtils;
import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = MessageQueueConstants.ACK_QUEUE_NAME,containerFactory = "ackContainerFactory")
public class AckProcessor {

	@RabbitHandler
    public void process(@Payload String content,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,Channel channel) throws InterruptedException {
		System.out.println(JSONUtils.toJson(content));
		try {
			channel.basicAck(deliveryTag, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
