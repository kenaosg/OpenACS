package com.cwmp.acsserver.acs.structprimitive;

import org.w3c.dom.Node;

public class EventStruct extends CwmpObjBase
{
	private static final long serialVersionUID = 1L;
	private EventCodeType eventCode;
    private CommandKeyType commandKey;

    public EventStruct( Node node ) {super(node);}
    public EventStruct() {}
    public EventStruct( EventCodeType eventcode, CommandKeyType commandkey )
    {
        this.eventCode = eventcode;
        this.commandKey = commandkey;
    }
    
	public EventCodeType getEventCode()
	{
		return eventCode;
	}

	public void setEventCode(EventCodeType eventCode)
	{
		this.eventCode = eventCode;
	}

	public CommandKeyType getCommandKey()
	{
		return commandKey;
	}

	public void setCommandKey(CommandKeyType commandKey)
	{
		this.commandKey = commandKey;
	}
	
	@Override
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
        sb.append("<EventCode>" + this.eventCode.toXml() + "</EventCode>");
        sb.append("<CommandKey>" + this.commandKey.toXml() + "</CommandKey>");
        return sb.toString();
	}

	@Override
	public void parseNode(Node node)
	{
		Node tempEventCode = this.getNode(node, "EventCode");
        if ((tempEventCode == null))
        {
        }
        else
        {
            this.eventCode = new EventCodeType(tempEventCode);
        }
        Node tempCommandKey = this.getNode(node, "CommandKey");
        if ((tempCommandKey == null))
        {
        }
        else
        {
            this.commandKey = new CommandKeyType(tempCommandKey);
        }		
	}

	@Override
	public Object clone()
	{
		EventStruct ret = new EventStruct();
		ret.eventCode = ((EventCodeType)(this.eventCode.clone()));
		ret.commandKey = ((CommandKeyType)(this.commandKey.clone()));
        return ret;
	}

}
