package org.zgf.lean.lean_jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class BaseTest {
	
	protected Jedis jedis;
	
	private String serverHost;
	private Integer serverPort;
	
	@Before
	public void setUp(){
		this.serverHost = "172.17.155.51";
		this.serverPort = 6379;
		this.jedis = new Jedis(serverHost, serverPort);
	}
	
	@After
	public void tearDown(){
		this.jedis.close();
	}

}
