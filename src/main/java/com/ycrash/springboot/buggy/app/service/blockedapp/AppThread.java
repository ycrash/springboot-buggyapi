package com.ycrash.springboot.buggy.app.service.blockedapp;

public class AppThread extends Thread {

	@Override
	public void run() {
		
		AppObject.getSomething();
	}
}
