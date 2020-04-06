package com.daloji.cachegrid.system;

import com.daloji.cachegrid.common.AspectParam;
import com.daloji.cachegrid.common.Utils;

import static java.util.Objects.nonNull;

public class CacheManager {

	CacheConfiguration cacheConfiguration = null ;

	private static  CacheManager instance = null;


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
		if(nonNull(param)) {
		   String key =	Utils.generateKey(param);
		   
		}
		return (T) obj;
	}

}
