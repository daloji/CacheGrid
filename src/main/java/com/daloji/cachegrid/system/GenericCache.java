package com.daloji.cachegrid.system;

public interface GenericCache {

	public  <T> void put(String key, T object);

	public <T> T get(String key);
	
	public boolean existKey(String key);
}
