package com.cwmp.acsserver.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.cwmp.acsserver.acs.AcsConfigurer;
import com.cwmp.acsserver.acs.CpeEntity;
import com.cwmp.acsserver.acs.soap.SoapBody;
import com.cwmp.acsserver.acs.soap.SoapEnvelope;
import com.cwmp.acsserver.acs.soap.SoapHeadHoldRequests;
import com.cwmp.acsserver.acs.soap.SoapHeader;
import com.cwmp.acsserver.acs.structprimitive.EnvelopeBodyTypeEnum;
import com.cwmp.acsserver.acs.structrpc.Inform;
import com.cwmp.acsserver.acs.structrpc.InformResponse;
import com.cwmp.acsserver.tools.singleton.DocumentBuilderFactorySingleton;

@Controller
@RequestMapping("/")
public class AcsServerController
{
	private static Logger logger = Logger.getLogger(AcsServerController.class);
	
	/**
	 * acs server
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/acs")
	public void acsServer(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			Map<String, CpeEntity> cpes = (Map<String, CpeEntity>) req.getServletContext().getAttribute("cpes");
			CpeEntity cpe;
			AcsConfigurer acsConfig;
			
			if(req.getContentLength() == 0)//ready for sending cpe rpc
			{
				cpe = cpes.get(req.getRemoteAddr());
				if((acsConfig = cpe.getCpeClient().sendCpeRpc()) == null)
				{
					//No waiting task, will reply blank http and disconnect
					logger.info("Blank message received, and no task waiting, cpe info-->" + cpe.getCpeClient().getCpeInformation().toString());
				}
				else
				{
					logger.info("Blank message received, cpe info-->" + cpe.getCpeClient().getCpeInformation().toString()
							+ ". waiting task num-->" + cpe.getCpeClient().getArrayBlockingQueueForRpcTasks().size()
							+ ", first one-->" + acsConfig.getSeReq().getBody().getMsg().getEnvelopeContentTypeEnum());
					resp.setStatus(200);
                    resp.setContentType("text/xml; charset=utf-8");
                    String seRespStr = acsConfig.getSeReq().toXml();
                    resp.setContentLength(seRespStr.length());
                    resp.getWriter().print(seRespStr);
                    resp.getWriter().flush();
				}
			}
			else if((req.getContentLength() > 0) && req.getContentType().contains("text/xml"))
			{
				SoapEnvelope se = null;
				try
				{
					se = new SoapEnvelope(DocumentBuilderFactorySingleton.getInstance().getDocBuilder().parse(req.getInputStream()).getFirstChild());
				} catch (IOException | SAXException e)
				{
					e.printStackTrace();
				}
				EnvelopeBodyTypeEnum envelopeBodyTypeEnumReceived = se.getBody().getMsg().getEnvelopeContentTypeEnum();
				switch (envelopeBodyTypeEnumReceived)
				{
				case Inform:
					Inform inf = (Inform)(se.getBody().getMsg());
					if(cpes.containsKey(inf.getDeviceId().getSerialNumber()))
					{
						cpe = cpes.get(inf.getDeviceId().getSerialNumber());
						cpe.updateCpeInformation(se);
					}
					else
					{
						cpe = new CpeEntity(se, req);
						cpes.put(cpe.getSerialNumber(), cpe);
						cpes.put(cpe.getIpAddress(), cpe);
						cpe.updateCpeInformationFirstTime(se);
					}
					logger.info("Received Inform message-->" + cpe.getIpAddress() + ", " + cpe.getCpeClient().getCpeInformation().toString());
                    resp.setStatus(200);
                    resp.setContentType("text/xml; charset=utf-8");
                    String seRespStr = cpe.getCpeClient().constructServerRpcResponse(EnvelopeBodyTypeEnum.InformResponse).toXml();
                    resp.setContentLength(seRespStr.length());
                    resp.getWriter().print(seRespStr);
                    resp.getWriter().flush();
					break;
				case TransferComplete:
				case AutonomousTransferComplete:
				case GetRPCMethods:
					cpe = cpes.get(req.getRemoteAddr());
					cpe.updateCpeInformation(se);
					logger.info("Received " + envelopeBodyTypeEnumReceived + " message-->" + cpe.getIpAddress() + ", " + cpe.getCpeClient().getCpeInformation().toString());
					resp.setStatus(200);
                    resp.setContentType("text/xml; charset=utf-8");
                    seRespStr = cpe.getCpeClient().constructServerRpcResponse(cpe.getCpeClient().getServerRpcResponseType(envelopeBodyTypeEnumReceived)).toXml();
                    resp.setContentLength(seRespStr.length());
                    resp.getWriter().print(seRespStr);
                    resp.getWriter().flush();
					break;
				default:
					cpe = cpes.get(req.getRemoteAddr());
					logger.info("Received RPC response, cpe info-->" + cpe.getCpeClient().getCpeInformation().toString()
							+ ", RPC-->" + envelopeBodyTypeEnumReceived);
					acsConfig = cpe.getCpeClient().getArrayBlockingQueueForRpcTasks().poll();
					acsConfig.getArrayBlockingQueueForRpcResp().add(se);
					//ready for sending cpe rpc
					if((acsConfig = cpe.getCpeClient().sendCpeRpc()) == null)
					{
						//No waiting task, will reply blank http and disconnect
						logger.info("RPC response received, and no task waiting, cpe info");
					}
					else
					{
						logger.info("RPC response received, waiting task num-->" + cpe.getCpeClient().getArrayBlockingQueueForRpcTasks().size()
								+ ", first one-->" + acsConfig.getSeReq().getBody().getMsg().getEnvelopeContentTypeEnum());
						resp.setStatus(200);
	                    resp.setContentType("text/xml; charset=utf-8");
	                    seRespStr = acsConfig.getSeReq().toXml();
	                    resp.setContentLength(seRespStr.length());
	                    resp.getWriter().print(seRespStr);
	                    resp.getWriter().flush();
					}
					break;
				}
			}
			else
			{
				logger.info("Unexpected http received-->not blank, not XML");
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	@RequestMapping("/reqRpc")
	public void reqRpc(HttpServletRequest req, HttpServletResponse resp)
	{
		//TODO: According to front end, you can process any CPE RPC req here.
		//Just use RPC methods in CpeClient, like GetParameterValues, SetParameterValues, GetParameterNames, etc.
	}
	
	@RequestMapping("/reqTestCaseList")
	public void reqTestCaseList(HttpServletRequest req, HttpServletResponse resp)
	{
		//TODO:
	}
}
