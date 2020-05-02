package com.daloji.cachegrid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.MockStrict;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.daloji.cachegrid.common.AspectParam;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.crypto.*","javax.security.auth.*"})
@PrepareForTest({CacheConfiguration.class,CacheManager.class,GenericCache.class})
public class CacheManagerTest {


	@MockStrict
	private CacheConfiguration cacheConfiguration;
	
	@MockStrict
	private GenericCache genericCache;

	@Before
	public void beforeTest() {
		PowerMock.resetAll();
		PowerMock.mockStaticStrict(CacheConfiguration.class);
		PowerMock.mockStaticStrict(GenericCache.class);
	}
	
	@After
	public void afterTest() throws Exception{
		CacheManager cache =  CacheManager.getInstance();
		EasyMockTool.setInaccessibleFieldValue(cache, "instance", null); 
	}

	/**
	 * when AspectParam  null 
	 */

	@Test
	public  void cacheManager_KO_01() {
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		Object obj = cache.get(null);
		PowerMock.verify();
		assertNull(obj);
	}

	/**
	 * when AspectParam  cacheEngine is null 
	 */

	@Test
	public  void cacheManager_KO_02() {
		AspectParam<Object> param = new AspectParam<Object>();
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		Object obj = cache.get(param);
		PowerMock.verify();
		assertNull(obj);
	}

	/**
	 * get when GenericCache is null 
	 */

	@Test
	public  void cacheManager_KO_03() throws  Exception{
		AspectParam<Object> param = new AspectParam<Object>();
		param.setNameCache("redis");
		Object[] obj = new Object[3];
		obj[0] = "value";
		obj[1] = "value";
		param.setParamValue(obj);
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		EasyMock.expect(cacheConfiguration.getCache("redis")).andReturn(null);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		Object retobj = cache.get(param);
		PowerMock.verify();
		assertNull(retobj);
	}
	
	/**
	 * Put  when Param and GenericCache is null
	 * @throws Exception
	 */
	@Test
	public  void cacheManager_KO_04() throws  Exception{
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		EasyMock.expect(cacheConfiguration.getCache("redis")).andReturn(null);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		cache.put(null, null);
		PowerMock.verify();
	}
	/**
	 * Put  when GenericCache is null
	 * @throws Exception
	 */
	@Test
	public  void cacheManager_KO_05() throws  Exception{
		AspectParam<Object> param = new AspectParam<Object>();
		param.setNameCache("redis");
		Object[] obj = new Object[3];
		obj[0] = "value";
		obj[1] = "value";
		param.setParamValue(obj);
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		EasyMock.expect(cacheConfiguration.getCache("redis")).andReturn(null);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		cache.put(param, null);
		PowerMock.verify();
	}
	/**
	 * get objet from CacheManager
	 * @throws Exception
	 */
	@Test
	public  void cacheManager_OK_01() throws  Exception {
		AspectParam<Object> param = new AspectParam<Object>();
		param.setNameCache("redis");
		Object[] obj = new Object[3];
		obj[0] = "value";
		obj[1] = "value";
		String value = "value";
		param.setParamValue(obj);
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		EasyMock.expect(cacheConfiguration.getCache("redis")).andReturn(genericCache);
		EasyMock.expect(genericCache.get(EasyMock.anyString())).andReturn(value);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		Object retobj = cache.get(param);
		PowerMock.verify();
		assertNotNull(retobj);
		assertEquals(retobj, value);
	}

	/**
	 * Put objet to CacheManager
	 * @throws Exception
	 */
	@Test
	public  void cacheManager_OK_02() throws  Exception {
		AspectParam<Object> param = new AspectParam<Object>();
		param.setNameCache("redis");
		Object[] obj = new Object[3];
		obj[0] = "value";
		obj[1] = "value";
		String value = "value";
		param.setParamValue(obj);
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		EasyMock.expect(cacheConfiguration.getCache("redis")).andReturn(genericCache);
		EasyMock.expect(genericCache.existKey(EasyMock.anyString())).andReturn(true);

		genericCache.put(EasyMock.anyString(), EasyMock.eq(value));
		EasyMock.expectLastCall();
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		cache.put(param, value);
		PowerMock.verify();
	
	}
	
}
