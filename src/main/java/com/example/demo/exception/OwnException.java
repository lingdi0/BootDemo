package com.example.demo.exception;

import com.example.demo.base.ExceptionBase;

/** 
* 自定义异常类
* @date 2019-09-30 09:48:06
* @author LEI
* TODO
*/

public class OwnException {

	public static final ExceptionBase RESOURCE_NOT_EXSIT = new ExceptionBase(404, "资源不存在");
	
	public static final ExceptionBase SYS_INNER_ERROR = new ExceptionBase(500, "系统内部错误");
}
