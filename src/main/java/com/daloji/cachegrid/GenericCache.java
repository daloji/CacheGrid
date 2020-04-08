package com.daloji.cachegrid;

/**
 * all methods need for all Cache System
 * @author daloji
 *
 */
public interface GenericCache {

	/**
	 * Put object in Cache
	 * @param <T>
	 * @param key
	 *      key value
	 * @param object
	 * object
	 */
	public  <T> void put(String key, T object);

	/**
	 * get object from Cache
	 * @param <T>
	 * @param key
	 *      key value
	 * @return
	 */
	public <T> T get(String key);

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean existKey(String key);
}
