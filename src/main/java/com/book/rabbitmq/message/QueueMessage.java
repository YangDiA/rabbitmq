package com.book.rabbitmq.message;

import java.io.Serializable;
import java.util.Date;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.enums.message.MessageGroupEnum;
import com.book.rabbitmq.enums.message.MessageStatusEnum;
import com.book.rabbitmq.enums.message.MessageTypeEnum;

/**
 * 消息队列载体类
 * @author victor
 *
 */
public class QueueMessage implements Serializable{

	private static final long serialVersionUID = 140085406084367370L;
	
	
	public QueueMessage() {
		super();
	}
	
	
	public QueueMessage(String queueName, String message) {
		super();
		this.queueName = queueName;
		this.message = message;
		this.exchange = MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME;
		this.type = MessageTypeEnum.DEFAULT.getIndex();
		this.group = MessageGroupEnum.DEFAULT.getIndex();
		this.timestamp = new Date();
		this.status = MessageStatusEnum.DEFAULT.getIndex();
	}



	public QueueMessage(String exchange ,String queueName, Integer type, Integer group, String message, Integer status) {
		super();
		this.queueName = queueName;
		this.type = type;
		this.exchange = exchange;
		this.group = group;
		this.message = message;
		this.status = status;
		this.timestamp = new Date();
	}

	public QueueMessage(String exchange ,String queueName, Integer type, Integer group, Date timestamp, String message, Integer status,int retry, int maxRetry) {
		super();
		this.queueName = queueName;
		this.type = type;
		this.exchange = exchange;
		this.group = group;
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.retry = retry;
		this.maxRetry = maxRetry;
	}

	private String exchange;
	
	private String queueName;
	
	private Integer type;
	
	private Integer group;
	
	private Date timestamp;
	
	private String message;
	
	private Integer status;
	
	private int retry = 0;
	
	private int maxRetry = 10;
	
	private int seconds = 1;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}


	public int getSeconds() {
		return seconds;
	}


	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
}
