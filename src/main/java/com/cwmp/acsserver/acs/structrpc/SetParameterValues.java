package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.ParameterKeyType;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;

public class SetParameterValues extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ParameterValueList parameterList;
    private ParameterKeyType parameterKey;

    public SetParameterValues( Node node ) {super(node);}
    public SetParameterValues() {}
    public SetParameterValues( ParameterValueList parameterlist, ParameterKeyType parameterkey )
    {
        this.parameterList = parameterlist;
        this.parameterKey = parameterkey;
    }

	public ParameterValueList getParameterList()
	{
		return parameterList;
	}

	public void setParameterList(ParameterValueList parameterList)
	{
		this.parameterList = parameterList;
	}

	public ParameterKeyType getParameterKey()
	{
		return parameterKey;
	}

	public void setParameterKey(ParameterKeyType parameterKey)
	{
		this.parameterKey = parameterKey;
	}

	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:SetParameterValues>");
        sb.append("\n");
        sb.append("<ParameterList SOAP-ENC:arrayType=\"cwmp:ParameterValueStruct[" + this.parameterList.getItems().size() + "]\">" + this.parameterList.toXml() + "</ParameterList>");
        sb.append("\n");
        sb.append("<ParameterKey>" + this.parameterKey.toXml() + "</ParameterKey>");
        sb.append("\n");
        sb.append("</cwmp:SetParameterValues>");
        sb.append("\n");
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
        Node tempParameterKey = this.getNode(node, "ParameterKey");
        if ((tempParameterKey == null))
        {
        }
        else
        {
            this.parameterKey = new ParameterKeyType(tempParameterKey);
        }
	}

	@Override
	public Object clone()
	{
		SetParameterValues ret = new SetParameterValues();
		ret.parameterList = ((ParameterValueList)(this.parameterList.clone()));
		ret.parameterKey = ((ParameterKeyType)(this.parameterKey.clone()));
        return ret;
	}	
}
/*
HTTP/1.1 200 OK
Content-Length: 3942
Content-Type: text/xml; charset=utf-8
Server: Microsoft-HTTPAPI/2.0
Date: Thu, 21 Jul 2016 06:49:21 GMT

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:cwmp="urn:dslforum-org:cwmp-1-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<SOAP-ENV:Header>
		<cwmp:HoldRequests SOAP-ENV:mustUnderstand="1">0</cwmp:HoldRequests>
	</SOAP-ENV:Header>
	<SOAP-ENV:Body>
		<cwmp:SetParameterValues>
			<ParameterList SOAP-ENC:arrayType="cwmp:ParameterValueStruct[16]">
				<ParameterValueStruct>
					<Name>Device.FAP.PerfMgmt.Config.1.Enable</Name>
					<Value xsi:type="xsd:">1</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.FAP.PerfMgmt.Config.1.Username</Name>
					<Value xsi:type="xsd:">admin</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.FAP.PerfMgmt.Config.1.Password</Name>
					<Value xsi:type="xsd:">admin</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Traces.LogServer.ConnectingPolicy</Name>
					<Value xsi:type="xsd:">Always</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Traces.LogServer.CpuhPort</Name>
					<Value xsi:type="xsd:">1111</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Traces.LogServer.CpulPort</Name>
					<Value xsi:type="xsd:">7777</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Traces.LogServer.CpuhFileSize</Name>
					<Value xsi:type="xsd:">1</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Traces.LogServer.CpulFileSize</Name>
					<Value xsi:type="xsd:">10</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Upload.Enable</Name>
					<Value xsi:type="xsd:">1</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Upload.Username</Name>
					<Value xsi:type="xsd:">admin</Value>
				</ParameterValueStruct>
				<ParameterValueStruct>
					<Name>Device.X_BC0304_DebugMgmt.Upload.Password</Name>
					<Value xsi:type="xsd:">admin</Value>
				</ParameterValueStruct>
			</ParameterList>
			<ParameterKey>key35348</ParameterKey>
		</cwmp:SetParameterValues>
	</SOAP-ENV:Body>
</SOAP-ENV:Envelope>
*/
