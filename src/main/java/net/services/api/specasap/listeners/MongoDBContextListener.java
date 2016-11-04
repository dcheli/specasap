package net.services.api.specasap.listeners;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import net.services.api.specasap.exceptions.MongoDBException;

public class MongoDBContextListener implements ServletContextListener {
	final static Logger logger = Logger.getLogger(MongoDBContextListener.class);
	MongoClient mongoClient = null;
	List<ServerAddress> seeds = new ArrayList<ServerAddress>();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		try {
			MongoCredential credential = MongoCredential.createScramSha1Credential(
					servletContext.getInitParameter("MONGODB_USER"),
					servletContext.getInitParameter("MONGODB_AUTH_DATABASE"),
					servletContext.getInitParameter("MONGODB_PWD").toCharArray());
		
			
			seeds.add(new ServerAddress(new InetSocketAddress(
				servletContext.getInitParameter("MONGODB_HOST"), 
					Integer.parseInt(servletContext.getInitParameter("MONGODB_PORT1")))));
	
//			mongoClient = new MongoClient(Arrays.asList(
//					//   new ServerAddress("192.168.99.100", 30001),
//					   new ServerAddress("192.168.99.100", 30001)));
			// The below does work
			//mongoClient = new MongoClient("192.168.99.100",30001);
			mongoClient = new MongoClient(seeds, Arrays.asList(credential));
//			mongoClient = new MongoClient(seeds, Arrays.asList(credential));
						
			// This is my test to see if Mongo is up or down.
			System.out.println("MongoAddress is: " + mongoClient.getAddress());
			
		} catch (MongoException e) {
			
			logger.error("Invalid MongoDB Port" + e);
			throw new MongoDBException("Invalid Connection");
			
		} finally{
			System.out.println("MongoClient initialized successfully");
			logger.info("MongoClient initialized successfully " + mongoClient);
			servletContextEvent.getServletContext().setAttribute("MONGODB_CLIENT", mongoClient);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		mongoClient = (MongoClient) sce.getServletContext()
							.getAttribute("MONGODB_CLIENT");
		mongoClient.close();
		logger.info("MongoClient closed successfully");
		System.out.println("MongoClient closed successfully");
	}

}
