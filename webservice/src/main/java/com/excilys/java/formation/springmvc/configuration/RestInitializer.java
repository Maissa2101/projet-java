package com.excilys.java.formation.springmvc.configuration;


import org.springframework.context.annotation.Profile;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Profile("!interface")
public class RestInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RestConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected FrameworkServlet createDispatcherServlet (WebApplicationContext wac) {
        DispatcherServlet ds = new DispatcherServlet(wac);
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

}