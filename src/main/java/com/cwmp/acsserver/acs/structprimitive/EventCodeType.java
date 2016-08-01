package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class EventCodeType extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String value;

    public EventCodeType( Node node )
    {
    	super(node);
        this.isSimpleType = true;
    }
    public EventCodeType()
    {
        this.isSimpleType = true;
    }
    public EventCodeType( String value )
    {
        this.isSimpleType = true;
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
//		EventCodeType ret = new EventCodeType(this.value);
//        return ret;
        
        return this.clone();
	}

	@Override
	public String toString()
	{
		return this.value;
	}
	

}
