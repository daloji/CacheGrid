package com.daloji.cachegrid;

import static org.junit.Assert.assertNull;

import org.easymock.EasyMock;
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
@PrepareForTest({CacheConfiguration.class,CacheManager.class})
public class CacheManagerTest {


	@MockStrict
	private CacheConfiguration cacheConfiguration;

	@Before
	public void beforeTest() {
		PowerMock.resetAll();
		PowerMock.mockStaticStrict(CacheConfiguration.class);
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

	//@Test
	public  void cacheManager_KO_02() {
		AspectParam param = new AspectParam();
		EasyMock.expect(CacheConfiguration.getInstance()).andReturn(cacheConfiguration);
		PowerMock.replayAll();
		CacheManager cache =  CacheManager.getInstance();
		Object obj = cache.get(param);
		PowerMock.verify();
		assertNull(obj);
	}
	
	/**
	 * when GenericCache is null 
	 */

	//@Test
	public  void cacheManager_KO_03() {
		AspectParam param = new AspectParam();
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
}
