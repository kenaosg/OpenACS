package com.cwmp.acsserver.test.testbase;

public class TestCommonBase extends TestRoot
{

	@Override
	public void collectResources()
	{
		// TODO collect resources necessary
		this.collectResourcesByUser();
	}

	@Override
	public void setup()
	{
		// TODO setup parameters necessary
		this.setupByUser();
	}

	@Override
	public void test()
	{
		//completed by user with common API
		//initiate threads, process message...
	}

	@Override
	public void cleanup()
	{
		// TODO cleanup resources clooected by this class
		this.cleanupByUser();
	}
	
	public void collectResourcesByUser() {}
	public void setupByUser() {}
	public void cleanupByUser() {}

}
