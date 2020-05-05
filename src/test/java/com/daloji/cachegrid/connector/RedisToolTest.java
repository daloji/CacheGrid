package com.daloji.cachegrid.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.MockStrict;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.daloji.cachegrid.CacheConfiguration;
import com.daloji.cachegrid.EasyMockTool;
import com.daloji.cachegrid.common.Utils;
import com.daloji.cachegrid.connector.RedisTool;
import com.daloji.caching.data.CacheSettings;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.crypto.*","javax.security.auth.*","javax.management.*"})
@PrepareForTest({JedisPool.class,Jedis.class})
public class RedisToolTest {


	private static PodamFactory podam = new PodamFactoryImpl();

	@MockStrict
	private JedisPool jedisPool;

	@MockStrict
	private Jedis jedis;

	@BeforeClass
	public static void init() 
	{
		podam.getStrategy().setMemoization(false);
	}


	@Before
	public void beforeTest() {
		PowerMock.resetAll();
		PowerMock.mockStaticStrict(JedisPool.class);
		PowerMock.mockStaticStrict(Jedis.class);
	}

	/**
	 * Put object Null
	 * @throws Exception
	 */
	@Test
	public void putObject_002_KO() throws Exception {
		CacheSettings cache = podam.manufacturePojo(CacheSettings.class);
		cache.setPort(6375);
		cache.setIpAdress("127.0.0.1");
		RedisTool redis = new RedisTool(cache);
		EasyMockTool.setInaccessibleFieldValue(redis, "pool", jedisPool); //$NON-NLS-1$
		PowerMock.replayAll();
		redis.put(null, null);
		PowerMock.verify();

	}
	
	/**
	 * Put object Null
	 * @throws Exception
	 */
	@Test
	public void putObject_001_KO() throws Exception {
		CacheSettings cache = podam.manufacturePojo(CacheSettings.class);
		cache.setPort(6375);
		cache.setIpAdress("127.0.0.1");
		RedisTool redis = new RedisTool(cache);
		EasyMockTool.setInaccessibleFieldValue(redis, "pool", jedisPool); //$NON-NLS-1$
		EasyMock.expect(jedisPool.getResource()).andReturn(null);
		PowerMock.replayAll();
		redis.put("key", null);
		PowerMock.verify();

	}
	
	/**
	 * Put object nominal
	 * @throws Exception
	 */
	@Test
	public void putObject_003_OK() throws Exception {
		CacheSettings cache = podam.manufacturePojo(CacheSettings.class);
		cache.setPort(6375);
		cache.setIpAdress("127.0.0.1");
		String key = "key";
		String value = "value";
		RedisTool redis = new RedisTool(cache);
		EasyMockTool.setInaccessibleFieldValue(redis, "pool", jedisPool); 
		EasyMock.expect(jedis.set(EasyMock.anyObject(byte[].class),EasyMock.anyObject())).andReturn(null);
		EasyMock.expect(jedisPool.getResource()).andReturn(jedis);
		EasyMock.expect(jedis.expire(EasyMock.anyObject(byte[].class), EasyMock.anyInt())).andReturn(null);
		jedis.close();
		EasyMock.expectLastCall();
		PowerMock.replayAll();
		redis.put(key, value);
		PowerMock.verify();

	}
	
	
	/**
	 * Get object key null
	 * @throws Exception
	 */
	@Test
	public void getObject_001_OK() throws Exception {
		CacheSettings cache = podam.manufacturePojo(CacheSettings.class);
		cache.setPort(6375);
		cache.setIpAdress("127.0.0.1");
		RedisTool redis = new RedisTool(cache);
		EasyMockTool.setInaccessibleFieldValue(redis, "pool", jedisPool); 
		PowerMock.replayAll();
		Object obj= redis.getObject(null);
		PowerMock.verify();
		assertNull(obj);

	}
	
	/**
	 * Get object key null
	 * @throws Exception
	 */
	@Test
	public void getObject_002_KO() throws Exception {
		CacheSettings cache = podam.manufacturePojo(CacheSettings.class);
		cache.setPort(6375);
		cache.setIpAdress("127.0.0.1");
		String key = "key";
		String value = "value";
		RedisTool redis = new RedisTool(cache);
		EasyMockTool.setInaccessibleFieldValue(redis, "pool", jedisPool); 
		EasyMock.expect(jedis.get(EasyMock.anyObject(byte[].class))).andReturn(Utils.toByteArray(value));
		EasyMock.expect(jedisPool.getResource()).andReturn(jedis);
		jedis.close();
		EasyMock.expectLastCall();
		PowerMock.replayAll();
		Object obj= redis.getObject(key);
		PowerMock.verify();
		assertEquals(obj,value);

	}
	
}
