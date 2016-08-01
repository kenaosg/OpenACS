package com.cwmp.acsserver.acs.soap;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class SoapHeadHoldRequests extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private int value;

    public SoapHeadHoldRequests( Node node ) { super(node); }
    public SoapHeadHoldRequests( int value )
    {
        this.value = value;
    }
    
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	@Override
	public String toXml()
	{
		return this.value + "";
	}
	@Override
	public void parseNode(Node node) 
	{
		this.value = Integer.parseInt(this.getCurrentNodeValue(node));
	}
	@Override
	public Object clone()
	{
		return new SoapHeadHoldRequests(this.value);
	}
    
}
