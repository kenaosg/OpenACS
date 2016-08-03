package com.cwmp.acsserver.tools.singleton;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentBuilderFactorySingleton
{
	private DocumentBuilderFactory dbFactory;
	
	private DocumentBuilderFactorySingleton()
	{
		this.dbFactory = DocumentBuilderFactory.newInstance();
		this.dbFactory.setNamespaceAware(true);
	}
	
	public DocumentBuilder getDocBuilder()
	{
		try
		{
			synchronized(this)
			{
				return this.dbFactory.newDocumentBuilder();
			}
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static DocumentBuilderFactorySingleton getInstance()
	{
		return DocumentBuilderFactorySingleton.DocumentBuilderFactorySingletonHolder.documentBuilderFactorySingleton;
	}
	
	private static class DocumentBuilderFactorySingletonHolder
	{
		private static DocumentBuilderFactorySingleton documentBuilderFactorySingleton = new DocumentBuilderFactorySingleton();
	}
}
