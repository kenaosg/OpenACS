package com.cwmp.acsserver.acs.structprimitive;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParameterInfoList extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ParameterInfoStruct> items;
	
	public ParameterInfoList( Node node ) {super(node);}
    public ParameterInfoList() {this.items = new ArrayList<ParameterInfoStruct>();}
    public ParameterInfoList( Iterable<ParameterInfoStruct> items )
    {
    	this.items = new ArrayList<ParameterInfoStruct>();
        this.items.clear();
        for (Iterator<ParameterInfoStruct> iter = items.iterator(); iter.hasNext(); )
        {
            this.items.add(iter.next());
        }
    }

	public ArrayList<ParameterInfoStruct> getItems()
	{
		return items;
	}

	public void setItems(ArrayList<ParameterInfoStruct> items)
	{
		this.items = items;
	}

	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++)
        {
            sb.append("<ParameterInfoStruct>");
            sb.append(this.items.get(i).toXml());
            sb.append("</ParameterInfoStruct>");
        }
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		if(this.items == null) {this.items = new ArrayList<ParameterInfoStruct>();}
		NodeList nl = node.getChildNodes();
		Node item;
		for (int i = 0; i < nl.getLength(); i++)
        {
			item = nl.item(i);
			if("ParameterInfoStruct".equals(item.getLocalName()))
			{
	            ParameterInfoStruct temp = new ParameterInfoStruct(node.getChildNodes().item(i));
	            this.items.add(temp);
			}
        }
	}

	@Override
	public Object clone()
	{
		ParameterInfoList ret = new ParameterInfoList();
	    for (int i = 0; i < this.items.size(); i++)
	    {
	    	ret.items.add((ParameterInfoStruct)(this.items.get(i).clone()));
	    }
	    return ret;
	}
	
}
