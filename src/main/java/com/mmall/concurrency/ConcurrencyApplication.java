package com.mmall.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	//在spring boot 中 添加 filter
	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return  registrationBean;
	}

	//在spring boot 中 添加interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}

}
