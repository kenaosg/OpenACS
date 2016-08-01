package com.cwmp.acsserver.acs;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;

import com.cwmp.acsserver.acs.soap.SoapBody;
import com.cwmp.acsserver.acs.soap.SoapEnvelope;
import com.cwmp.acsserver.acs.soap.SoapHeadHoldRequests;
import com.cwmp.acsserver.acs.soap.SoapHeader;
import com.cwmp.acsserver.acs.structprimitive.CwmpObjBase;
import com.cwmp.acsserver.acs.structprimitive.EnvelopeBodyTypeEnum;
import com.cwmp.acsserver.acs.structprimitive.ParameterKeyType;
import com.cwmp.acsserver.acs.structprimitive.ParameterNames;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;
import com.cwmp.acsserver.acs.structrpc.GetParameterNames;
import com.cwmp.acsserver.acs.structrpc.GetParameterNamesResponse;
import com.cwmp.acsserver.acs.structrpc.GetParameterValues;
import com.cwmp.acsserver.acs.structrpc.GetParameterValuesResponse;
import com.cwmp.acsserver.acs.structrpc.InformResponse;
import com.cwmp.acsserver.acs.structrpc.SetParameterValues;
import com.cwmp.acsserver.acs.structrpc.SetParameterValuesResponse;

public class CpeClient implements ICpe
{
	private String username;
    private String password;
    private String ipAddress;
    private int port;
    private String alias = "";
    private String connectionUrl = "";
    private CpeInformation cpeInformation;
    private Tr69Communication comm = null;
    /**
     * initialized in constructor to 10
     * can be re-initialized by setter
     */
    ArrayBlockingQueue<AcsConfigurer> arrayBlockingQueueForRpcTasks;

    public CpeClient() {}
    public CpeClient(CpeInformation cpeInformation)
    {
    	this.cpeInformation = cpeInformation;
        this.comm = new Tr69Communication(this);
        this.arrayBlockingQueueForRpcTasks = new ArrayBlockingQueue<AcsConfigurer>(10);//10 RPC buffer allowed, can be re-initialized by setter
    }
    public CpeClient( CpeInformation cpeInformation, String ip, int port )
    {
    	this(cpeInformation);
        this.ipAddress = ip;
        this.port = port;
		this.connectionUrl = this.getUrl();
    }
    public CpeClient( CpeInformation cpeInformation, String ip, int port, String username, String password )
    {
    	this(cpeInformation, ip, port);
        this.username = username;
        this.password = password;
    }
    
