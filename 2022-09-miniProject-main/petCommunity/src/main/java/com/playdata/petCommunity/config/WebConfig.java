package com.playdata.petCommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.playdata.petCommunity.util.interceptor.AuthHandler;

@Configuration 
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public AuthHandler userAuthHandler()	{
		return new AuthHandler();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( userAuthHandler() )
				.addPathPatterns("/user/**")
				.addPathPatterns("/doctor/**")
				.addPathPatterns("/pet/**")
				.addPathPatterns("/notice/**")
				.addPathPatterns("/comment/**")
				
				.excludePathPatterns("/notice/main")
				.excludePathPatterns("/user/userJoin")
				.excludePathPatterns("/user/userJoinForm")
				.excludePathPatterns("/user/userLogin")
				.excludePathPatterns("/user/userLoginForm")
				
				.excludePathPatterns("/doctor/doctorJoin")
				.excludePathPatterns("/doctor/doctorJoinForm")
				.excludePathPatterns("/doctor/doctorLogin")
				.excludePathPatterns("/doctor/doctorLoginForm")
				
				.excludePathPatterns("/notice/noticeListAll")
				.excludePathPatterns("/notice/noticeDetail");
	}
	
}
