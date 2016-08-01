package com.cwmp.acsserver.acs.soap;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structrpc.Fault;

public class SoapFault extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
    private String faultCode;
    private String faultString;
    private String faultActor;
    private Fault detail;
	public SoapFault( Node node ){super(node); }
    public SoapFault() { }
    
	public String getFaultCode()
	{
		return faultCode;
	}
	public void setFaultCode(String faultCode)
	{
		this.faultCode = faultCode;
	}
	public String getFaultString()
	{
		return faultString;
	}
	public void setFaultString(String faultString)
	{
		this.faultString = faultString;
	}
	public String getFaultActor()
	{
		return faultActor;
	}
	public void setFaultActor(String faultActor)
	{
		this.faultActor = faultActor;
	}
	public Fault getDetail()
	{
		return detail;
	}
	public void setDetail(Fault detail)
	{
		this.detail = detail;
	}
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<SOAP-ENV:Fault>");
        sb.append("\n");
        sb.append("<faultcode>" + this.faultCode + "</faultcode>");
        sb.append("\n");
        sb.append("<faultstring>" + this.faultString + "</faultstring>");
        sb.append("\n");
        if (this.faultActor != null)
        {
            sb.append("<faultactor>" + this.faultActor + "</faultactor>");
            sb.append("\n");
        }
        sb.append(this.detail.toXml());
        sb.append("\n");
        sb.append("</SOAP-ENV:Fault>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node faultCodeNd = this.getNode(node, "faultcode");
        if (faultCodeNd != null)
        {
            this.faultCode = faultCodeNd.getNodeValue();
        }
        Node faultStringNd = this.getNode(node, "faultstring");
        if (faultStringNd != null)
        {
            this.faultString = faultStringNd.getNodeValue();
        }
        Node faultActorNd = this.getNode(node, "faultactor");
        if (faultActorNd != null)
        {
            this.faultActor = faultActorNd.getNodeValue();
        }
        Node detailNd = this.getNode(node, "detail");
        if (detailNd != null)
        {
            Node cwmpFaultNode = this.getNode(detailNd, "Fault");
            if (cwmpFaultNode != null)
            {
                this.detail = new Fault(cwmpFaultNode);
            }
        }
	}

	@Override
	public Object clone()
	{
		SoapFault ret = new SoapFault();
        ret.faultActor = this.faultActor;
        ret.faultCode = this.faultCode;
        ret.faultString = this.faultString;
        ret.detail = (Fault)this.detail.clone();
        return ret;
	}

}
