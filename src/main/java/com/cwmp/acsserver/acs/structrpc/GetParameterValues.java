package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.ParameterNames;

public class GetParameterValues extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ParameterNames parameterNames;
	
	public GetParameterValues(Node node) { super(node); }
	public GetParameterValues() {}
	public GetParameterValues(ParameterNames parameterNames)
	{
		this.parameterNames = parameterNames;
	}

	public ParameterNames getParameterNames() 
	{
		return parameterNames;
	}
	public void setParameterNames(ParameterNames parameterNames) 
	{
		this.parameterNames = parameterNames;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<cwmp:GetParameterValues>"); 
		sb.append("\n");
        sb.append("<ParameterNames SOAP-ENC:arrayType=\"cwmp:string[" + this.parameterNames.getItems().size() + "]\">" + this.parameterNames.toXml() + "</ParameterNames>");
        sb.append("\n");
        sb.append("</cwmp:GetParameterValues>");
        sb.append("\n");
        return sb.toString();
	}
	@Override
	public void parseNode(Node node)
	{
		Node tempParameterNames = this.getNode(node, "ParameterNames");
		if(tempParameterNames == null)
		{	}
		else
		{
			this.parameterNames = new ParameterNames(tempParameterNames);
		}
	}
	@Override
	public Object clone()
	{
		GetParameterValues ret = new GetParameterValues();
		ret.parameterNames = (ParameterNames)(this.parameterNames.clone());
		return ret;
	}	
}

/*
HTTP/1.1 200 OK
Content-Length: 645
Content-Type: text/xml; charset=utf-8
Server: Microsoft-HTTPAPI/2.0
Date: Thu, 21 Jul 2016 06:49:31 GMT

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:cwmp="urn:dslforum-org:cwmp-1-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<SOAP-ENV:Header>
		<cwmp:HoldRequests SOAP-ENV:mustUnderstand="1">0</cwmp:HoldRequests>
	</SOAP-ENV:Header>
	<SOAP-ENV:Body>
		<cwmp:GetParameterValues>
			<ParameterNames SOAP-ENC:arrayType="cwmp:string[1]">
				<string>
					Device.DeviceInfo.SoftwareVersion
				</string>
			</ParameterNames>
		</cwmp:GetParameterValues>
	</SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
