package com.ycrash.springboot.buggy.app.service.threadleak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ycrash.springboot.buggy.app.controller.BuggyAppController;

/**
 * Created infinite number of threads
 * 
 * @author Ram Lakshmanan
 */
@Service
public class ThreadLeakDemoService {

	private static final int threadLeakSize = 1250;
	
	private static final Logger log = LoggerFactory.getLogger(ThreadLeakDemoService.class);

	public void start() {

		log.info("Thread App started");
		int threadCount = 0;
		
		while (threadCount < threadLeakSize) {
			log.info("Thread Started : "+threadCount);
			try {
				// Failed to put thread to sleep.
				Thread.sleep(10);
			} catch (Exception e) {
			}
			threadCount++;

			new ForeverThread().start();
		}
	}
}
