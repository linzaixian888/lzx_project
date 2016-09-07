package com.lzx.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
	private static Logger logger=LoggerFactory.getLogger(LogbackDemo.class);
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			logger.debug("debug");
			logger.warn("warn");
			logger.error("error");
		}
		
	}
}
