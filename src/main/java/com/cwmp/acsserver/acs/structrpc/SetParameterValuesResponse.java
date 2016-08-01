package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class SetParameterValuesResponse extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private int status;
	
	public SetParameterValuesResponse( Node node ) {super(node);}
    public SetParameterValuesResponse() {}
    public SetParameterValuesResponse( int status )
    {
        this.status = status;
    }
    
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:SetParameterValuesResponse>");
        sb.append("\n");
        sb.append("<Status>" + this.status + "</Status>");
        sb.append("\n");
        sb.append("</cwmp:SetParameterValuesResponse>");
        sb.append("\n");
        return sb.toString();
	}
	@Override
	public void parseNode(Node node)
	{
		Node tempStatus = this.getNode(node, "Status");
        if ((tempStatus == null))
        {
        }
        else
        {
            this.status = Integer.parseInt(this.getCurrentNodeValue(tempStatus));
        }
	}
	@Override
	public Object clone()
	{
//		SetParameterValuesResponse ret = new SetParameterValuesResponse();
//		ret.status = this.status;
//      return ret;
		
		return this.clone();
	}   
}

/*
POST / HTTP/1.1
Host: 192.168.5.246:9093
User-Agent: XXX_TR69_CPE_04_00
Connection: keep-alive
SOAPAction: 
Content-Type: text/xml
Content-Length: 566

<SOAP-ENV:Envelope
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:cwmp="urn:dslforum-org:cwmp-1-2">
    <SOAP-ENV:Header>
      <cwmp:ID SOAP-ENV:mustUnderstand="1">1</cwmp:ID>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
      <cwmp:SetParameterValuesResponse>
        <Status>0</Status>
      </cwmp:SetParameterValuesResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
