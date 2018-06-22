package com.book.rabbitmq.enums.message;

import com.book.rabbitmq.enums.IndexedEnum;
import com.book.rabbitmq.enums.IndexedEnumUtil;
import com.google.common.collect.ImmutableMap;

/**
 * 消息类型枚举
 * @author victor
 *
 */
public enum MessageTypeEnum implements IndexedEnum<MessageTypeEnum> {

	DEFAULT(10,"即时消息"),
	DELAYED(20,"延时消息");
	
	MessageTypeEnum(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	private static final ImmutableMap<Integer, MessageTypeEnum> INDEXS = IndexedEnumUtil.toIndexes(values());
	
	private int index;
	
	private String name;

	
	public static MessageTypeEnum indexOf(int index){
		return INDEXS.get(index);
	}

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
