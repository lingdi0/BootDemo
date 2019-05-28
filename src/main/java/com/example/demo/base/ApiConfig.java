package com.example.demo.base;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.example.demo.interceptor.ApiInterceptor;

/** 
* @date 2019-04-29 17:26:21
* @author LEI
* TODO
*/
@SpringBootConfiguration
public class ApiConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new ApiInterceptor());
	}

}
