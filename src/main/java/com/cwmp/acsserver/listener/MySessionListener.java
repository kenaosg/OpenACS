package com.cwmp.acsserver.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MySessionListener implements HttpSessionListener
{
	private static Logger logger = Logger.getLogger(MySessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent arg0) 
	{
		logger.debug("Start session listener...");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) 
	{
		logger.debug("MySessionListener: destroyed...");
		
		HttpSession hs = arg0.getSession();
		
	}
}

