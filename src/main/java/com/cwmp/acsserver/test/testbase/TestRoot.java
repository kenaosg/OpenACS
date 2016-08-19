package com.cwmp.acsserver.test.testbase;

public abstract class TestRoot
{
	public abstract void collectResources();
	public abstract void setup();
	public abstract void test();
	public abstract void cleanup();
}
