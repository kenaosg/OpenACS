package com.cwmp.acsserver.acs.structprimitive;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParameterNames extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> items;
	
	public ParameterNames(Node node) {super(node);}
	public ParameterNames() { this.items = new ArrayList<String>();}
	public ParameterNames(Iterable<String> items)
	{
		this();
		this.items.clear();
		for(Iterator<String> iter = items.iterator(); iter.hasNext(); )
		{
			this.items.add(iter.next());
		}
	}

	public ArrayList<String> getItems() 
	{
		return items;
	}
	public void setItems(ArrayList<String> items) 
	{
		this.items = items;
	}
	@Override
	public String toXml() 
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.items.size(); i++)
		{
			sb.append("<string>");
			sb.append(this.items.get(i));
			sb.append("</string>");
		}
		return sb.toString();
		
//		for(Iterator<String> iter = this.items.iterator(); iter.hasNext(); )
//		{
//			sb.append("<string>");
//			sb.append("\n");
//			sb.append(iter.next());
//			sb.append("\n");
//			sb.append("</string>");
//			sb.append("\n");
//		}
	}

	@Override
	public void parseNode(Node node) 
	{
		if(this.items == null) {this.items = new ArrayList<String>();}
		NodeList nl = node.getChildNodes();
		Node item;
		for(int i = 0; i < nl.getLength(); i++)
		{
			item = nl.item(i);
			if("string".equals(item.getLocalName()))
			{
				this.items.add(this.getCurrentNodeValue(item));
			}
		}
	}

	@Override
	public Object clone() 
	{
		ParameterNames ret =  (ParameterNames)this.clone();
		ret.setItems((ArrayList<String>)this.items.clone());
		
		return ret;
	}
	
}
