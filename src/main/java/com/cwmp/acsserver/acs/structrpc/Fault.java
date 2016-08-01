package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.SetParameterValuesFaultType;

public class Fault extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private int faultCode;
    private String faultString;
    private SetParameterValuesFaultType setParameterValuesFault;

    public Fault( Node node ) {super(node);}
    public Fault() {}
    public Fault( int faultcode, String faultstring, SetParameterValuesFaultType setparametervaluesfault )
    {
        this.faultCode = faultcode;
        this.faultString = faultstring;
        this.setParameterValuesFault = setparametervaluesfault;
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

	public SetParameterValuesFaultType getSetParameterValuesFault()
	{
		return setParameterValuesFault;
	}

	public void setSetParameterValuesFault(
			SetParameterValuesFaultType setParameterValuesFault)
	{
		this.setParameterValuesFault = setParameterValuesFault;
	}

	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:Fault>");
        sb.append("\n");
        sb.append("<FaultCode>" + this.faultCode + "</FaultCode>");
        sb.append("\n");
        sb.append("<FaultString>" + this.faultString.toString() + "</FaultString>");
        sb.append("\n");
        sb.append("<SetParameterValuesFault>" + this.setParameterValuesFault.toXml() + "</SetParameterValuesFault>");
        sb.append("\n");
        sb.append("</cwmp:Fault>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
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
        Node tempSetParameterValuesFault = this.getNode(node, "SetParameterValuesFault");
        if ((tempSetParameterValuesFault == null))
        {
        }
        else
        {
            this.setParameterValuesFault = new SetParameterValuesFaultType(tempSetParameterValuesFault);
        }
	}

	@Override
	public Object clone()
	{
		Fault ret = new Fault();
        ret.faultCode = this.faultCode;
        ret.faultString = this.faultString;
        ret.setParameterValuesFault = ((SetParameterValuesFaultType)(this.setParameterValuesFault.clone()));
        return ret;
	}
    
    
}
