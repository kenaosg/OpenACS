package com.cwmp.acsserver.acs.soap;

public enum CwmpVersionEnum
{
	Any(0xff),
    Ver_1_0(0),
    Ver_1_1(1),
    Ver_1_2(2),
    Ver_1_3(3),
    Ver_1_4(4);
	
	private int id;
	private CwmpVersionEnum(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public static CwmpVersionEnum getById(int id)
	{
		switch(id)
		{
		case 0:
			return CwmpVersionEnum.Ver_1_0;
		case 1:
			return CwmpVersionEnum.Ver_1_1;
		case 2:
			return CwmpVersionEnum.Ver_1_2;
		case 3:
			return CwmpVersionEnum.Ver_1_3;
		case 4:
			return CwmpVersionEnum.Ver_1_4;
		case 0xff:
			return CwmpVersionEnum.Any;
		}
		return null;
	}
}
