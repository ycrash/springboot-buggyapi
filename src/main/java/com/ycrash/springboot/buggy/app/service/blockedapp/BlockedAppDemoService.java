package com.ycrash.springboot.buggy.app.service.blockedapp;

import org.springframework.stereotype.Service;

@Service
public class BlockedAppDemoService {

	public  void start() {
		
		System.out.println("App started");
		for (int counter = 0; counter < 10; ++counter) {

			// Launch 10 threads.
			new AppThread().start();
		}
		
		System.out.println("App became unresponsive");		
	}
}
