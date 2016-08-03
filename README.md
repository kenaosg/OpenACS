# OpenACS
The project is based on TR-069 protocol(RPC) intended for communication between a CPE and Auto-Configuration Server (ACS).
It is written with Java, under SSM(SpringMVC, Spring, Mybatis).

#Package description:
1. Package "com.cwmp.acsserver.acs.structprimitive",
	-->class "CwmpObjBase": base class for primitive struct class, RPC struct class and SOAP struct class.
	-->other classes: primitive classes which will help construct or parse RPC methods.
2. Package "com.cwmp.acsserver.acs.structrpc",
	-->construct or parse RPC methods, which will be part of SOAP envelope.
3. Package "com.cwmp.acsserver.acs.soap",
	-->construct or parse SOAP envelope.
4. Package "com.cwmp.acsserver.acs",
	-->With all above classes, classes under this package will act like a real CPE.
