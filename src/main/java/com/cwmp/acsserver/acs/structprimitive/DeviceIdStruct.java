package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class DeviceIdStruct extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String manufacturer;
    private String oui;
    private String productClass;
    private String serialNumber;

    public DeviceIdStruct( Node node ) {super(node);}
    public DeviceIdStruct() {}
    public DeviceIdStruct( String manufacturer, String oui, String productclass, String serialnumber )
    {
        this.manufacturer = manufacturer;
        this.oui = oui;
        this.productClass = productclass;
        this.serialNumber = serialnumber;
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
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<Manufacturer>" + this.manufacturer.toString() + "</Manufacturer>");
        sb.append("<OUI>" + this.oui.toString() + "</OUI>");
        sb.append("<ProductClass>" + this.productClass.toString() + "</ProductClass>");
        sb.append("<SerialNumber>" + this.serialNumber.toString() + "</SerialNumber>");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempManufacturer = this.getNode(node, "Manufacturer");
        if ((tempManufacturer == null))
        {
        }
        else
        {
            this.manufacturer = this.getCurrentNodeValue(tempManufacturer);
        }
        Node tempOUI = this.getNode(node, "OUI");
        if ((tempOUI == null))
        {
        }
        else
        {
            this.oui = this.getCurrentNodeValue(tempOUI);
        }
        Node tempProductClass = this.getNode(node, "ProductClass");
        if ((tempProductClass == null))
        {
        }
        else
        {
            this.productClass = this.getCurrentNodeValue(tempProductClass);
        }
        Node tempSerialNumber = this.getNode(node, "SerialNumber");
        if ((tempSerialNumber == null))
        {
        }
        else
        {
            this.serialNumber = this.getCurrentNodeValue(tempSerialNumber);
        }
	}

	@Override
	public Object clone()
	{
//		DeviceIdStruct ret = new DeviceIdStruct();
//        ret.manufacturer = this.manufacturer;
//        ret.oui = this.oui;
//        ret.productClass = this.productClass;
//        ret.serialNumber = this.serialNumber;
//        return ret;
        
		return this.clone();
	}

}
