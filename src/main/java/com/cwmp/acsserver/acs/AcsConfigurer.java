package com.cwmp.acsserver.acs;

import java.util.concurrent.ArrayBlockingQueue;

import com.cwmp.acsserver.acs.soap.SoapEnvelope;

public class AcsConfigurer
{
	SoapEnvelope seReq;
	ArrayBlockingQueue<SoapEnvelope> arrayBlockingQueueForRpcResp;
	
	public AcsConfigurer() 
	{
		arrayBlockingQueueForRpcResp = new ArrayBlockingQueue<SoapEnvelope>(1);
	}
	
	public SoapEnvelope getSeReq()
	{
		return seReq;
	}

	public void setSeReq(SoapEnvelope seReq)
	{
		this.seReq = seReq;
	}

	public ArrayBlockingQueue<SoapEnvelope> getArrayBlockingQueueForRpcResp()
	{
		return arrayBlockingQueueForRpcResp;
	}

	public void setArrayBlockingQueueForRpcResp(
			ArrayBlockingQueue<SoapEnvelope> arrayBlockingQueueForRpcResp)
	{
		this.arrayBlockingQueueForRpcResp = arrayBlockingQueueForRpcResp;
	}
	
}
