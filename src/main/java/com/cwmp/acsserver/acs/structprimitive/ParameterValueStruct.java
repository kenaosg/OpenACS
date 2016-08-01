package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class ParameterValueStruct extends CwmpObjBase 
{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String value;
	private String valueType;
	
	public ParameterValueStruct(Node node) {super(node);}
	public ParameterValueStruct() {}
	public ParameterValueStruct(String name, String value, String valueType) 
	{
		this.name = name;
		this.value = value;
		this.valueType = valueType;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getValue() 
	{
		return value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}
	public String getValueType() 
	{
		return valueType;
	}
	public void setValueType(String valueType) 
	{
		this.valueType = valueType;
	}
	
	@Override
	public String toXml()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<Name>" + this.name + "</Name>");
        sb.append("<Value xsi:type=\"xsd:" + this.valueType + "\">" + this.value + "</Value>");
        return sb.toString();
    }

	@Override
    public void parseNode( Node node )
    {
        Node tempName = this.getNode(node, "Name");
        if ((tempName == null))
        {
        }
        else
        {
            this.name = this.getCurrentNodeValue(tempName);
        }
        Node tempValue = this.getNode(node, "Value");
        if ((tempValue == null))
        {
        }
        else
        {
            this.value = this.getCurrentNodeValue(tempValue);
            this.valueType = this.getCurrentNodeAttrValue(tempValue, "xsi:type").replaceAll("^[xsd]+:", "");
        }
    }

	@Override
    public Object clone()
    {
//        ParameterValueStruct ret = new ParameterValueStruct();
//        ret.name = this.name;
//        ret.value = this.value;
//        return ret;
        
        return this.clone();
    }
	
	
}
