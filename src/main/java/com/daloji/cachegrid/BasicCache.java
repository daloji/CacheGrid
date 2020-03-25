package com.daloji.cachegrid;

import java.util.concurrent.ConcurrentHashMap;

public class BasicCache<K> {
	
	
	private ConcurrentHashMap<String, K> cache;
	

	public BasicCache() {
		 cache = new ConcurrentHashMap<String, K>();
	}


	public void put(String key,K value) {
		cache.put(key, value);
	}
	
	public K get(String key) {
		return cache.get(key);
	}

}
