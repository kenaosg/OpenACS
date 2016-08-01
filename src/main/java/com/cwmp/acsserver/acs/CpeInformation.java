package com.cwmp.acsserver.acs;

import org.apache.log4j.Logger;

import com.cwmp.acsserver.acs.soap.CwmpVersionEnum;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueStruct;
import com.cwmp.acsserver.acs.structrpc.Inform;

public class CpeInformation
{
	private String manufacturer;
    private String oui;
    private String productClass;
    private String serialNumber;
    private int retryCount;
    private String deviceSummary;
    private String specVersion;
    private String hardwareVersion;
    private String softwareVersion;
    private String provisionCode;
    
    private CwmpVersionEnum cwmpVersion;
    private String ipAddr;
    
    public CpeInformation() {}
    
    public CpeInformation(Inform inform)
    {
    	this.manufacturer = inform.getDeviceId().getManufacturer();
    	this.oui = inform.getDeviceId().getOui();
    	this.productClass = inform.getDeviceId().getProductClass();
    	this.serialNumber = inform.getDeviceId().getSerialNumber();
    	this.retryCount = inform.getRetryCount();
    	for(ParameterValueStruct pvs : inform.getParameterList().getItems())
        {
    		if(pvs.getName().contains("Device.DeviceSummary")) {this.deviceSummary = pvs.getValue(); continue;}
    		if(pvs.getName().contains("Device.DeviceInfo.SpecVersion")) {this.specVersion = pvs.getValue(); continue;}
    		if(pvs.getName().contains("Device.DeviceInfo.HardwareVersion")) {this.hardwareVersion = pvs.getValue(); continue;}
    		if(pvs.getName().contains("Device.DeviceInfo.SoftwareVersion")) {this.softwareVersion = pvs.getValue(); continue;}
    		if(pvs.getName().contains("Device.DeviceInfo.ProvisioningCode")) {this.provisionCode = pvs.getValue(); continue;}
//            switch(pvs.getName())
//            {
//                case "Device.DeviceSummary":
//                    this.deviceSummary = pvs.getValue();
//                    break;
//                case "Device.DeviceInfo.SpecVersion":
//                    this.specVersion = pvs.getValue();
//                    break;
//                case "Device.DeviceInfo.HardwareVersion":
//                    this.hardwareVersion = pvs.getValue();
//                    break;
//                case "Device.DeviceInfo.SoftwareVersion":
//                    this.softwareVersion = pvs.getValue();
//                    break;
//                case "Device.DeviceInfo.ProvisioningCode":
//                    this.provisionCode = pvs.getValue();
//                    break;
//            }
        }
    }
    
	public String getManufacturer()
	{
		return manufacturer;
	}
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	public String getOui()
	{
		return oui;
	}
	public void setOui(String oui)
	{
		this.oui = oui;
	}
	public String getProductClass()
	{
		return productClass;
	}
	public void setProductClass(String productClass)
	{
		this.productClass = productClass;
	}
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public int getRetryCount()
	{
		return retryCount;
	}
	public void setRetryCount(int retryCount)
	{
		this.retryCount = retryCount;
	}
	public String getDeviceSummary()
	{
		return deviceSummary;
	}
	public void setDeviceSummary(String deviceSummary)
	{
		this.deviceSummary = deviceSummary;
	}
	public String getSpecVersion()
	{
		return specVersion;
	}
	public void setSpecVersion(String specVersion)
	{
		this.specVersion = specVersion;
	}
	public String getHardwareVersion()
	{
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion)
	{
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion()
	{
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion)
	{
		this.softwareVersion = softwareVersion;
	}
	public String getProvisionCode()
	{
		return provisionCode;
	}
	public void setProvisionCode(String provisionCode)
	{
		this.provisionCode = provisionCode;
	}
	public CwmpVersionEnum getCwmpVersion()
	{
		return cwmpVersion;
	}

	public void setCwmpVersion(CwmpVersionEnum cwmpVersionEnum)
	{
		this.cwmpVersion = cwmpVersionEnum;
	}

	public String getIpAddr()
	{
		return ipAddr;
	}

	public void setIpAddr(String ipAddr)
	{
		this.ipAddr = ipAddr;
	}

	@Override
	public String toString()
	{
		return "CpeInformation [manufacturer=" + manufacturer + ", oui=" + oui
				+ ", productClass=" + productClass + ", serialNumber="
				+ serialNumber + ", retryCount=" + retryCount
				+ ", deviceSummary=" + deviceSummary + ", specVersion="
				+ specVersion + ", hardwareVersion=" + hardwareVersion
				+ ", softwareVersion=" + softwareVersion + ", provisionCode="
				+ provisionCode + ", cwmpVersion=" + cwmpVersion + ", ipAddr="
				+ ipAddr + "]";
	}
}
