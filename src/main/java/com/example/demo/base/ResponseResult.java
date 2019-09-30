package com.example.demo.base;

import org.springframework.http.HttpStatus;

/** 
* 返回数据封装类
* @date 2019-09-30 09:19:21
* @author LEI
* TODO
*/

public class ResponseResult {
	
	private int code;
	
	private String message;
	
	private Object data;
	
	public ResponseResult() {
		
	}
	
	public static ResponseResult success() {
		return success(null);
	}
	public static ResponseResult success(Object data) {
		ResponseResult ro = new ResponseResult();
		ro.setCode(HttpStatus.OK.value());
		ro.setData(data);
		return ro;
	}
	public static ResponseResult fail(ExceptionBase ex) {
		ResponseResult ro = new ResponseResult();
		ro.setCode(ex.getCode());
		ro.setMessage(ex.getMessage());
		return ro;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
