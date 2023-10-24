package com.ycrash.springboot.buggy.app.service.deadlock;

public class ThreadB extends Thread {

	@Override	
	public void run() {
		HotObject.method2();
	}	
}
