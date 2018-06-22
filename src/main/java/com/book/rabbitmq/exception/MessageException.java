package com.book.rabbitmq.exception;

public class MessageException extends RuntimeException{

	private static final long serialVersionUID = 140085406084367372L;

	private int code ;
	
	private String message;
	
	private String data;
	
	public MessageException() {
		super();
	}

	public MessageException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
