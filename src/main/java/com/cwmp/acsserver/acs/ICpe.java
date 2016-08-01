package com.cwmp.acsserver.acs;

import com.cwmp.acsserver.acs.structprimitive.ParameterKeyType;
import com.cwmp.acsserver.acs.structprimitive.ParameterNames;
import com.cwmp.acsserver.acs.structprimitive.ParameterValueList;
import com.cwmp.acsserver.acs.structrpc.GetParameterNamesResponse;
import com.cwmp.acsserver.acs.structrpc.GetParameterValuesResponse;
import com.cwmp.acsserver.acs.structrpc.SetParameterValuesResponse;

public interface ICpe
{
	GetParameterValuesResponse GetParameterValues(ParameterNames parameterNames);
	SetParameterValuesResponse SetParameterValues(ParameterValueList valueList, ParameterKeyType key);
	GetParameterNamesResponse GetParameterNames(String path, boolean nextLevel);
//	GetParameterAttributesResponse GetParameterAttributes( ParameterNames names );
//	SetParameterAttributesResponse SetParameterAttributes(SetParameterAttributesList parameterList);
//	AddObjectResponse AddObject( ObjectNameType obj, ParameterKeyType key );
//	DeleteObjectResponse DeleteObject( ObjectNameType obj, ParameterKeyType key );
//	RebootResponse Reboot(CommandKeyType cmdKey);
//	DownloadResponse Download(CommandKeyType commandkey, DownloadFileType filetype, String url, String username, String password, int filesize, String targetfilename, int delayseconds, String successurl, String failureurl);
//	UploadResponse Upload(CommandKeyType commandKey, UploadFileType fileType, String url, String username, String password, int delaySeconds);
//	FactoryResetResponse FactoryReset();
}
