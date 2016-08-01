package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class SetParameterValuesFaultType extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String parameterName;
    private int faultCode;
    private String faultString;

    public SetParameterValuesFaultType( Node node ) {super(node);}
    public SetParameterValuesFaultType() {}
    public SetParameterValuesFaultType( String parametername, int faultcode, String faultstring )
    {
        this.parameterName = parametername;
        this.faultCode = faultcode;
        this.faultString = faultstring;
    }
    
	public String getParameterName()
	{
		return parameterName;
	}
	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}
	public int getFaultCode()
	{
		return faultCode;
	}
	public void setFaultCode(int faultCode)
	{
		this.faultCode = faultCode;
	}
	public String getFaultString()
	{
		return faultString;
	}
	public void setFaultString(String faultString)
	{
		this.faultString = faultString;
	}
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<ParameterName>" + this.parameterName.toString() + "</ParameterName>");
        sb.append("<FaultCode>" + this.faultCode + "</FaultCode>");
        sb.append("<FaultString>" + this.faultString.toString() + "</FaultString>");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempParameterName = this.getNode(node, "ParameterName");
        if ((tempParameterName == null))
        {
        }
        else
        {
            this.parameterName = this.getCurrentNodeValue(tempParameterName);
        }
        Node tempFaultCode = this.getNode(node, "FaultCode");
        if ((tempFaultCode == null))
        {
        }
        else
        {
            this.faultCode = Integer.parseInt(this.getCurrentNodeValue(tempFaultCode));
        }
        Node tempFaultString = this.getNode(node, "FaultString");
        if ((tempFaultString == null))
        {
        }
        else
        {
            this.faultString = this.getCurrentNodeValue(tempFaultString);
        }
	}

	@Override
	public Object clone()
	{
//		SetParameterValuesFaultType ret = new SetParameterValuesFaultType();
//        ret.parameterName = this.parameterName;
//        ret.faultCode = this.faultCode;
//        ret.faultString = this.faultString;
//        return ret;
		
		return this.clone();
	}

}
