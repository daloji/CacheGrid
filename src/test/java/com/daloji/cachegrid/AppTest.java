package com.daloji.cachegrid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.daloji.caching.data.CacheSettings;
import com.daloji.caching.data.Engine;
import com.daloji.caching.data.ServerCaches;



/**
 * Unit test for simple App.
 */
public class AppTest  {

    
    @Test
    public void test001() throws JAXBException {
    	
    	
    	ServerCaches serv = new ServerCaches();

    	CacheSettings cache = new CacheSettings();
    	cache.setEngine(Engine.REDIS);
    	cache.setName("rediscache");
    	cache.setIpAdresse("127.0.0.1");
    	cache.setPort("8364");
    	List<CacheSettings> listcache = new ArrayList<CacheSettings>();
    	listcache.add(cache);
    	listcache.add(cache);
    	serv.getCacheSettings().addAll(listcache);
    
    	
    	File file = new File("C:\\Users\\A632436\\file.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(ServerCaches.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(serv, file);
		jaxbMarshaller.marshal(serv, System.out);
    }
}
