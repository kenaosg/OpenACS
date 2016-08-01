package acsserver;

//************************************************************
//for xml parse

//These are the JAXP APIs
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;













//These classes are for the exceptions that can be thrown when the XML document is parsed:
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;

//These classes read the sample XML file and manage output:
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;





import java.util.ArrayList;








//Finally, import the W3C definitions for a DOM, DOM exceptions, entities and nodes:
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//************************************************************

import org.apache.log4j.Logger;
import org.junit.Test;

import com.cwmp.acsserver.acs.soap.SoapEnvelope;
import com.cwmp.acsserver.acs.soap.SoapHeader;
import com.cwmp.acsserver.acs.structprimitive.EventList;
import com.cwmp.acsserver.acs.structprimitive.EventStruct;
import com.cwmp.acsserver.acs.structprimitive.ParameterKeyType;
import com.cwmp.acsserver.acs.structprimitive.ParameterNames;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueStruct;
import com.cwmp.acsserver.acs.structrpc.GetParameterValues;
import com.cwmp.acsserver.acs.structrpc.GetParameterValuesResponse;
import com.cwmp.acsserver.acs.structrpc.Inform;
import com.cwmp.acsserver.acs.structrpc.InformResponse;
import com.cwmp.acsserver.acs.structrpc.SetParameterValues;
import com.cwmp.acsserver.acs.structrpc.SetParameterValuesResponse;

public class RpcStructTest
{
	private static Logger logger = Logger.getLogger(RpcStructTest.class);
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	private SoapEnvelope se;
	
	public RpcStructTest()
	{
		this.dbFactory = DocumentBuilderFactory.newInstance();
		this.dbFactory.setNamespaceAware(true);
		try
		{
			this.docBuilder = this.dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getEnvelope(String docName)
	{
		try
		{
			this.doc = this.docBuilder.parse(docName);
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.se = new SoapEnvelope(doc.getFirstChild());
	}
	
	@Test
	public void testGetParameterValues()
	{
		this.getEnvelope("D://report//GetParameterValues.xml");	
		
		logger.debug(this.se.getVersion());
		logger.debug(this.se.getHeader().getHoldRequests().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		GetParameterValues msg = (GetParameterValues)this.se.getBody().getMsg();
		ParameterNames pn = msg.getParameterNames();
		logger.debug(pn.getItems());
		logger.debug("*************************GetParameterValues******************************");
	}
	
	@Test
	public void testGetParameterValuesResponse()
	{
		this.getEnvelope("D://report//GetParameterValuesResponse.xml");	
		
		logger.debug(this.se.getHeader().getId().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		
		GetParameterValuesResponse msg = (GetParameterValuesResponse)this.se.getBody().getMsg();
		ParameterValueList pvl = msg.getParameterList();
		ArrayList<ParameterValueStruct> items = pvl.getItems();
		for(int i = 0; i < items.size(); i++)
		{
			logger.debug(items.get(i).getName());
			logger.debug(items.get(i).getValue());
			logger.debug(items.get(i).getValueType());
		}
		logger.debug("*************************GetParameterValuesResponse******************************");
	}
	
	@Test
	public void testSetParameterValues()
	{
		this.getEnvelope("D://report//SetParameterValues.xml");	
		
		logger.debug(this.se.getHeader().getHoldRequests().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		SetParameterValues msg = (SetParameterValues)this.se.getBody().getMsg();
		ParameterValueList pvl = msg.getParameterList();
		ArrayList<ParameterValueStruct> items = pvl.getItems();
		for(int i = 0; i < items.size(); i++)
		{
			logger.debug(items.get(i).getName());
			logger.debug(items.get(i).getValue());
			logger.debug(items.get(i).getValueType());
		}
		
		ParameterKeyType pkt = msg.getParameterKey();
		logger.debug(pkt.getValue());
		logger.debug("****************************SetParameterValues***************************");
	}
	
	@Test
	public void testSetParameterValuesResponse()
	{
		this.getEnvelope("D://report//SetParameterValuesResponse.xml");	
		
		logger.debug(this.se.getHeader().getId().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		SetParameterValuesResponse msg = (SetParameterValuesResponse)this.se.getBody().getMsg();
		logger.debug(msg.getStatus());
		logger.debug("****************************SetParameterValuesResponse***************************");
	}

	@Test
	public void testInform()
	{
		this.getEnvelope("D://report//Inform.xml");	
		
		logger.debug(this.se.getHeader().getId().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		Inform msg = (Inform)this.se.getBody().getMsg();
		logger.debug(msg.getCurrentTime());
		logger.debug(msg.getDeviceId().getOui());
		logger.debug(msg.getDeviceId().getProductClass());
		logger.debug(msg.getDeviceId().getSerialNumber());
		logger.debug(msg.getDeviceId().getManufacturer());
		logger.debug(msg.getMaxEnvelopes());
		logger.debug(msg.getRetryCount());
		
		ArrayList<EventStruct> items = msg.getEvent().getItems();
		for(int i = 0; i < items.size(); i++)
		{
			logger.debug(items.get(i).getEventCode());
			logger.debug(items.get(i).getCommandKey());
		}
		
		ParameterValueList pvl = msg.getParameterList();
		ArrayList<ParameterValueStruct> items1 = pvl.getItems();
		for(int i = 0; i < items1.size(); i++)
		{
			logger.debug(items1.get(i).getName());
			logger.debug(items1.get(i).getValue());
			logger.debug(items1.get(i).getValueType());
		}
		
		logger.debug("****************************Inform***************************");
	}

	@Test
	public void testInformResponse()
	{
		this.getEnvelope("D://report//InformResponse.xml");	
		
		logger.debug(this.se.getHeader().getHoldRequests().getValue());
		logger.debug(this.se.getBody().getMsg().getEnvelopeContentTypeEnum());
		InformResponse msg = (InformResponse)this.se.getBody().getMsg();
		logger.debug(msg.getMaxEnvelopes());
		
		logger.debug("****************************InformResponse***************************");
	}
}
