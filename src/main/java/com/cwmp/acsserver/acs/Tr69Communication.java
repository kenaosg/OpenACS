package com.cwmp.acsserver.acs;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Tr69Communication
{
	private boolean isConnected = false;
	private CpeClient client;
	public Object syncIsConnected = new Object();
	
	private CloseableHttpClient httpClient;
	
	Tr69Communication(CpeClient client)
    {
        this.client = client;
    }

	public boolean isConnected() 
	{
		return this.isConnected;
	}
	public void setConnected(boolean isConnected)
	{
		//synchronized(this.client)
		//{
			this.isConnected = isConnected;
		//}
	}
	public CpeClient getClient(){ return client; }
	public void setClient(CpeClient client){ this.client = client; }
	
	public void RequestConnection()
	{
		if(!this.isConnected)
		{
			synchronized(syncIsConnected)//There may be several persons req CPE RPC.
			{
				if(this.isConnected) return;
				else
				{
					//send blank http get
					RequestConfig reqConfig = RequestConfig.custom()
							.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
							.build();
					CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
					credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.client.getUsername(), this.client.getPassword()));
					httpClient = HttpClients.custom()
							.setDefaultCredentialsProvider(credentialsProvider)
							.setDefaultRequestConfig(reqConfig)
							.build();
					HttpGet httpGet = new HttpGet(this.client.getConnectionUrl());
					try
					{
						httpClient.execute(new HttpHost(this.client.getIpAddress() + ":" + this.client.getPort()), httpGet);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return;
	}

	@Override
	protected void finalize() throws Throwable
	{
		// TODO Auto-generated method stub
		super.finalize();
		this.httpClient.close();
	}	
}
