package com.book.rabbitmq.constants;

/**
 * rabbitmq 消息队列常量
 * @author victor
 *
 */
public class MessageQueueConstants {
	
	/**
	 * 默认即时消息交换机名称
	 */
	public static String DEFAULT_DIRECT_EXCHANGE_NAME = "default.direct.exchange";
	
	/**
	 * 默认广播交换机名称
	 */
	public static String  DEFAULT_FANOUT_EXCHANGE_NAME = "default.fanout.exchange";
	
	/**
	 * 默认topic类型消息交换机
	 */
	public static String DEFAULT_TOPIC_EXCHANGE_NAME = "default.topic.exchange";
	
	/**
	 * 默认headers类型消息交换机
	 */
	public static String DEFAULT_HEADERS_EXCHANGE_NAME = "default.headers.exchange";
	
	/**
	 * 默认作为延时功能的死信队列名称
	 */
	public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "default.dead.letter.queue";
	
	/**
	 * 默认作为延时功能的死信消息转发的接收队列名称
	 */
	public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "default.repeat.trade.queue";
	
	
	
	/**
	 * hello消息队列名称
	 */
	public static final String QUEUE_HELLO_NAME = "app.queue.hello";
	
	
	/**
	 * 测试多线程消费队列名称
	 */
	public static final String CONCURRENT_CONSUMERS_NAME = "app.queue.concurrent.consumers";
	
	
	public static final String ACK_QUEUE_NAME = "app.ack.queue";
	
	public static final String ACK_EXCHANGE_NAME = "app.ack.exchange";

}
