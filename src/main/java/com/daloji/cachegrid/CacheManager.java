package com.daloji.cachegrid;

import java.util.HashMap;
import java.util.List;

import com.daloji.caching.data.CacheSettings;
import com.daloji.caching.data.Engine;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;;

/**
 * @author daloji
 *
 */
public class CacheManager {

	private HashMap<Engine, List<CacheSettings>> mapSettings = new HashMap<Engine,List<CacheSettings>>();


	private void loadConfiguration(List<CacheSettings> listCacheSettings) {	
		if(listCacheSettings !=null) {
			for(CacheSettings cacheSettings:listCacheSettings) {
				if(checkCacheSettings(cacheSettings)) {
					List<CacheSettings> listCache = null;
					if(mapSettings.containsKey(cacheSettings.getEngine())) {
						listCache = mapSettings.get(cacheSettings.getName());
					}else {
						listCache = new ArrayList<CacheSettings>();			
					}
					listCache.add(cacheSettings);
					mapSettings.put(cacheSettings.getEngine(), listCache);
				}
			}
		}
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
			iscorrect = iscorrect & checkEngine(cacheSetting.getEngine().name());

		}
		return iscorrect;

	}

}
