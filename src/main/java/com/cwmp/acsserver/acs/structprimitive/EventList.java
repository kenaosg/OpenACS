package com.cwmp.acsserver.acs.structprimitive;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EventList extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ArrayList<EventStruct> items;
	
	public EventList( Node node ) {super(node);}
    public EventList() {this.items = new ArrayList<EventStruct>();}
    public EventList( Iterable<EventStruct> items )
    {
    	this();
        this.items.clear();
        for (Iterator<EventStruct> iter = items.iterator(); iter.hasNext(); )
        {
            this.items.add(iter.next());
        }
    }
	
	public ArrayList<EventStruct> getItems()
	{
		return items;
	}

	public void setItems(ArrayList<EventStruct> items)
	{
		this.items = items;
	}

	@Override
	public String toXml()
	{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; (i < this.items.size()); i++)
        {
            sb.append("<EventStruct>");
            sb.append(this.items.get(i).toXml());
            sb.append("</EventStruct>");
        }
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		if(this.items == null) {this.items = new ArrayList<EventStruct>();}
		NodeList nl = node.getChildNodes();
		Node item;
		for (int i = 0; i < nl.getLength(); i++)
        {
			item = nl.item(i);
			if("EventStruct".equals(item.getLocalName()))
			{
	            EventStruct temp = new EventStruct(item);
	            this.items.add(temp);
			}
        }
	}

	@Override
	public Object clone()
	{
		EventList ret = new EventList();
        for (int i = 0; i < this.items.size(); i++)
        {
            ret.items.add((EventStruct)this.items.get(i).clone());
        }
        return ret;
	}

}
