package com.book.rabbitmq.enums.message;

import com.book.rabbitmq.enums.IndexedEnum;
import com.book.rabbitmq.enums.IndexedEnumUtil;
import com.google.common.collect.ImmutableMap;

public enum MessageGroupEnum implements IndexedEnum<MessageGroupEnum>{

	DEFAULT(10,"default");
	
	
	MessageGroupEnum(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	private static final ImmutableMap<Integer, MessageGroupEnum> INDEXS = IndexedEnumUtil.toIndexes(values());
	
	private int index;
	
	private String name;

	
	public static MessageGroupEnum indexOf(int index){
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
