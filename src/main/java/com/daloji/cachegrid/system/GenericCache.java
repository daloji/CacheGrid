package com.daloji.cachegrid.system;

public interface GenericCache<T> {

	public  void put(String key, T object);

	public T get(String key);
	
	public boolean existKey(String key);
}
