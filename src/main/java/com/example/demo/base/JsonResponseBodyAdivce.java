package com.example.demo.base;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import common.util.JsonUtil;

/** 
* 封装正常返回值为ResponseResult
* @date 2019-09-30 09:26:46
* @author LEI
* TODO
*/
@ControllerAdvice
public class JsonResponseBodyAdivce implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		Object result;
		if(body instanceof ResponseResult) {
			result = body;
		}else if(body instanceof String){
			//因为StringHttpMessageConverter会直接把字符串写入body, 所以字符串特殊处理
			result = JsonUtil.toJson(ResponseResult.success(body));
		}else {
			result = ResponseResult.success(body);
		}
		System.out.println("返回值："+JsonUtil.toJson(result));
		return result;
	}
	
}
