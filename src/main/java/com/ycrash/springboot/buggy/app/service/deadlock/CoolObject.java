package com.ycrash.springboot.buggy.app.service.deadlock;

public class CoolObject {

	public static synchronized void method1() {
		
		try {
			// Sleep for 10 seconds
			Thread.sleep(10 * 1000);
		} catch (Exception e) {
			
		}
		
		HotObject.method2();
	}
}
