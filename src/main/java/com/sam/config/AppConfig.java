package com.sam.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan({"com.sam.*"})
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("inside addview");
	    registry.addViewController("/").setViewName("/index");
	}
  
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		System.out.println("inside resourcehndl");
		registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/**");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
        registry.addResourceHandler("/font/**").addResourceLocations("/font/**");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
	}
	
	public void configureDefaultServlet(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver ViewResolver()
	{
		System.out.println("inside internal");
		InternalResourceViewResolver bean=new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	@Bean(name="messageSource")
	public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	@Bean
	@Deprecated
	public WebContentInterceptor webContentInterceptor()
	{
		WebContentInterceptor interceptor=new WebContentInterceptor();
		interceptor.setCacheSeconds(0);
		interceptor.setUseExpiresHeader(false);
		interceptor.setUseCacheControlHeader(true);
		interceptor.setUseCacheControlNoStore(true);
		return interceptor;
	}
	

}
