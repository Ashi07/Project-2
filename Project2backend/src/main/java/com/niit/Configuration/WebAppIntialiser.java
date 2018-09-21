package com.niit.Configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppIntialiser extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[]
				{
						DBConfig.class
				};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]
				{
						WebAppConfig.class
				};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}
	
}