package com.example.demo.base;

/** 
* @date 2019-09-30 09:41:54
* @author LEI
* TODO
*/

public class ExceptionBase extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	
	private String message;
	
	public ExceptionBase(int code,String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

	
}
