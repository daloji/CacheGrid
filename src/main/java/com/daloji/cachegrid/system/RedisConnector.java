package com.daloji.cachegrid.system;

import com.daloji.caching.data.CacheSettings;

public class RedisConnector implements GenericCache{

	private RedisTool redis;

	public  RedisConnector(CacheSettings cacheSettings) {
		redis = new RedisTool(cacheSettings);
	}

	@Override
	public <T> void put(String key, T object) {
		redis.put(key, object);
	}

	@Override
	public <T> T get(String key) {
		return redis.getObject(key);
	}

	@Override
	public boolean existKey(String key) {
		return redis.existKey(key);
	}

}
