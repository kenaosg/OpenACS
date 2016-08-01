package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//************************************************************
import java.io.Serializable;

public abstract class CwmpObjBase implements Cloneable, Serializable 
{
	private static final long serialVersionUID = 1L;	
	protected EnvelopeBodyTypeEnum envelopeContentTypeEnum;
	protected boolean isSimpleType = false;
	
	public CwmpObjBase ( Node node )
    {
        this.parseNode(node);
    }
    public CwmpObjBase() { }
	
	public EnvelopeBodyTypeEnum getEnvelopeContentTypeEnum() 
	{
		return envelopeContentTypeEnum;
	}
	public void setEnvelopeContentTypeEnum(EnvelopeBodyTypeEnum envelopeContentTypeEnum) 
	{
		this.envelopeContentTypeEnum = envelopeContentTypeEnum;
	}
	public boolean isSimpleType() 
	{
		return isSimpleType;
	}
	
	public abstract String toXml();
	public abstract void parseNode(Node node);
	public abstract Object clone();
	
    public Node getNode( Node node, String nodeName )
    {
        NodeList nl = node.getChildNodes();
		Node item = null;
        for (int i = 0; i < nl.getLength(); i++)
        {
        	if((item = nl.item(i)) != null)
        	{
	            if (nodeName.equals(item.getLocalName()))
	            {
	                return item;
	            }
        	}
        }
        return null;
    }
    
	public String getChildNodeValue( Node node, String childNodeName )
    {
		NodeList nl = node.getChildNodes();
		Node item = null;
        for (int i = 0; i < nl.getLength(); i++)
        {
        	item = nl.item(i);
            if (childNodeName.equals(item.getLocalName()))
            {
                return this.getCurrentNodeValue(item);
            }
        }
        return null;
    }
	
	public String getCurrentNodeValue(Node node)
	{
		NodeList nl = node.getChildNodes();
		Node item = null;
		for (int i = 0; i < nl.getLength(); i++)
        {
			item = nl.item(i);
        	if("#text".equals(item.getNodeName()))
        	{
        		return item.getNodeValue();
        	}
        }
        return null;
	}
	
    /**
     * @param node
     * @param name: name of childnode from which you want to fetch attribute
     * @param attrName
     * @return
     */
    public String getChildNodeAttrValue( Node node, String childNodeName, String attrName )
    {
        NodeList nl = node.getChildNodes();
		Node item = null;
        for (int i = 0; i < nl.getLength(); i++)
        {
        	item = nl.item(i);
            if (childNodeName.equals(item.getLocalName()))
            {
                //System.out.println(item.getAttributes().getNamedItem("xsi:" + attrName).getNodeValue());
                return item.getAttributes().getNamedItem("xsi:" + attrName).getNodeValue();
            }
        }
        return null;
    }
    
    public String getCurrentNodeAttrValue(Node node, String attrName)
    {
    	return node.getAttributes().getNamedItem(attrName).getNodeValue();
    }
    
}
