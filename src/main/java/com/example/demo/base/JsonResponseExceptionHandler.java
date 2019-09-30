package com.example.demo.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.exception.OwnException;

/** 
* @date 2019-09-30 10:19:58
* @author LEI
* TODO
*/
@ControllerAdvice
@ResponseBody
/**
 * 异常返回处理类
 * @date 2019年9月30日 上午10:26:42
 * @author lj
 * TODO
 */
public class JsonResponseExceptionHandler {
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(Exception ex) {
		return ownExceptionHandler(OwnException.SYS_INNER_ERROR);
	}
	@ExceptionHandler(ExceptionBase.class)
	public Object ownExceptionHandler(ExceptionBase ex) {
		return ResponseResult.fail(ex);
	}
}
