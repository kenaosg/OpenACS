package com.cwmp.acsserver.listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cwmp.acsserver.acs.CpeEntity;

public class MyContextLoaderListener extends ContextLoaderListener
{
	private static Logger logger = Logger.getLogger(MyContextLoaderListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		super.contextInitialized(sce);		
		logger.info("MyContextLoaderListener...");
		
		ServletContext sc = sce.getServletContext();		
		//ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		Map<String, CpeEntity> cpes = Collections.synchronizedMap(new HashMap<String, CpeEntity>(128));
		sc.setAttribute("cpes", cpes);
		
		//start thread which will process test case
		
	}
}