	public String getUsername(){ return username; }
	public void setUsername(String username){ this.username = username; }
	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password = password; }
	public String getIpAddress(){ return ipAddress; }
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public int getPort() { return port; }
	public void setPort(int port) { this.port = port; }
	public String getAlias()
	{
		if (this.alias == "")
        {
            return this.ipAddress.toString();
        }
		return alias;
	}
	public void setAlias(String alias) { this.alias = alias; }
	public CpeInformation getCpeInformation() { return cpeInformation; }
	public void setCpeInformation(CpeInformation cpeInformation)
	{
		this.cpeInformation = cpeInformation;
	}
	public String getConnectionUrl()
	{
		if(this.connectionUrl == "")
		{
			this.connectionUrl = this.getUrl();
		}
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) { this.connectionUrl = connectionUrl; }
	public Tr69Communication getComm() { return comm; }
	public ArrayBlockingQueue<AcsConfigurer> getArrayBlockingQueueForRpcTasks()
	{
		return arrayBlockingQueueForRpcTasks;
	}
	public void setArrayBlockingQueueForRpcTasks(ArrayBlockingQueue<AcsConfigurer> arrayBlockingQueueForRpcTasks)
	{
		this.arrayBlockingQueueForRpcTasks = arrayBlockingQueueForRpcTasks;
	}
	
	private String getUrl()
	{
		InetAddress inetAddress = null;
		try
		{
			inetAddress = InetAddress.getByName(this.ipAddress);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		if(inetAddress instanceof Inet4Address) { return "http://" + this.ipAddress + ":" + this.port + "/"; }
		else { return "http://[" + this.ipAddress + "]:" + this.port + "/"; }
	}
	
	@Override
	public GetParameterValuesResponse GetParameterValues(ParameterNames parameterNames)
	{
		GetParameterValues msg = new GetParameterValues(parameterNames);
		msg.setEnvelopeContentTypeEnum(EnvelopeBodyTypeEnum.GetParameterValues);
		if(this.rpcCommon(msg) == null) return null;
		return (GetParameterValuesResponse)this.rpcCommon(msg).getBody().getMsg();
	}
	@Override
	public SetParameterValuesResponse SetParameterValues(ParameterValueList valueList, ParameterKeyType key)
	{
		SetParameterValues msg = new SetParameterValues(valueList, key);
		msg.setEnvelopeContentTypeEnum(EnvelopeBodyTypeEnum.SetParameterValues);
		if(this.rpcCommon(msg) == null) return null;
		return (SetParameterValuesResponse)this.rpcCommon(msg).getBody().getMsg();
	}
	@Override
	public GetParameterNamesResponse GetParameterNames(String path,boolean nextLevel)
	{
		GetParameterNames msg = new GetParameterNames(path, nextLevel);
		msg.setEnvelopeContentTypeEnum(EnvelopeBodyTypeEnum.GetParameterNames);
		if(this.rpcCommon(msg) == null) return null;
		return (GetParameterNamesResponse)this.rpcCommon(msg).getBody().getMsg();
	}
	
	private SoapEnvelope rpcCommon(CwmpObjBase msg)
	{
		//construct envelope
		SoapBody sb = new SoapBody();
		sb.setMsg(msg);
        SoapHeader sh = new SoapHeader();
        sh.setHoldRequests(new SoapHeadHoldRequests(0));
        SoapEnvelope se = new SoapEnvelope();
        se.setHeader(sh);
        se.setBody(sb);
        se.setVersion(this.cpeInformation.getCwmpVersion());//TODO: synchronized?
        
        //inQueue
        AcsConfigurer acsConf = new AcsConfigurer();
        acsConf.setSeReq(se);
		if(this.arrayBlockingQueueForRpcTasks.offer(acsConf))
		{
			//TODO: more than 10 RPC tasks are waiting
			return null;
		}
		//reqConnection, check if isConnected and send blank http
		this.comm.RequestConnection();
		//try dequeue
		SoapEnvelope seResp;
		try
		{
			seResp = (SoapEnvelope) acsConf.getArrayBlockingQueueForRpcResp().take();
			//response received
			return seResp;
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * called until below two cases:
	 * 1. blank http received from CPE
	 * 2. CPE RPC response received
	 * @param cc
	 * @return
	 */
	public AcsConfigurer sendCpeRpc()
	{
		AcsConfigurer acsConfig;
		if((acsConfig = this.arrayBlockingQueueForRpcTasks.peek()) == null)
		{
			//disconnect
			this.comm.setConnected(false);
			//Because there may be a case like below:
			//acsServer peek but null returned, some user offers to the queue, some user reqConnection, acsServer disconnect
			//do this to avoid this case and no need lock
			if((acsConfig = this.arrayBlockingQueueForRpcTasks.peek()) != null)
			{
				return acsConfig;
			}
			else
			{
				//No waiting task, will reply blank http and disconnect
				return null;
			}
		}
		else
		{
			return acsConfig;
		}
	}
	
	public SoapEnvelope constructServerRpcResponse(EnvelopeBodyTypeEnum ebte)
	{
		SoapEnvelope seResp = new SoapEnvelope();
		switch(ebte)
		{
		case Inform:
			InformResponse infResp = new InformResponse(1);
			seResp.setVersion(this.cpeInformation.getCwmpVersion());
            seResp.setHeader(new SoapHeader());
            seResp.getHeader().setHoldRequests(new SoapHeadHoldRequests(0));
            seResp.setBody(new SoapBody());
            seResp.getBody().setMsg(infResp);
            break;
		case TransferComplete:
			break;
		case AutonomousTransferComplete:
			break;
		case GetRPCMethods:
			break;
		default:
			seResp = null;
			break;
		}
        return seResp;
		
	}
	
	public EnvelopeBodyTypeEnum getServerRpcResponseType(EnvelopeBodyTypeEnum ebte)
	{
		EnvelopeBodyTypeEnum ret = null;
		switch(ebte)
		{
		case Inform:
			ret = EnvelopeBodyTypeEnum.InformResponse;
			break;
		case TransferComplete:
			ret = EnvelopeBodyTypeEnum.TransferCompleteResponse;
			break;
		case AutonomousTransferComplete:
			ret = EnvelopeBodyTypeEnum.AutonomousTransferCompleteResponse;
			break;
		case GetRPCMethods:
			ret = EnvelopeBodyTypeEnum.GetRPCMethodsResponse;
			break;
		default:
			break;
		}
		
		return ret;
	}
}
