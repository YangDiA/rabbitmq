package com.book.rabbitmq.config.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.rabbitmq.constants.MessageQueueConstants;

/**
 * 测试多线程消费队列
 * @author victor
 *
 */
@Configuration
public class ConcurrentConsumersQueueConfiguration {

	
	@Autowired
	@Qualifier("defaultDirectExchange")
	private DirectExchange exchange;
	
	
	@Bean("concurrentConsumersContainerFactory")
	public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setPrefetchCount(50);//每个消费者最大投递数量
		factory.setConcurrentConsumers(10);//消费者数量
		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
	
	@Bean
	public Queue concurrentConsumersQueue() {
		Queue queue = new Queue(MessageQueueConstants.CONCURRENT_CONSUMERS_NAME,true,false,false);
		return queue; 
	}
	
	@Bean
	public Binding  concurrentConsumersBinding() {
		return BindingBuilder.bind(concurrentConsumersQueue()).to(exchange).with(MessageQueueConstants.CONCURRENT_CONSUMERS_NAME);
	}
	
	
}
