package com.daloji.cachegrid;

import com.daloji.cachegrid.common.AspectParam;
import com.daloji.cachegrid.common.Utils;

import static java.util.Objects.nonNull;

public class CacheManager {

	private CacheConfiguration cacheConfiguration = null ;

	private static CacheManager instance = null;


	private CacheManager() {
		cacheConfiguration = CacheConfiguration.getInstance() ;
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

	public  <T> T get(AspectParam<T> param) {
		Object obj =null;
		if(nonNull(param) && nonNull(param.getNameCache())) {
			String key = Utils.generateKey(param);
			GenericCache cacheSystem = cacheConfiguration.getCache(param.getNameCache());
			if(nonNull(cacheSystem)) {
				obj = cacheSystem.get(key);
			}
		}
		return (T) obj;
	}
	
	public  <T> void put(AspectParam<T> param,Object obj) {
		if(nonNull(param) && nonNull(param.getNameCache())) {
			String key = Utils.generateKey(param);
			GenericCache cacheSystem =cacheConfiguration.getCache(param.getNameCache());
			if(nonNull(cacheSystem) && nonNull(obj) ) {
				if(!cacheSystem.existKey(key)) {
					cacheSystem.put(key, obj);
				}
			}
		}
		
	}

}
