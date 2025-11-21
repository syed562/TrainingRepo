package com.chubb.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		final Logger logger=LogManager.getLogger(Main.class);
		logger.trace("Hiiiiii");
		  logger.trace("This is a TRACE message - very detailed debugging info");
	        logger.debug("This is a DEBUG message - for debugging");
	        logger.info("This is an INFO message - general information");
	        logger.warn("This is a WARN message - something unexpected but not an error");
	        logger.error("This is an ERROR message - an issue occurred");
	        logger.fatal("This is a FATAL message - severe system failure");
		Worker worker=new Worker();
		worker.start();
		ExecutorService service=Executors.newFixedThreadPool(3);
		service.execute(worker);
		service.execute(worker);;
		//service.shutdown();
	}
}
