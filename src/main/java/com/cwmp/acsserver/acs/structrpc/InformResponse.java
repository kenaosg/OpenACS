package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class InformResponse extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private int maxEnvelopes;

    public InformResponse( Node node ) {super(node);}
    public InformResponse() {}
    public InformResponse( int maxenvelopes )
    {
        this.maxEnvelopes = maxenvelopes;
    }
    
	public int getMaxEnvelopes()
	{
		return maxEnvelopes;
	}

	public void setMaxEnvelopes(int maxEnvelopes)
	{
		this.maxEnvelopes = maxEnvelopes;
	}

	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:InformResponse>");
        sb.append("\n");
        sb.append("<MaxEnvelopes>" + this.maxEnvelopes + "</MaxEnvelopes>");
        sb.append("\n");
        sb.append("</cwmp:InformResponse>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempMaxEnvelopes = this.getNode(node, "MaxEnvelopes");
        if ((tempMaxEnvelopes == null))
        {
        }
        else
        {
            this.maxEnvelopes = Integer.parseInt(this.getCurrentNodeValue(tempMaxEnvelopes));
        }
	}

	@Override
	public Object clone()
	{
//		InformResponse ret = new InformResponse();
//        ret.maxEnvelopes = this.maxEnvelopes;
//        return ret;
		
		return this.clone();
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