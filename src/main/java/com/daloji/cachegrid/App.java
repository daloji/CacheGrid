package com.daloji.cachegrid;

import java.util.ArrayList;
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
		
		app.testMethode(liste);
	}

	@Cache
	public int testMethode(List<Engine> e) {
		System.out.println("ICIICICICICI :testMethode");
		return 0;
	}
	

	//@Cache
	private void testMethode1() {
		System.out.println("ICIICICICICI :testMethode");
	}
}
