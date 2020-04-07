package com.daloji.cachegrid.system;

import static java.util.Objects.nonNull;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class CacheConfiguration {

	
	private static final String CONFIG = "cacheSettings.xml";
	
	private static  CacheConfiguration instance = null;

	private HashMap<String,GenericCache> mapServerSettings = null;


	private CacheConfiguration() {
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
	public static CacheConfiguration getInstance()
	{
		if(instance ==null) {
			instance = new CacheConfiguration();
		}
		return instance;
	}
	

	
	private ServerCacheSettings loadServerCacheConfig() {
	
		ServerCacheSettings serverSetting = null;
		InputStream is = CacheConfiguration.class.getClassLoader().getResourceAsStream(CONFIG);
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

	private  HashMap<String, GenericCache>  executeConfiguration(ServerCacheSettings serverSettings) {	
		 HashMap<String, GenericCache> mapSettings = null;
		if(nonNull(serverSettings) && nonNull(serverSettings.getCacheSettings())) {
			mapSettings = new HashMap<String,GenericCache>();
			for(CacheSettings cacheSettings:serverSettings.getCacheSettings()) {
				if(checkCacheSettings(cacheSettings)) {
					GenericCache cache = null;
					switch (cacheSettings.getEngine()) {
					case REDIS: cache = new RedisConnector(cacheSettings); 
						break;
					default:
						break;
					}
					if(nonNull(cache)) {
						mapSettings.put(cacheSettings.getName(),cache);	
					}
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
	
	
	public GenericCache getCache(String cacheName) {
		GenericCache cache = null;
		if(nonNull(mapServerSettings) && mapServerSettings.containsKey(cacheName)) {
			cache = mapServerSettings.get(cacheName);
		}
		return cache;
	}
	


}
