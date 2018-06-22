package com.book.rabbitmq.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class JSONUtils {
	
	/**
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static JSONObject json2obj(String json){
		return JSON.parseObject(json); 
	}
	
	/**
	 * 
	 * @param json
	 * @param clz
	 * @return 
	 * @desc JSONתBean
	 */
	public static <T> T toBean(String json, Class<T> clz) {
		return JSON.parseObject(json, clz);
	}

	/**
	 * 
	 * @desc
	 * @param json
	 * @param clz
	 * @return 
	 */
	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, T> map = JSON.parseObject(json,new TypeReference<Map<String, T>>() {});
		return map;
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = JSON.parseObject(json,new TypeReference<Map<String, Object>>() {});
		return map;
	}

	/**
	 * 
	 * @param json
	 * @param clz
	 * @return 
	 */
	public static <T> List<T> toList(String json, Class<T> clz) {
		return JSON.parseArray(json, clz);
	}
	
}
