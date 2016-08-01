package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;

public class GetParameterValuesResponse extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ParameterValueList parameterList;
	
	public GetParameterValuesResponse( Node node ) { super(node); }
    public GetParameterValuesResponse() {}
    public GetParameterValuesResponse( ParameterValueList parameterlist )
    {
        this.parameterList = parameterlist;
    }
    

	public ParameterValueList getParameterList()
	{
		return parameterList;
	}
	public void setParameterList(ParameterValueList parameterList)
	{
		this.parameterList = parameterList;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:GetParameterValuesResponse>");
        sb.append("<ParameterList SOAP-ENC:arrayType=\"cwmp:ParameterValueStruct[" + this.parameterList.getItems().size() + "]\">" + this.parameterList.toXml() + "</ParameterList>");
        sb.append("</cwmp:GetParameterValuesResponse>");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempParameterList = this.getNode(node, "ParameterList");
        if ((tempParameterList == null))
        {
        }
        else
        {
            this.parameterList = new ParameterValueList(tempParameterList);
        }
	}

	@Override
	public Object clone()
	{
		GetParameterValuesResponse ret = new GetParameterValuesResponse();
		ret.parameterList = ((ParameterValueList)(this.parameterList.clone()));
        return ret;
	}
	
}

/*
POST / HTTP/1.1
Host: 192.168.5.246:9093
User-Agent: XXX_TR69_CPE_04_00
Connection: keep-alive
SOAPAction: 
Content-Type: text/xml
Content-Length: 902

<SOAP-ENV:Envelope
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:cwmp="urn:dslforum-org:cwmp-1-2">
    <SOAP-ENV:Header>
      <cwmp:ID SOAP-ENV:mustUnderstand="1">3</cwmp:ID>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
      <cwmp:GetParameterValuesResponse>
        <ParameterList SOAP-ENC:arrayType="cwmp:ParameterValueStruct[000001]">
            <ParameterValueStruct>
              <Name>Device.DeviceInfo.SoftwareVersion</Name>
              <Value xsi:type="xsd:string">643-19953-19916-20025-20027-485-597</Value>
            </ParameterValueStruct>
        </ParameterList>
      </cwmp:GetParameterValuesResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
