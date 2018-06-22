package com.book.rabbitmq.config.queue;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.rabbitmq.constants.MessageQueueConstants;

/**
 * 消息确认队列测试
 * @author victor
 *
 */
@Configuration
public class AckQueueConfiguration {

	
	@Bean("ackDirectExchange")
	public DirectExchange acktExchange() {
		return new DirectExchange(MessageQueueConstants.ACK_EXCHANGE_NAME, true, false);
	}
	
	@Bean
	public Queue ackQueue() {
		Queue queue = new Queue(MessageQueueConstants.ACK_QUEUE_NAME,true,false,false);
		return queue; 
	}
	
	@Bean
	public Binding  ackBinding() {
		return BindingBuilder.bind(ackQueue()).to(acktExchange()).with(MessageQueueConstants.ACK_QUEUE_NAME);
	}
	
	@Bean("ackContainerFactory")
	public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//必须显示确认
		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
	
}
