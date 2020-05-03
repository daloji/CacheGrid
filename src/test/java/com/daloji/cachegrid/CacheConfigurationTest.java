package com.daloji.cachegrid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import com.daloji.caching.data.CacheSettings;
import com.daloji.caching.data.ServerCacheSettings;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "javax.crypto.*", "javax.security.auth.*","javax.management.*"})
@PrepareForTest({/*GenericCache.class*/})
public class CacheConfigurationTest {
	
	/**
	 * When param ServerCacheSetting is null for executeConfiguration method
	 */
	@Test
	public void testExecuteConfiguration_001() throws Exception {
		CacheConfiguration cacheConfig = CacheConfiguration.getInstance();
		EasyMockTool.setInaccessibleFieldValue(cacheConfig, "instance", null); 
		HashMap<String, GenericCache> hashCache = Whitebox.invokeMethod(cacheConfig, "executeConfiguration",null); 
		assertNull(hashCache);
	}
	/**
	 * 
	 * When param ServerCacheSetting is empty for executeConfiguration method
	 * @throws Exception
	 */

	@Test
	public void testExecuteConfiguration_002() throws Exception {
		ServerCacheSettings serverCache = new ServerCacheSettings();
		CacheConfiguration cacheConfig = CacheConfiguration.getInstance();
		EasyMockTool.setInaccessibleFieldValue(cacheConfig, "instance", null); 
		HashMap<String, GenericCache> hashCache = Whitebox.invokeMethod(cacheConfig, "executeConfiguration",serverCache); 
		assertNotNull(hashCache);
	}

	/**
	 * 
	 * When param ServerCacheSetting is empty for executeConfiguration method
	 * @throws Exception
	 */

	@Test
	public void testExecuteConfiguration_003() throws Exception {
		ServerCacheSettings serverCache = new ServerCacheSettings();
		CacheSettings cachesettings = new CacheSettings();	
		cachesettings.setIpAdress("127.0.0.1");
		cachesettings.setName("unkown");
		serverCache.getCacheSettings().add(cachesettings);
		CacheConfiguration cacheConfig = CacheConfiguration.getInstance();
		EasyMockTool.setInaccessibleFieldValue(cacheConfig, "instance", null); 
	    PowerMock.replayAll();
	    HashMap<String, GenericCache> hashCache = Whitebox.invokeMethod(cacheConfig, "executeConfiguration",serverCache); 
	    PowerMock.verifyAll();
		
		assertNotNull(hashCache);
		assertEquals(0,hashCache.size());
	}

}
