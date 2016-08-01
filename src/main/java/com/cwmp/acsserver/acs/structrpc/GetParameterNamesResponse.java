package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.ParameterInfoList;

public class GetParameterNamesResponse extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private ParameterInfoList parameterList;
	
	public GetParameterNamesResponse( Node node ) { super(node); }
    public GetParameterNamesResponse() {}
    public GetParameterNamesResponse( ParameterInfoList parameterlist )
    {
        this.parameterList = parameterlist;
    }
	
	public ParameterInfoList getParameterList()
	{
		return parameterList;
	}

	public void setParameterList(ParameterInfoList parameterList)
	{
		this.parameterList = parameterList;
	}

	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:GetParameterNamesResponse>");
        sb.append("\n");
        sb.append("<ParameterList SOAP-ENC:arrayType=\"cwmp:ParameterInfoStruct[" + this.parameterList.getItems().size() + "]\">" + this.parameterList.toXml() + "</ParameterList>");
        sb.append("\n");
        sb.append("</cwmp:GetParameterNamesResponse>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempParameterList = this.getNode(node, "ParameterList");
        if ((tempParameterList == null))
        {
        }
        else
        {
            this.parameterList = new ParameterInfoList(tempParameterList);
        }
	}

	@Override
	public Object clone()
	{
		GetParameterNamesResponse ret = new GetParameterNamesResponse();
		ret.parameterList = ((ParameterInfoList)(this.parameterList.clone()));
        return ret;
	}
	
}
