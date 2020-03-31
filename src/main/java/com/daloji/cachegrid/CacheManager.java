package com.daloji.cachegrid;

import static java.util.Objects.nonNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.daloji.caching.data.CacheSettings;
import com.daloji.caching.data.Engine;
import com.daloji.caching.data.ServerCacheSettings;;

/**
 * @author daloji
 * @param <K>
 *
 */
public class CacheManager {

	
	private static final String CONFIG = "cacheSettings.xml";
	
	private static  CacheManager instance = null;

	private HashMap<Engine, List<CacheSettings>> mapServerSettings = null;

	
	
	private CacheManager() {
		ServerCacheSettings serverCache = loadServerCacheConfig();
		if(nonNull(serverCache)) {
			mapServerSettings =	executeConfiguration(serverCache);
		}
	}
	
	/**
	 * singleton
	 * 
	 * @return
	 */
	public static CacheManager getInstance()
	{
		if(instance ==null) {
			instance = new CacheManager();
		}
		return instance;
	}
	
	
	
	private ServerCacheSettings loadServerCacheConfig() {
	
		ServerCacheSettings serverSetting = null;
		InputStream is = CacheManager.class.getClassLoader().getResourceAsStream(CONFIG);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ServerCacheSettings.class);
			Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();
			serverSetting =(ServerCacheSettings) Unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}
		return serverSetting;
		
	}

	private  HashMap<Engine, List<CacheSettings>>  executeConfiguration(ServerCacheSettings serverSettings) {	
		 HashMap<Engine, List<CacheSettings>> mapSettings = null;
		if(nonNull(serverSettings) && nonNull(serverSettings.getCacheSettings())) {
			mapSettings = new HashMap<Engine,List<CacheSettings>>();
			for(CacheSettings cacheSettings:serverSettings.getCacheSettings()) {
				if(checkCacheSettings(cacheSettings)) {
					List<CacheSettings> listCache = null;
					if(mapSettings.containsKey(cacheSettings.getEngine())) {
						listCache = mapSettings.get(cacheSettings.getEngine());
					}else {
						listCache = new ArrayList<CacheSettings>();			
					}
					listCache.add(cacheSettings);
					mapSettings.put(cacheSettings.getEngine(), listCache);
				}
			}
		}
		
		return mapSettings;
	}


	private boolean checkEngine(String engine) {
		boolean iscorrect = false;
		if(Engine.APACHEIGNITE.name().equals(engine) ||//
				Engine.REDIS.name().equals(engine)||//
				Engine.HAZELCAST.name().equals(engine)||//
				Engine.MEMCACHED.name().equals(engine)) {
			iscorrect = true;
		}
		return iscorrect;
	}

	private boolean checkCacheSettings(CacheSettings cacheSetting) {
		boolean iscorrect = false;
		if(nonNull(cacheSetting) && //
				nonNull(cacheSetting.getEngine())) {
			iscorrect = checkEngine(cacheSetting.getEngine().name());

		}
		return iscorrect;

	}

}
