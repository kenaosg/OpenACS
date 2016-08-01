package com.cwmp.acsserver.acs.soap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.EnvelopeBodyTypeEnum;

public class SoapBody extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private CwmpObjBase msg;//rpc, fault...
	
    public SoapBody( Node node ) { super(node); }
    public SoapBody() { }
	
	public CwmpObjBase getMsg()
	{
		return msg;
	}
	public void setMsg(CwmpObjBase msg)
	{
		this.msg = msg;
	}
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<SOAP-ENV:Body>");
        sb.append("\n");
        sb.append(this.msg.toXml());
        sb.append("</SOAP-ENV:Body>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		NodeList nl = node.getChildNodes();
		Node item;
		for(int i = 0; i < nl.getLength(); i++)
		{
			item = nl.item(i);
			if(item.getLocalName() == null)
			{
				continue;
			}
			else if("Fault".equals(item.getLocalName()))
			{
				SoapFault sf = new SoapFault(item);
                this.msg = sf;
                this.msg.setEnvelopeContentTypeEnum(EnvelopeBodyTypeEnum.SoapFault);
			}
			else
			{
				Object obj = null;
				try
				{
					Class cla = Class.forName("com.cwmp.acsserver.acs.structrpc." + item.getLocalName());
					Constructor<?> con = cla.getConstructor(Node.class);
					obj = con.newInstance(item);
				} catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (NoSuchMethodException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (InstantiationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.msg = (CwmpObjBase)(obj);
				this.msg.setEnvelopeContentTypeEnum(Enum.valueOf(EnvelopeBodyTypeEnum.class, item.getLocalName()));
			}
		}
	}

	@Override
	public Object clone()
	{
		SoapBody ret = new SoapBody();
        ret.msg = (SoapBody)this.msg.clone();
        return ret;
	}
}
/*
HTTP/1.1 200 OK
Content-Length: 533
Content-Type: text/xml; charset=utf-8
Server: Microsoft-HTTPAPI/2.0
Date: Thu, 21 Jul 2016 06:49:00 GMT

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:cwmp="urn:dslforum-org:cwmp-1-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<SOAP-ENV:Header>
		<cwmp:HoldRequests SOAP-ENV:mustUnderstand="1">0</cwmp:HoldRequests>
	</SOAP-ENV:Header>
	<SOAP-ENV:Body>
		<cwmp:InformResponse>
			<MaxEnvelopes>1</MaxEnvelopes>
		</cwmp:InformResponse>
	</SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
