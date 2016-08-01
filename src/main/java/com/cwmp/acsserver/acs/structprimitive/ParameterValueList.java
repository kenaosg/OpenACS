package com.cwmp.acsserver.acs.structprimitive;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParameterValueList extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ParameterValueStruct> items;
	
	public ParameterValueList( Node node ) {super(node);}
    public ParameterValueList() {this.items = new ArrayList<ParameterValueStruct>();}
    public ParameterValueList( Iterable<ParameterValueStruct> items )
    {
    	this.items = new ArrayList<ParameterValueStruct>();
        this.items.clear();
        for (Iterator<ParameterValueStruct> iter = items.iterator(); iter.hasNext(); )
        {
            this.items.add(iter.next());
        }
    }

	public ArrayList<ParameterValueStruct> getItems()
	{
		return items;
	}
	public void setItems(ArrayList<ParameterValueStruct> items)
	{
		this.items = items;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        for (int i = 0; (i < this.items.size()); i++)
        {
            sb.append("<ParameterValueStruct>");
            sb.append(this.items.get(i).toXml());
            sb.append("</ParameterValueStruct>");
        }
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		if(this.items == null){this.items = new ArrayList<ParameterValueStruct>();}
		NodeList nl = node.getChildNodes();
		Node item;
		for(int i = 0; i < nl.getLength(); i++)
		{
			item = nl.item(i);
			if("ParameterValueStruct".equals(item.getLocalName()))
			{
				ParameterValueStruct temp = new ParameterValueStruct(node.getChildNodes().item(i));
				this.items.add(temp);
			}
		}
	}

	@Override
	public Object clone()
	{
		ParameterValueList ret = new ParameterValueList();
		for (Iterator<ParameterValueStruct> iter = this.items.iterator(); iter.hasNext(); )
        {
			ret.items.add((ParameterValueStruct)iter.next().clone());
        }
		return ret;
	}	
}