package com.daloji.cachegrid.system;

public interface CacheEngine {
	
	public <T> void put(String key, T object);
	
	public boolean existKey(String key);
	
	public <T> T getObject(String key);
}
