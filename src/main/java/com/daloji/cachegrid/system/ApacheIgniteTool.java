package com.daloji.cachegrid.system;

import com.daloji.caching.data.CacheSettings;

public class ApacheIgniteTool implements CacheEngine{


	public ApacheIgniteTool(CacheSettings cacheSettings) {

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
