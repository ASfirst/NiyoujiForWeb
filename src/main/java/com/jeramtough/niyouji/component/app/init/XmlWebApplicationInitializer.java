package com.jeramtough.niyouji.component.app.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * @author 11718
 * on 2018  January 05 Friday 10:02.
 */

public class XmlWebApplicationInitializer extends AbstractDispatcherServletInitializer
{
	@Override
	protected WebApplicationContext createServletApplicationContext()
	{
		XmlWebApplicationContext cxt = new XmlWebApplicationContext();
		cxt.setConfigLocation("/WEB-INF/spring-config.xml");
		return cxt;
	}
	
	@Override
	protected String[] getServletMappings()
	{
		return new String[]{"*.do"};
	}
	
	@Override
	protected WebApplicationContext createRootApplicationContext()
	{
		return null;
	}
}
