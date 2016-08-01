package com.cwmp.acsserver.acs.soap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class SoapHeader extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private SoapHeadID id;
    private SoapHeadHoldRequests holdRequests;
    
    public SoapHeader( Node node ) { super(node); }
    public SoapHeader() { }

	public SoapHeadID getId()
	{
		return id;
	}
	public void setId(SoapHeadID id)
	{
		this.id = id;
	}
	public SoapHeadHoldRequests getHoldRequests()
	{
		return holdRequests;
	}
	public void setHoldRequests(SoapHeadHoldRequests holdRequests)
	{
		this.holdRequests = holdRequests;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<SOAP-ENV:Header>");
        sb.append("\n");
        if (this.id != null)
        {
            sb.append("<cwmp:ID SOAP-ENV:mustUnderstand = \"1\">" + this.id.getValue() + "</cwmp:ID>");
            sb.append("\n");
        }
        if (this.holdRequests != null)
        {
            sb.append("<cwmp:HoldRequests SOAP-ENV:mustUnderstand = \"1\">" + this.holdRequests.getValue() + "</cwmp:HoldRequests>");
            sb.append("\n");
        }
        sb.append("</SOAP-ENV:Header>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		NodeList nodeList = node.getChildNodes();
		Node item;
		for (int i = 0; i < nodeList.getLength(); i++)
        {
			item = nodeList.item(i);
            if ("ID".equals(item.getLocalName()))
            {
                this.id = new SoapHeadID(item);
            }
            if ("HoldRequests".equals(item.getLocalName()))
            {
                this.holdRequests = new SoapHeadHoldRequests(item);
            }
        }
	}

	@Override
	public Object clone()
	{
		SoapHeader ret = new SoapHeader();
        ret.id = (SoapHeadID)this.id.clone();
        ret.holdRequests = (SoapHeadHoldRequests)this.id.clone();
        return ret;
	}
}