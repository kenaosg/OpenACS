package com.cwmp.acsserver.acs.soap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class SoapEnvelope extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private CwmpVersionEnum version = CwmpVersionEnum.Ver_1_0;
    private SoapHeader header;
    private SoapBody body;
    
    public SoapEnvelope( Node node ) { super(node); }
    public SoapEnvelope() {}

	public CwmpVersionEnum getVersion()
	{
		return version;
	}
	public void setVersion(CwmpVersionEnum version)
	{
		this.version = version;
	}
	public SoapHeader getHeader()
	{
		return header;
	}
	public void setHeader(SoapHeader header)
	{
		this.header = header;
	}
	public SoapBody getBody()
	{
		return body;
	}
	public void setBody(SoapBody body)
	{
		this.body = body;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:cwmp=\"urn:dslforum-org:cwmp-1-" + (byte)this.version.getId() + "\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append("\n");
        if (this.header != null)
        {
            sb.append(this.header.toXml());
            sb.append("\n");
        }
        if (this.body != null)
        {
            sb.append(this.body.toXml());
            sb.append("\n");
        }
        sb.append("</SOAP-ENV:Envelope>");
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
			if("Header".equals(item.getLocalName()))
			{
				this.header = new SoapHeader(item);
			}
			else if("Body".equals(item.getLocalName()))
			{
				this.body = new SoapBody(item);
			}	
		}
		Matcher matcher = Pattern.compile("cwmp-1-(\\d)").matcher(this.getCurrentNodeAttrValue(node, "xmlns:cwmp"));
		if(matcher.find())
		{
			String str = matcher.group(1);
			if(str == null) {this.version = CwmpVersionEnum.Ver_1_0;}
			else {this.version = CwmpVersionEnum.getById(Integer.parseInt(str));}
		}
		else
		{
			this.version = CwmpVersionEnum.Ver_1_0;
		}
	}

	@Override
	public Object clone()
	{
		SoapEnvelope ret = new SoapEnvelope();
        ret.header = (SoapHeader)this.header.clone();
        ret.body =(SoapBody)this.body.clone();
        return ret;
	}

}
