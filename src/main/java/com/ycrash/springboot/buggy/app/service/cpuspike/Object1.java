package com.ycrash.springboot.buggy.app.service.cpuspike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Ram Lakshmanan
 */
public class Object1 {
	
	private static final Logger log = LoggerFactory.getLogger(Object1.class);
	 
	public static void execute() throws InterruptedException {
		while (true) {
			doSomething();
		}		
	}
	
	public static void doSomething() {
	}
}
