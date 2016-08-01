package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class ParameterInfoStruct extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String name;
	private boolean writable;
	
	public ParameterInfoStruct( Node node ) {super(node);}
    public ParameterInfoStruct() {}
    public ParameterInfoStruct( String name, boolean writable )
    {
        this.name = name;
        this.writable = writable;
    }

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean isWritable()
	{
		return writable;
	}
	public void setWritable(boolean writable)
	{
		this.writable = writable;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<Name>" + this.name.toString() + "</Name>");
        sb.append("<Writable>" + (this.writable ? "1" : "0") + "</Writable>");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempName = this.getNode(node, "Name");
        if ((tempName == null))
        {
        }
        else
        {
            this.name =this.getCurrentNodeValue(tempName);
        }
        Node tempWritable = this.getNode(node, "Writable");
        if ((tempWritable == null))
        {
        }
        else
        {
            this.writable = this.getCurrentNodeValue(tempWritable).equals("1") ? true : false;
        }
	}

	@Override
	public Object clone()
	{
//		ParameterInfoStruct ret = new ParameterInfoStruct();
//		ret.name = this.name;
//		ret.writable = this.writable;
//        return ret;
		
		return this.clone();
	}
	
}
