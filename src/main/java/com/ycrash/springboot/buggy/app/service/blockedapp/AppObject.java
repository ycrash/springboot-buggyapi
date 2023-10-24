package com.ycrash.springboot.buggy.app.service.blockedapp;

public class AppObject {

	public static synchronized void getSomething() {

		// Put the thread to sleep forever. Basically first
		// thread would have acquired the lock and go to sleep
		// No other thread would be able to enter this method.
		while (true) {
			
			try {
				
				Thread.sleep(10 * 60 * 1000);
			} catch (Exception e) {}
		}
	
	}
}
