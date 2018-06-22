package com.book.rabbitmq.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextReader implements ApplicationContextAware{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ApplicationContext appContext = null;//静态
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		appContext =  context;
		logger.info("ApplicationContext init ...");
	}
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T)appContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clz){
		return (T) appContext.getBean(clz);
	}
	

}
