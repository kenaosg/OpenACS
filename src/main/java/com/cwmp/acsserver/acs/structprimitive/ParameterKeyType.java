package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class ParameterKeyType extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String value;
	
	public ParameterKeyType( Node node )
    {
		super(node);
        this.isSimpleType = true;
    }
    public ParameterKeyType()
    {
        this.isSimpleType = true;
    }
    public ParameterKeyType( String value )
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
//		ParameterKeyType ret = new ParameterKeyType(this.value);
//      return ret;
		
		return this.clone();
	}
	
	@Override
	public String toString()
	{
		return this.value;
	}
	
	
}
