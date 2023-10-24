package com.ycrash.springboot.buggy.app.service.deadlock;

public class ThreadA extends Thread {

	@Override	
	public void run() {
		CoolObject.method1();
	}
}
