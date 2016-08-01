package com.cwmp.acsserver.acs;

import javax.servlet.ServletRequest;

import com.cwmp.acsserver.acs.soap.SoapEnvelope;
import com.cwmp.acsserver.acs.structrpc.Inform;

public class CpeEntity
{
	private CpeClient cpeClient;
	private String serialNumber;
	private String ipAddress;
	
	public CpeEntity() {}
	public CpeEntity(SoapEnvelope se, ServletRequest req)
	{
		this.cpeClient = new CpeClient(new CpeInformation((Inform) se.getBody().getMsg()), req.getRemoteAddr(), 30005);
		this.cpeClient.getCpeInformation().setIpAddr(req.getRemoteAddr());
		this.cpeClient.getCpeInformation().setCwmpVersion(se.getVersion());//need update when inform received
		this.serialNumber = this.cpeClient.getCpeInformation().getSerialNumber();
		this.ipAddress = req.getRemoteAddr();
	}
	
	public void updateCpeInformationFirstTime(SoapEnvelope se)
	{
		this.cpeClient.getComm().setConnected(true);
	}	
	public void updateCpeInformation(SoapEnvelope se)
	{
		this.cpeClient.getCpeInformation().setCwmpVersion(se.getVersion());
		this.cpeClient.getComm().setConnected(true);
	}
	
	public CpeClient getCpeClient()
	{
		return cpeClient;
	}
	public void setCpeClient(CpeClient cpeClient)
	{
		this.cpeClient = cpeClient;
	}
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
}
