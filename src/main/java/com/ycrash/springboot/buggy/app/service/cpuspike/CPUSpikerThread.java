package com.ycrash.springboot.buggy.app.service.cpuspike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ycrash.springboot.buggy.app.controller.BuggyAppController;

/**
 * 
 * @author Ram Lakshmanan
 */
public class CPUSpikerThread extends Thread {
	
	private static final Logger log = LoggerFactory.getLogger(CPUSpikerThread.class);


	@Override
	public void run() {
		log.info("Starting CPU Spike");
		try {
			Object1.execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
