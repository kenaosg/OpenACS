package com.cwmp.acsserver.acs.structrpc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.DeviceIdStruct;
import com.cwmp.acsserver.acs.structprimitive.EventList;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;

public class Inform extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private DeviceIdStruct deviceId;
    private EventList event;
    private int maxEnvelopes;
    private Date currentTime;//2016-07-21T14:49:03+08:00
    private int retryCount;
    private ParameterValueList parameterList;
    
    public Inform( Node node ) {super(node);}
    public Inform() {}
    public Inform( DeviceIdStruct deviceid, EventList event, int maxenvelopes, Date currenttime, int retrycount, ParameterValueList parameterlist )
    {
        this.deviceId = deviceid;
        this.event = event;
        this.maxEnvelopes = maxenvelopes;
        this.currentTime = currenttime;
        this.retryCount = retrycount;
        this.parameterList = parameterlist;
    }
    
	public DeviceIdStruct getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(DeviceIdStruct deviceId)
	{
		this.deviceId = deviceId;
	}

	public EventList getEvent()
	{
		return event;
	}

	public void setEvent(EventList event)
	{
		this.event = event;
	}

	public int getMaxEnvelopes()
	{
		return maxEnvelopes;
	}

	public void setMaxEnvelopes(int maxEnvelopes)
	{
		this.maxEnvelopes = maxEnvelopes;
	}

	public Date getCurrentTime()
	{
		return currentTime;
	}

	public void setCurrentTime(Date currentTime)
	{
		this.currentTime = currentTime;
	}

	public int getRetryCount()
	{
		return retryCount;
	}

	public void setRetryCount(int retryCount)
	{
		this.retryCount = retryCount;
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
		SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:Inform>");
        sb.append("\n");
        sb.append("<DeviceId>" + this.deviceId.toXml() + "</DeviceId>");
        sb.append("\n");
        sb.append("<Event SOAP-ENC:arrayType=\"cwmp:EventStruct[" + this.event.getItems().size() + "]\">" + this.event.toXml() + "</Event>");
        sb.append("\n");
        sb.append("<MaxEnvelopes>" + this.maxEnvelopes + "</MaxEnvelopes>");
        sb.append("\n");
        sb.append("<CurrentTime>" + dateF.format(this.currentTime) + "</CurrentTime>");
        sb.append("\n");
        sb.append("<RetryCount>" + this.retryCount + "</RetryCount>");
        sb.append("\n");
        sb.append("<ParameterList SOAP-ENC:arrayType=\"cwmp:ParameterValueStruct[" + this.parameterList.getItems().size() + "]\">" + this.parameterList.toXml() + "</ParameterList>");
        sb.append("\n");
        sb.append("</cwmp:Inform>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		Node tempDeviceId = this.getNode(node, "DeviceId");
        if ((tempDeviceId == null))
        {
        }
        else
        {
            this.deviceId = new DeviceIdStruct(tempDeviceId);
        }
        Node tempEvent = this.getNode(node, "Event");
        if ((tempEvent == null))
        {
        }
        else
        {
            this.event = new EventList(tempEvent);
        }
        Node tempMaxEnvelopes = this.getNode(node, "MaxEnvelopes");
        if ((tempMaxEnvelopes == null))
        {
        }
        else
        {
            this.maxEnvelopes = Integer.parseInt(this.getCurrentNodeValue(tempMaxEnvelopes));
        }
        Node tempCurrentTime = this.getNode(node, "CurrentTime");
        if ((tempCurrentTime == null))
        {
        }
        else
        {
            try
			{
				this.currentTime = dateF.parse(this.getCurrentNodeValue(tempCurrentTime));
			} catch (DOMException | ParseException e)
			{
				e.printStackTrace();
				this.currentTime = null;
			}
        }
        Node tempRetryCount = this.getNode(node, "RetryCount");
        if ((tempRetryCount == null))
        {
        }
        else
        {
            this.retryCount = Integer.parseInt(this.getCurrentNodeValue(tempRetryCount));
        }
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
		Inform ret = new Inform();
        ret.deviceId = ((DeviceIdStruct)(this.deviceId.clone()));
        ret.event = ((EventList)(this.event.clone()));
        ret.maxEnvelopes = this.maxEnvelopes;
        ret.currentTime = this.currentTime;
        ret.retryCount = this.retryCount;
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
Content-Length: 7992

<SOAP-ENV:Envelope
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:cwmp="urn:dslforum-org:cwmp-1-2">
    <SOAP-ENV:Header>
      <cwmp:ID SOAP-ENV:mustUnderstand="1">1461805464</cwmp:ID>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
      <cwmp:Inform>
        <DeviceId>
          <Manufacturer>XXX</Manufacturer>
          <OUI>BC0304</OUI>
          <ProductClass>First class</ProductClass>
          <SerialNumber>1923043</SerialNumber>
        </DeviceId>
        <Event SOAP-ENC:arrayType="cwmp:EventStruct[2]">
          <EventStruct>
            <EventCode>X BC0304 Alarm</EventCode>
            <CommandKey></CommandKey>
          </EventStruct>
          <EventStruct>
            <EventCode>4 VALUE CHANGE</EventCode>
            <CommandKey></CommandKey>
          </EventStruct>
        </Event>
        <MaxEnvelopes>1</MaxEnvelopes>
        <CurrentTime>2016-07-21T14:49:03+08:00</CurrentTime>
        <RetryCount>1</RetryCount>
        <ParameterList SOAP-ENC:arrayType="cwmp:ParameterValueStruct[000032]">
            <ParameterValueStruct>
              <Name>Device.RootDataModelVersion</Name>
              <Value xsi:type="xsd:string">2.7</Value>
            </ParameterValueStruct>
            <ParameterValueStruct>
              <Name>Device.DeviceInfo.HardwareVersion</Name>
              <Value xsi:type="xsd:string">dalian</Value>
            </ParameterValueStruct>
            <ParameterValueStruct>
              <Name>Device.DeviceInfo.SoftwareVersion</Name>
              <Value xsi:type="xsd:string">643-19953-19916-20025-20027-485-597</Value>
            </ParameterValueStruct>
        </ParameterList>
      </cwmp:Inform>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope> 
*/