package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


//Servlet-context.xml 대체

@ComponentScan(basePackages = {"com.company.controller"}) // <context:component-scan base-package="com.company.controller"/>
@EnableWebMvc
public class ServletConfig implements WebMvcConfigurer {
	
	/*
	 * <beans:bean
	 * 		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * 		<beans:property name="prefix" value="/WEB-INF/views/" /> 
	 * 		<beans:property name="suffix" value=".jsp" /> 
	 * </beans:bean>
	 */
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		registry.viewResolver(resolver);
	}
	/* <resources mapping="/resources/**" location="/resources/" /> */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/*
	 * <!-- 파일 업로드 객체 생성 -->
	<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	 * */
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver =new CommonsMultipartResolver();
		return resolver;
	}
}














