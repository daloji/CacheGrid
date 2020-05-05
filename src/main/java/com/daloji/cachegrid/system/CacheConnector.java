package com.daloji.cachegrid.system;

import com.daloji.cachegrid.GenericCache;
import com.daloji.cachegrid.connector.MemCachedTool;
import com.daloji.cachegrid.connector.RedisTool;
import com.daloji.caching.data.CacheSettings;
import com.daloji.caching.data.Engine;

public class CacheConnector implements GenericCache{

	private CacheEngine cacheEngine;

	public  CacheConnector(CacheSettings cacheSettings) {
		if(cacheSettings.getEngine()==Engine.REDIS) {
			cacheEngine = new RedisTool(cacheSettings);
		}
		if(cacheSettings.getEngine()==Engine.MEMCACHED) {
			cacheEngine = new MemCachedTool(cacheSettings);
		}
	}

	@Override
	public <T> void put(String key, T object) {
		cacheEngine.put(key, object);
	}

	@Override
	public <T> T get(String key) {
		return cacheEngine.getObject(key);
	}

	@Override
	public boolean existKey(String key) {
		return cacheEngine.existKey(key);
	}

}
