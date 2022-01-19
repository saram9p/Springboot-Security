package com.cos.security1.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	// 해당 뷰리졸버 Mustache를 오버라이딩해서 재설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		MustacheViewResolver resolver = new MustacheViewResolver();
		resolver.setCharset("UTF-8"); // 인코딩을 UTF-8
		resolver.setContentType("text/html; charset=UTF-8"); // 던지는 데이터는 html 파일, 그리고 html 파일은 UTF-8 이라고 알려줌
		resolver.setPrefix("classpath:/templates/"); // classpath: 까지가 프로젝트
		resolver.setSuffix(".html"); // .html로 변경 하면 html 파일을 만들어도 mustache가  인식을 하게된다.
		
		registry.viewResolver(resolver); // registry로 뷰리졸버를 등록
	}
	
}
