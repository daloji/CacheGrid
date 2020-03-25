package com.daloji.cachegrid;

import com.daloji.cachegrid.aspectj.Cache;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		BasicCache cache = new BasicCache<Object>();
		App app = new App();
		app.testMethode();
	}

	@Cache
	private void testMethode() {
		System.out.println("ICIICICICICI :testMethode");
	}
}
