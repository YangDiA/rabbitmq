package com.book.rabbitmq.utils;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;

/**
 * 
 * @author victor
 * @desc　字符串工具类
 */
public final class StringUtils {
	
	public static final String EMPTY = "";
	
	private static final Pattern REG_CHINESE_CHARS = Pattern.compile("[\\u4e00-\\u9fa5·]+");
	private static final Pattern REG_VALIDATE_IDCARD = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
	private static final Pattern REG_VALIDATE_MOBILE = Pattern.compile("^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[0-9])[0-9]{8}$");
	private static final Pattern REG_VALIDATE_ZIPCODE = Pattern.compile("[1-9]\\d{5}(?!\\d)");
	private static final Pattern REG_VALIDATE_SKU_CODE = Pattern.compile("[0-9]*");
	private static final Pattern REG_VALIDATE_DOUBLE_CODE = Pattern.compile("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$");
	
	/**
	 * 判断是否为null 或者 ""
	 * @param str 需要判断的字符串
	 * @return
	 */
	public static boolean isNullOrEmpty(String str){
		if(str == null) {
			return true;
		}
		if(str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为空白字符串 ""
	 * @param str 
	 * @return
	 */
	public static boolean isBlank(String str){
		return org.apache.commons.lang.StringUtils.isBlank(str);
	}
	
	/**
	 * 判断是否为json格式
	 * @param str 需要校验的字符串
	 * @return
	 */
	public static boolean isJson(String str){
		try {
			JSON.parseObject(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param length
	 * @return
	 * @desc 生成随机字符串 包含字符数字
	 */
	public static String generateCharAndNumrRamdom (int length){
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				buffer.append((char) (choice + random.nextInt(26)));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				buffer.append(String.valueOf(random.nextInt(10)));
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 首字母大写
	 * @param str 字符串
	 * @return
	 */
    public static String toUpperFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
    
    /**
     * 首字母小写
     * @param str 
     * @return
     */
    public static String toLowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
    
    /**
     * 是否为汉字串
     *
     * @param str 字符串
     * @return bool
     */
    public static boolean isChineseChars(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return REG_CHINESE_CHARS.matcher(str).matches();
    }
    
    /**
     * 判断是否为身份证
     * @param str 字符串
     * @return
     */
    public static boolean isIdcardNo(String str) {
        if (isNullOrEmpty(str))
            return false;
        return REG_VALIDATE_IDCARD.matcher(str).matches();
    }
    
   /**
    * 判断是否为手机号码
    * @param str
    * @return
    */
    public static boolean isMobileNo(String str) {
        if (isNullOrEmpty(str))
            return false;
        return REG_VALIDATE_MOBILE.matcher(str).matches();
    }
    
    /**
     * 判断是否为邮编
     * @param str
     * @return
     */
    public static boolean isZipCode(String str) {
        if (isNullOrEmpty(str))
            return false;
        return REG_VALIDATE_ZIPCODE.matcher(str).matches();
    }
    
    /**
     * 转换为UF-8编码
     * @param source 源字符串
     * @param charsetIn 源编码格式
     * @return
     */
    public static String toUTF8(String source, Charset charsetIn) {
        return new String(source.getBytes(charsetIn), Charsets.UTF_8);
    }
    
    /**
     * 是否为数字
     * @param str 源字符串
     * @return
     */
    public static boolean isNumber(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }
        return REG_VALIDATE_SKU_CODE.matcher(str).matches();
    }
    
    /**
     * 是否为dubbo
     * @param str 源字符串
     * @return
     */
    public  static  boolean isDouble(String str){
        if (isNullOrEmpty(str)) {
            return false;
        }
        return REG_VALIDATE_DOUBLE_CODE.matcher(str).matches();
    }
    
    /**
     * 是否为数字
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str) {
        return isNumber(str) || isDouble(str);
    }
    
    
    /**
     * 替换空格为指定字符
     * @param str 
     * @param separator 
     * @return
     */
    public static String replaceSpace(String str,String separator){
        String regEx = "[' ']+"; // 一个或多个空格
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll(separator).trim();
    }
    
    /**
     * 判断是否为中文 根据Unicode编码判断
     * @param strName
     * @return
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        }
        return false;
    }
    
    
    /**
     * 判断是否包含中文
     * @param strName
     * @return
     */
    public static boolean isHaveChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param obj 对象
     * @return
     * @desc 判断对象是否为空
     */
    public static boolean isNullOrEmpty(Object obj){
    	if(obj == null ){
    		return true;
    	}
    	return isNullOrEmpty(obj.toString());
    }
}
