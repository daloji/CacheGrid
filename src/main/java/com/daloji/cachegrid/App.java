package com.daloji.cachegrid;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.daloji.cachegrid.aspectj.Cache;
import com.daloji.caching.data.Engine;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		App app = new App();
		List<Engine> liste=new ArrayList<Engine>();
		liste.add(Engine.REDIS);
		
		//int val =app.testMethodeTest(liste,new HashMap<String, String>());
		//System.out.println("val : " + val);
		 app.testListRep(10);
		//List<Engine> list = app.testListRep(10);
	//	System.out.println("size : " + list.size());
		//System.out.println("info : "+ list.get(0).toString());
		
		// HashMap<String, String> hashmap = app.testHashRep(10);
		//System.out.println("size : " + hashmap.size());
		//System.out.println("info : "+ hashmap.get("1"));
		

	}
	
	

	@Cache(engineName = "rediscache")
	public void testListRep(int aa) {
		System.out.println("methode testListRep");
		List<Engine> liste=new ArrayList<Engine>();
		liste.add(Engine.REDIS);
		liste.add(Engine.MEMCACHED);
		liste.add(Engine.HAZELCAST);
		for(int i=0;i<1000000;i++) {
			System.out.println("iciciciiciciciic");
		}
		//return liste;
	}
	
	@Cache(engineName = "rediscache")
	public HashMap<String, String> testHashRep(int aa) {
		System.out.println("methode testListRep");
		List<Engine> liste=new ArrayList<Engine>();
		liste.add(Engine.REDIS);
		liste.add(Engine.MEMCACHED);
		liste.add(Engine.HAZELCAST);
		for(int i=0;i<1000000;i++) {
			System.out.println("iciciciiciciciic");
		}
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("1","value1");
		hashmap.put("2","value2");
		hashmap.put("3","value3");
		hashmap.put("4","value5");
		return hashmap;
	}
	

	@Cache(engineName = "rediscache")
	public int testMethode(List<Engine> e) {
		return 444;
	}
	
	
	@Cache(engineName = "rediscache")
	public int testMethodeTest(List<Engine> e,HashMap<String, String> mm) {
		long start = System.currentTimeMillis();
		for(int i=0;i<1000000;i++) {
			System.out.println("iciciciiciciciic");
		}
		long stop = System.currentTimeMillis();

		return 789546;
	}

	//@Cache
	private void testMethode1() {
		System.out.println("ICIICICICICI :testMethode");
	}
}
