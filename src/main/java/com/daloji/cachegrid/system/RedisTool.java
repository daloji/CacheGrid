package com.daloji.cachegrid.system;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.time.Duration;

import com.daloji.cachegrid.common.Utils;
import com.daloji.caching.data.CacheSettings;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTool  implements CacheEngine{

	private JedisPool pool;

	private int ttl=10000;

	
	public RedisTool(CacheSettings cacheSettings) {
		pool = new JedisPool(buildPoolConfig(),cacheSettings.getIpAdress(),cacheSettings.getPort());

	}

	private JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		poolConfig.setMaxIdle(128);
		poolConfig.setMinIdle(16);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		return poolConfig;
	}


	@Override
	public <T> void put(String key, T object) {

		Jedis jedis=null;
		if(key !=null){
			try{
				byte[] bytearray =Utils.toByteArray(object);
				jedis = pool.getResource();
				if(jedis != null){
					jedis.set(key.getBytes(), bytearray);
					jedis.expire(key.getBytes(), ttl);
					jedis.close();
				}
			}catch (IOException e) {
				//NOP
			}
		}
	}


	@Override
	public boolean existKey(String key) {
		Jedis jedis=null;
		boolean exist = false;
		if(key != null){
			try {
			jedis = pool.getResource();
			if(jedis != null){
				exist =jedis.exists(key.getBytes());
			}
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				if(jedis!=null) {
					jedis.close();
				}
			}
		}

		return exist;
	}


	@Override
	public <T> T getObject(String key) {
		Jedis jedis=null;
		T returnObject =null;
		ObjectInput in = null;
		if(key != null){
			try {
				jedis = pool.getResource();
				if(jedis != null){
					byte[] byterep =jedis.get(key.getBytes());
					if(byterep!=null){
						ByteArrayInputStream bis = new ByteArrayInputStream(byterep);
						in = new ObjectInputStream(bis);
						returnObject = (T) in.readObject(); 
					}
				}
			}catch (Exception e) {
				//NOP
			}finally {
				if(jedis!=null) {
					jedis.close();
				}
			}
		}

		return returnObject;
	}
}
