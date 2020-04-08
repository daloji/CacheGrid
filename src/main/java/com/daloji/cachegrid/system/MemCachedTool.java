package com.daloji.cachegrid.system;

import com.daloji.caching.data.CacheSettings;

import redis.clients.jedis.JedisPool;

public class MemCachedTool  implements CacheEngine{

	
	public MemCachedTool(CacheSettings cacheSettings) {

	}
	
	@Override
	public <T> void put(String key, T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T getObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
