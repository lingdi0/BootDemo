package com.example.demo.interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
* @date 2019-04-29 17:25:22
* @author LEI
* TODO
*/

public class ApiInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("--------------------");
		System.out.println("url:"+request.getMethod()+":"+request.getRequestURI());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRequestURL().toString());
		request.getParameterMap().entrySet().forEach(t->System.out.println(t.getKey()+":"+t.getValue()[0]));
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String str = "";
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		if(sb.length()>0) {
			System.out.println("body->"+sb.toString());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LocalDateTime start = LocalDateTime.now();
		System.out.println(start.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		LocalDateTime end = LocalDateTime.now();
		System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println("耗时:"+ChronoUnit.MILLIS.between(start, end)+"ms");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		System.out.println("--------------------\n");
	}

}
