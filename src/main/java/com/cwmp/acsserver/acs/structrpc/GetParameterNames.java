package com.cwmp.acsserver.acs.structrpc;

import org.w3c.dom.Node;

import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;

public class GetParameterNames extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private String parameterPath;
    private boolean nextLevel;

    public GetParameterNames( Node node ) { super(node); }
    public GetParameterNames() {}
    public GetParameterNames( String parameterpath, Boolean nextlevel )
    {
        this.parameterPath = parameterpath;
        this.nextLevel = nextlevel;
    }
    
	public String getParameterPath()
	{
		return parameterPath;
	}
	public void setParameterPath(String parameterPath)
	{
		this.parameterPath = parameterPath;
	}
	public boolean isNextLevel()
	{
		return nextLevel;
	}
	public void setNextLevel(boolean nextLevel)
	{
		this.nextLevel = nextLevel;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<cwmp:GetParameterNames>");
        sb.append("\n");
        sb.append("<ParameterPath>" + this.parameterPath.toString() + "</ParameterPath>");
        sb.append("\n");
        sb.append("<NextLevel>" + (this.nextLevel ? "true" : "false") + "</NextLevel>");
        sb.append("\n");
        sb.append("</cwmp:GetParameterNames>");
        sb.append("\n");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempParameterPath = this.getNode(node, "ParameterPath");
        if ((tempParameterPath == null))
        {
        }
        else
        {
            this.parameterPath = this.getCurrentNodeValue(tempParameterPath);
        }
        Node tempNextLevel = this.getNode(node, "NextLevel");
        if ((tempNextLevel == null))
        {
        }
        else
        {
            this.nextLevel = this.getCurrentNodeValue(tempNextLevel).equals("1") ? true : false;
        }
	}

	@Override
	public Object clone()
	{
//		GetParameterNames ret = new GetParameterNames();
//		ret.parameterPath = this.parameterPath;
//		ret.nextLevel = this.nextLevel;
//        return ret;
		
		return this.clone();
	}
	
}
