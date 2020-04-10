package com.daloji.cachegrid.system;

import static java.util.Objects.nonNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;

import com.daloji.cachegrid.common.Utils;
import com.daloji.caching.data.CacheSettings;

import net.spy.memcached.MemcachedClient;


public class MemCachedTool  implements CacheEngine{

	private MemcachedClient  memcached;

	private int ttl=10000;

	public MemCachedTool(CacheSettings cacheSettings) {

		try {
			InetSocketAddress inetSock = new InetSocketAddress(cacheSettings.getIpAdress(),cacheSettings.getPort());
			memcached=new MemcachedClient(inetSock);
		} catch (IOException e) {
			memcached = null;
		}
	}

	@Override
	public <T> void put(String key, T object) {
		if(nonNull(memcached) && nonNull(object)) {
			byte[] bytearray;
			try {
				bytearray = Utils.toByteArray(object);
				memcached.add(key, ttl, bytearray);
			} catch (IOException e) {
				//NOP
			}

		}

	}

	@Override
	public boolean existKey(String key) {
	boolean value = false;
		if(nonNull(memcached) && nonNull(key)) {
		}
		return false;
	}

	@Override
	public <T> T getObject(String key) {
		ObjectInput in = null;
		T returnObject =null;
		if(nonNull(memcached) && nonNull(key)) {
			byte[] byterep =	(byte[]) memcached.get(key);
			if(byterep!=null){
				try {
					ByteArrayInputStream bis = new ByteArrayInputStream(byterep);
					in = new ObjectInputStream(bis);
					returnObject = (T) in.readObject(); 
				} catch (IOException | ClassNotFoundException e) {
				   // NOP
				}
			}
		}
		return returnObject;
	}

}
