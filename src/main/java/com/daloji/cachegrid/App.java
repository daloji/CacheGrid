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
		
		int val =app.testMethodeTest(liste,new HashMap<String, String>());
		System.out.println("val : " + val);
	}

	@Cache(engineName = "rediscache")
	public int testMethode(List<Engine> e) {
		System.out.println("ICIICICICICI :testMethode");
		return 444;
	}
	
	
	@Cache(engineName = "rediscache")
	public int testMethodeTest(List<Engine> e,HashMap<String, String> mm) {
		long start = System.currentTimeMillis();
		for(int i=0;i<1000000;i++) {
			System.out.println("iciciciiciciciic");
		}
		long stop = System.currentTimeMillis();

		System.out.println(stop-start);
		return 789546;
	}

	//@Cache
	private void testMethode1() {
		System.out.println("ICIICICICICI :testMethode");
	}
}
