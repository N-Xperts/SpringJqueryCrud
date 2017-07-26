package com.sam.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class AppInitializer implements WebApplicationInitializer {
	

	@Override
	public void onStartup(ServletContext container) throws ServletException 
	{
		System.out.println("in onstartup");
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(Persistanceconfig.class);
		
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext dispatcherServlet=new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(AppConfig.class);
		
		ServletRegistration.Dynamic dispatcher=container.addServlet("dispatcher",new DispatcherServlet(dispatcherServlet));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");
	    System.out.println("out on startup");
    }
	
}
