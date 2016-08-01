package com.cwmp.acsserver.acs.soap;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class SoapHeadID extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String value;

    public SoapHeadID( Node node ) { super(node); }
    public SoapHeadID( String value )
    {
        this.value = value;
    }

    public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toXml()
	{
		return this.value;
	}
	@Override
	public void parseNode(Node node) 
	{
		this.value = this.getCurrentNodeValue(node);
	}
	@Override
	public Object clone()
	{
		SoapHeadID ret = new SoapHeadID(this.value);
        return ret;
	}
}

