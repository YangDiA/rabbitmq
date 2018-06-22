package com.book.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.rabbitmq.constants.MessageQueueConstants;

/**
 * 系统队列配置
 * 主要定义默认交换机bean以及延迟消息相关队列
 * @author victor
 *
 */
@Configuration
public class SystemQueueConfiguration {

	/**
	 * 默认及时消息交换机
	 * @return
	 */
	@Bean("defaultDirectExchange")
	public DirectExchange defaultDirectExchange() {
		return new DirectExchange(MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME, true, false);
	}
	
	
	/**
	 * 默认广播交换机对象
	 * @return
	 */
	@Bean("defaultFanoutExchange")
	public FanoutExchange defaultFanoutExchange() {
		return new FanoutExchange(MessageQueueConstants.DEFAULT_FANOUT_EXCHANGE_NAME, true, false);
	}
	
	
	/**
	 * 默认topic路由方式交换机
	 * @return
	 */
	@Bean("defaultTopicExchange")
	public TopicExchange defaultTopicExchange() {
		return new TopicExchange(MessageQueueConstants.DEFAULT_TOPIC_EXCHANGE_NAME, true, false);
	}
	
	
	/**
	 * 默认headers交换机
	 * @return
	 */
	@Bean("defaultHeadersExchange")
	public HeadersExchange defaultHeadersExchange() {
		return new HeadersExchange(MessageQueueConstants.DEFAULT_HEADERS_EXCHANGE_NAME, true, false);
	}
	
	
	/**
	 * 默认延迟消息死信队列
	 * @return
	 */
	@Bean
	public Queue defaultDeadLetterQueue() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange",MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME);//设置交换机路由
		arguments.put("x-dead-letter-routing-key", MessageQueueConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME);//设置转发队列名称
		Queue queue = new Queue(MessageQueueConstants.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);
		return queue; 
	}

	@Bean
	public Binding  defaultDeadLetterBinding() {
		Binding bind = BindingBuilder.bind(defaultDeadLetterQueue()).to(defaultDirectExchange()).with(MessageQueueConstants.DEFAULT_DEAD_LETTER_QUEUE_NAME);
		return bind;
	}
	
	
	/**
	 * 默认延迟消息死信接受转发消息队列
	 * @return
	 */
	@Bean
	public Queue defaultRepeatTradeQueue() {
		Queue queue = new Queue(MessageQueueConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);
		return queue; 
	}
	
	
	@Bean
	public Binding  defaultRepeatTradeBinding() {
		return BindingBuilder.bind(defaultRepeatTradeQueue()).to(defaultDirectExchange()).with(MessageQueueConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
	}
	
	
	
	
}
